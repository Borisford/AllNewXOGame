package su.ANV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.entities.PlayerEntity;
import su.ANV.exeptions.BadNumberOfPlayersException;
import su.ANV.exeptions.BadPlaygroundSideException;
import su.ANV.exeptions.NoNameException;
import su.ANV.exeptions.NotUniquePlayerException;
import su.ANV.repositories.PlayGroundRepository;
import su.ANV.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlayGroundService {
    @Autowired
    private PlayGroundRepository playGroundRepository;

    public PlayGroundEntity createPlayGroundEntity() throws NotUniquePlayerException, NoNameException, BadNumberOfPlayersException, BadPlaygroundSideException {
        Random random = new Random();
        Long playGroundKeyKey = random.nextLong();
        while (playGroundRepository.existsByPlayGroundKey(playGroundKeyKey)) {
            playGroundKeyKey = random.nextLong();
        }
        return playGroundRepository.save(new PlayGroundEntity(playGroundKeyKey));
    }

    public List<PlayGroundEntity> getAllPlayGrounds() {
        List<PlayGroundEntity> playGroundEntities = new ArrayList<>();
        for (PlayGroundEntity playerEntity : playGroundRepository.findAll()) {
            playGroundEntities.add(playerEntity);
        }
        return playGroundEntities;
    }


}
