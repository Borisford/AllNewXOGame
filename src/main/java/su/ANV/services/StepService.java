package su.ANV.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.entities.PlayerEntity;
import su.ANV.entities.StepEntity;
import su.ANV.exeptions.NoCellException;
import su.ANV.models.DemoStep;
import su.ANV.repositories.PlayGroundRepository;
import su.ANV.repositories.PlayerRepository;
import su.ANV.repositories.StepRepository;
import su.ANV.subEntities.PlayGroundLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StepService {
    private final StepRepository stepRepository;
    private final PlayerRepository playerRepository;
    private final PlayGroundRepository playGroundRepository;

    public List<StepEntity> getAllSteps() {
        return new ArrayList<>(stepRepository.findAll());
    }

    public List<DemoStep> getTheGame(Long playGroundKey) throws NoCellException {
        Map<Integer, StepEntity> allSteps = new HashMap<>();
        for (StepEntity stepEntity : stepRepository.findAll()) {
            if (stepEntity.getPlayGroundKey().equals(playGroundKey)) {
                allSteps.put(stepEntity.getStepNo(), stepEntity);
            }
        }
        List<StepEntity> stepEntities = new ArrayList<>();
        for (int i = 1; i <= allSteps.size(); i++) {
            if (allSteps.containsKey(i)) {
                stepEntities.add(allSteps.get(i));
            } else {
                return null;
            }
        }
        return toDemoStep(stepEntities);
    }

    private List<DemoStep> toDemoStep(List<StepEntity> stepEntityList) throws NoCellException {
        PlayGroundEntity playGroundEntity = playGroundRepository.getById(stepEntityList.get(0).getPlayGroundId());
        int side = PlayGroundLogic.getSide(playGroundEntity.getContent());
        char[] content = new char[side * side];
        for (int i = 0; i < side * side; i++) {
            content[i] = 0;
        }
        PlayerEntity playerEntity;
        String name;
        char sign;
        int cell;
        String[] field;
        int stepNo;
        List<DemoStep> res = new ArrayList<>();
        for (StepEntity stepEntity : stepEntityList) {
            if (stepEntity.getPlayerId() < 0) {
                name = "bot";
            } else {
                playerEntity = playerRepository.getById(stepEntity.getPlayerId());
                name = playerEntity.getName();
            }
            sign = stepEntity.getSign();
            cell = stepEntity.getCell();
            content[cell] = sign;
            field = PlayGroundLogic.getStrings(content);
            stepNo = stepEntity.getStepNo();
            res.add(new DemoStep(name, stepNo, sign, field));
        }
        return res;
    }
}
