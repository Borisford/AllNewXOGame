package su.ANV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.ANV.entities.PlayerEntity;
import su.ANV.entities.StepEntity;
import su.ANV.repositories.StepRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StepService {
    @Autowired
    private StepRepository stepRepository;

    public List<StepEntity> getAllSteps() {
        List<StepEntity> stepEntities = new ArrayList<>();
        for (StepEntity stepEntity : stepRepository.findAll()) {
            stepEntities.add(stepEntity);
        }
        return stepEntities;
    }

    public List<StepEntity> getTheGame(Long playGroundKey) {
        Map<Integer, StepEntity> allSteps = new HashMap<>();
        for (StepEntity stepEntity : stepRepository.findAll()) {
            if (stepEntity.getPlayGroundKey().equals(playGroundKey)) {
                allSteps.put(stepEntity.getStepNo(), stepEntity);
            }
        }
        List<StepEntity> stepEntities = new ArrayList<>();
        for (int i = 1; i <= allSteps.size(); i++) {
            System.out.println(i);
            if (allSteps.containsKey(i)) {
                stepEntities.add(allSteps.get(i));
            } else {
                return null;
            }
        }
        return stepEntities;
    }
}
