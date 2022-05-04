package su.ANV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.repositories.PlayGroundRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayGroundService {
    @Autowired
    private PlayGroundRepository playGroundRepository;

    public List<PlayGroundEntity> getAllPlayGrounds() {
        return new ArrayList<>(playGroundRepository.findAll());
    }

    public Object getPlayGround(Long playGroundKey) {
        return playGroundRepository.findByPlayGroundKey(playGroundKey);
    }
}
