package su.ANV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.ANV.entities.PlayerEntity;
import su.ANV.entities.WinEntity;
import su.ANV.exeptions.GameOverException;
import su.ANV.exeptions.NoGameException;
import su.ANV.repositories.PlayerRepository;
import su.ANV.repositories.WinRepository;
import su.ANV.units.AI.AI;

import java.util.List;

@Service
public class WinService {
    @Autowired
    private WinRepository winRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public int getNumberOfPlayersWins(Long winnerId) {
        int res = 0;
        for (WinEntity winEntity : winRepository.findAll()) {
            if (winEntity.getWinnerId().equals(winnerId)) {
                res++;
            }
        }
        return res;
    }

    public String getGameWinnerName(Long playGroundId) throws NoGameException {
        Long winnerId;
        for (WinEntity winEntity : winRepository.findAll()) {
            if (winEntity.getPlayGroundId().equals(playGroundId)) {
                winnerId = winEntity.getWinnerId();
                if (winnerId == 1) {
                    return "Draw";
                }
                if (winnerId < 0) {
                    return AI.getAIName(winnerId);
                }
                return playerRepository.getById(winnerId).getName();
            }
        }
        throw new NoGameException("Неовозможно найти победителя в несуществующей игре");
    }
}
