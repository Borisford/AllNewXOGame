package su.ANV.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.exeptions.*;
import su.ANV.models.Step;
import su.ANV.services.GameService;

@RestController
@RequestMapping("/gameplay/rest/start")
public class StartController {
    @Autowired
    private GameService gameService;

    @PostMapping("/simple/auto")
    public ResponseEntity startComplexGame() {
        try {
            return ResponseEntity.ok(gameService.startAutoGame());
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | GameIsFullException | NotAIIDException | NoCellException | NoVariantsException | NoPlayerInGameException | NotEmptyCellException | GameOverException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/simple/single")
    public ResponseEntity startSingleGame(@RequestParam Long playerKey) {
        try {
            return ResponseEntity.ok(gameService.startSinglePlayerGame(playerKey));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | PlayerAlreadyInGameException | GameIsFullException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/simple/multi")
    public ResponseEntity startMultiPlayerGame(@RequestParam Long playerKey) {
        try {
            return ResponseEntity.ok(gameService.startMultiPlayerGame(playerKey));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | PlayerAlreadyInGameException | GameIsFullException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/complex/auto")
    public ResponseEntity startComplexAutoGame(@RequestParam int numberOfPlayers) {
        try {
            return ResponseEntity.ok(gameService.startComplexAutoGame(numberOfPlayers));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | GameIsFullException | NotAIIDException | NoCellException | NoVariantsException | NoPlayerInGameException | NotEmptyCellException | GameOverException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/complex/single")
    public ResponseEntity startComplexSingleGame(@RequestParam Long playerKey, @RequestParam int numberOfPlayers) {
        try {
            return ResponseEntity.ok(gameService.startComplexSinglePlayerGame(playerKey, numberOfPlayers));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | PlayerAlreadyInGameException | GameIsFullException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/complex/multi")
    public ResponseEntity startComplexMultiPlayerGame(@RequestParam Long playerKey, @RequestParam int numberOfPlayers) {
        try {
            return ResponseEntity.ok(gameService.startComplexMultiPlayerGame(playerKey, numberOfPlayers));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | PlayerAlreadyInGameException | GameIsFullException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
