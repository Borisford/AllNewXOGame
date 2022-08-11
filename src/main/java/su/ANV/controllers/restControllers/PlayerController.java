package su.ANV.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.entities.PlayerEntity;
import su.ANV.exeptions.*;
import su.ANV.services.GameService;
import su.ANV.services.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/gameplay/rest/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<PlayerEntity>> getAllPayers() throws MyOverException{
        try {
            return ResponseEntity.ok(playerService.getAllPlayers());
        } catch (Exception e) {
            throw new MyOverException(e.getMessage());
        }
    }

    @GetMapping("/{playerKey}")
    public ResponseEntity<PlayerEntity> getPlayer(@PathVariable Long playerKey) throws MyOverException {
        try {
            return ResponseEntity.ok(playerService.getPlayer(playerKey));
        } catch (Exception e) {
            throw new MyOverException(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<PlayerEntity> createPlayer(@RequestParam String name) throws MyOverException {
        try {
            return ResponseEntity.ok(playerService.createPlayerEntity(name));
        } catch (NotUniquePlayerException | NoNameException e){
            throw new MyOverException(e.getMessage());
        } catch (Exception e) {
            throw new MyOverException(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<PlayGroundEntity> addPlayer(@RequestParam Long playerKey, @RequestParam String playGroundKey) throws MyOverException {
        try {
            return ResponseEntity.ok(gameService.addPlayerToPlayerToPlayGround(playerKey, playGroundKey));
        } catch (NoGameException | GameIsFullException | NoPlayerException | PlayerAlreadyInGameException e){
            throw new MyOverException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new MyOverException("Формат номера игры некорректен");
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @ExceptionHandler(MyOverException.class)
    public ResponseEntity overE(MyOverException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
