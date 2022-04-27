package su.ANV.controllers;

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
    public ResponseEntity getAllPayers() {
        try {
            return ResponseEntity.ok(playGroundService.getAllPlayGrounds());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
