package su.ANV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.exeptions.*;
import su.ANV.models.Step;
import su.ANV.services.GameService;
import su.ANV.services.StepService;

@RestController
@RequestMapping("/gameplay/rest/steps")
public class StepController {
    @Autowired
    private StepService stepService;
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity getAllPayers() {
        try {
            return ResponseEntity.ok(stepService.getAllSteps());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{playGroundKey}")
    public ResponseEntity getAllPayers(@PathVariable Long playGroundKey) {
        try {
            return ResponseEntity.ok(stepService.getTheGame(playGroundKey));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping
    public ResponseEntity makeManualStep(@RequestBody Step step) {
        try {
            return ResponseEntity.ok(gameService.step(step));
        } catch (NotAIIDException | NoVariantsException | NoCellException | NoPlayerInGameException | NotEmptyCellException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            //return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
