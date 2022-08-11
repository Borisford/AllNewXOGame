package su.ANV.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.repositories.PlayGroundRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayGroundService {
    private final PlayGroundRepository playGroundRepository;

    public List<PlayGroundEntity> getAllPlayGrounds() {
        return new ArrayList<>(playGroundRepository.findAll());
    }

    public PlayGroundEntity getPlayGround(Long playGroundKey) {
        return playGroundRepository.findByPlayGroundKey(playGroundKey);
    }
}
