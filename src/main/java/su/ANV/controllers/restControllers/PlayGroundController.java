package su.ANV.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.exeptions.*;
import su.ANV.services.GameService;
import su.ANV.services.PlayGroundService;
import su.ANV.services.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/gameplay/rest/playGrounds")
public class PlayGroundController {
    @Autowired
    private PlayGroundService playGroundService;

    @GetMapping
    public ResponseEntity<List<PlayGroundEntity>> getAllPlayGrounds() throws MyOverException {
        try {
            return ResponseEntity.ok(playGroundService.getAllPlayGrounds());
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @GetMapping("/{playerKey}")
    public ResponseEntity<PlayGroundEntity> getPlayer(@PathVariable Long playerKey) throws MyOverException {
        try {
            return ResponseEntity.ok(playGroundService.getPlayGround(playerKey));
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @ExceptionHandler(MyOverException.class)
    public ResponseEntity overE(MyOverException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
