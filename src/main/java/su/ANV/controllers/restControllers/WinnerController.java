package su.ANV.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.ANV.services.WinService;

@RestController
@RequestMapping("/gameplay/rest/winners")
public class WinnerController {
    @Autowired
    private WinService winService;

    @GetMapping("/player/{winnerId}")
    public ResponseEntity<Object> getNumberOfPlayersWins(@PathVariable Long winnerId) {
        try {
            return ResponseEntity.ok(winService.getNumberOfPlayersWins(winnerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/game/{playGroundId}")
    public ResponseEntity<Object> getGameWinnerName(@PathVariable Long playGroundId) {
        try {
            return ResponseEntity.ok(winService.getGameWinnerName(playGroundId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
