package su.ANV.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.ANV.exeptions.MyOverException;
import su.ANV.services.WinService;

@RestController
@RequestMapping("/gameplay/rest/winners")
public class WinnerController {
    @Autowired
    private WinService winService;

    @GetMapping("/player/{winnerId}")
    public ResponseEntity<Integer> getNumberOfPlayersWins(@PathVariable Long winnerId) throws MyOverException {
        try {
            return ResponseEntity.ok(winService.getNumberOfPlayersWins(winnerId));
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @GetMapping("/game/{playGroundId}")
    public ResponseEntity<String> getGameWinnerName(@PathVariable Long playGroundId) throws MyOverException {
        try {
            return ResponseEntity.ok(winService.getGameWinnerName(playGroundId));
        } catch (Exception e) {
            throw new MyOverException("Произошла непредвиденная ошибка" + e.getMessage());
        }
    }

    @ExceptionHandler(MyOverException.class)
    public ResponseEntity overE(MyOverException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
