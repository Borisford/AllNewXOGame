package su.ANV.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.entities.StepEntity;
import su.ANV.exeptions.*;
import su.ANV.models.DemoStep;
import su.ANV.models.Step;
import su.ANV.services.GameService;
import su.ANV.services.StepService;

import java.util.List;

@RestController
@RequestMapping("/gameplay/rest/steps")
public class StepController {
    @Autowired
    private StepService stepService;
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<StepEntity>> getAllSteps() throws MyOverException {
        try {
            return ResponseEntity.ok(stepService.getAllSteps());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @GetMapping("/{playGroundKey}")
    public ResponseEntity<List<DemoStep>> getTheGame(@PathVariable Long playGroundKey) throws NoCellException, MyOverException {
        try {
            return ResponseEntity.ok(stepService.getTheGame(playGroundKey));
        } catch (NoCellException e){
            throw new MyOverException(e.getMessage());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String[]> makeManualStep(@RequestBody Step step) throws MyOverException {
        try {
            return ResponseEntity.ok(gameService.step(step));
        } catch (NotAIIDException | NoVariantsException | NoCellException | NoPlayerInGameException | NotEmptyCellException e){
            throw new MyOverException(e.getMessage());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @ExceptionHandler(MyOverException.class)
    public ResponseEntity overE(MyOverException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
