package su.ANV.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.exeptions.*;
import su.ANV.models.Step;
import su.ANV.services.GameService;

@RestController
@RequestMapping("/gameplay/rest/start")
public class StartController {
    @Autowired
    private GameService gameService;

    @PostMapping("/simple/auto")
    public ResponseEntity<PlayGroundEntity> startComplexGame() throws MyOverException {
        try {
            return ResponseEntity.ok(gameService.startAutoGame());
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | GameIsFullException | NotAIIDException | NoCellException | NoVariantsException | NoPlayerInGameException | NotEmptyCellException | GameOverException e){
            throw new MyOverException(e.getMessage());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @PostMapping("/simple/single")
    public ResponseEntity<PlayGroundEntity> startSingleGame(@RequestParam Long playerKey) throws MyOverException {
        try {
            return ResponseEntity.ok(gameService.startSinglePlayerGame(playerKey));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | PlayerAlreadyInGameException | GameIsFullException e){
            throw new MyOverException(e.getMessage());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @PostMapping("/simple/multi")
    public ResponseEntity<PlayGroundEntity> startMultiPlayerGame(@RequestParam Long playerKey) throws MyOverException {
        try {
            return ResponseEntity.ok(gameService.startMultiPlayerGame(playerKey));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | PlayerAlreadyInGameException | GameIsFullException e){
            throw new MyOverException(e.getMessage());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @PostMapping("/complex/auto")
    public ResponseEntity<PlayGroundEntity> startComplexAutoGame(@RequestParam int numberOfPlayers) throws MyOverException {
        try {
            return ResponseEntity.ok(gameService.startComplexAutoGame(numberOfPlayers));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | GameIsFullException | NotAIIDException | NoCellException | NoVariantsException | NoPlayerInGameException | NotEmptyCellException | GameOverException e){
            throw new MyOverException(e.getMessage());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @PostMapping("/complex/single")
    public ResponseEntity<PlayGroundEntity> startComplexSingleGame(@RequestParam Long playerKey, @RequestParam int numberOfPlayers) throws MyOverException {
        try {
            return ResponseEntity.ok(gameService.startComplexSinglePlayerGame(playerKey, numberOfPlayers));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | PlayerAlreadyInGameException | GameIsFullException e){
            throw new MyOverException(e.getMessage());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @PostMapping("/complex/multi")
    public ResponseEntity<PlayGroundEntity> startComplexMultiPlayerGame(@RequestParam Long playerKey, @RequestParam int numberOfPlayers) throws MyOverException {
        try {
            return ResponseEntity.ok(gameService.startComplexMultiPlayerGame(playerKey, numberOfPlayers));
        } catch (BadNumberOfPlayersException | BadPlaygroundSideException | PlayerAlreadyInGameException | GameIsFullException e){
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
