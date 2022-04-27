package su.ANV.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.exeptions.*;
import su.ANV.services.GameService;
import su.ANV.services.PlayerService;

@RestController
@RequestMapping("/gameplay/rest/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity getAllPayers() {
        try {
            return ResponseEntity.ok(playerService.getAllPlayers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{playerKey}")
    public ResponseEntity getPlayer(@PathVariable Long playerKey) {
        try {
            return ResponseEntity.ok(playerService.getPlayer(playerKey));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


    @PostMapping
    public ResponseEntity createPlayer(@RequestParam String name) {
        try {
            return ResponseEntity.ok(playerService.createPlayerEntity(name));
        } catch (NotUniquePlayerException | NoNameException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity addPlayer(@RequestParam Long playerKey, @RequestParam Long playGroundKey) {
        try {
            return ResponseEntity.ok(gameService.addPlayerToPlayerToPlayGround(playerKey, playGroundKey));
        } catch (NoGameException | GameIsFullException | NoPlayerException | PlayerAlreadyInGameException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
