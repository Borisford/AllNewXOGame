package su.ANV.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.exeptions.*;
import su.ANV.services.GameService;
import su.ANV.services.PlayGroundService;
import su.ANV.services.PlayerService;

@RestController
@RequestMapping("/gameplay/rest/playGrounds")
public class PlayGroundController {
    @Autowired
    private PlayGroundService playGroundService;

    @GetMapping
    public ResponseEntity getAllPlayGrounds() {
        try {
            return ResponseEntity.ok(playGroundService.getAllPlayGrounds());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{playerKey}")
    public ResponseEntity getPlayer(@PathVariable Long playerKey) {
        try {
            return ResponseEntity.ok(playGroundService.getPlayGround(playerKey));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
