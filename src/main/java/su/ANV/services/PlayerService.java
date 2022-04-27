package su.ANV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.ANV.entities.PlayerEntity;
import su.ANV.exeptions.NoNameException;
import su.ANV.exeptions.NotUniquePlayerException;
import su.ANV.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public PlayerEntity createPlayerEntity(String name) throws NotUniquePlayerException, NoNameException {
        if (name.isEmpty()) {
            throw new NoNameException("При создании игрока не указано имя.");
        }
        if (playerRepository.existsByName(name)) {
            throw new NotUniquePlayerException("Имя " + name + " уже используется.");
        }
        Random random = new Random();
        Long playerKey = random.nextLong();
        while (playerRepository.existsByPlayerKey(playerKey)) {
            playerKey = random.nextLong();
        }
        return playerRepository.save(new PlayerEntity(playerKey, name));
    }

    public List<PlayerEntity> getAllPlayers() {
        List<PlayerEntity> playerEntities = new ArrayList<>();
        for (PlayerEntity playerEntity : playerRepository.findAll()) {
            playerEntities.add(playerEntity);
        }
        return playerEntities;
    }

    public Object getPlayer(Long playerKey) {
        return playerRepository.findByPlayerKey(playerKey);
    }
}
