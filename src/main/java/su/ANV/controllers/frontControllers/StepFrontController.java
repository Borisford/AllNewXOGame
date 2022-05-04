package su.ANV.controllers.frontControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.exeptions.*;
import su.ANV.models.Step;
import su.ANV.services.GameService;

@Controller
@RequestMapping("/gameplay/front/step")
public class StepFrontController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public String goStartSingle(Model model) {
        return "redirect:/gameplay/front/main";
    }

    @PostMapping
    public String stepFront(Model model, @RequestParam Long playerKey, @RequestParam Long playerId,
                       @RequestParam Long playGroundKey, @RequestParam Long playGroundId, @RequestParam int cell) {
        PlayGroundEntity playGroundEntity;
        try {
            model.addAttribute("playerKey", playerKey);
            model.addAttribute("playerId", playerId);
            model.addAttribute("playGroundKey", playGroundKey);
            model.addAttribute("playGroundId", playGroundId);
            gameService.end(playGroundId);
            if (gameService.isYourStep(playGroundId, playerId) && cell != -1) {
                playGroundEntity = gameService.steps(playerKey, playerId, playGroundKey, playGroundId, cell);
                model.addAttribute("strings", playGroundEntity.getStringsNum());
            } else {
                playGroundEntity = gameService.getPlayGround(playGroundId);
                model.addAttribute("strings", playGroundEntity.getStringsNum());
            }
            if (gameService.isYourStep(playGroundId, playerId)) {
                return "yourStep";
            } else {
                return "notYourStep";
            }
        } catch (GameOverException e){
            model.addAttribute("massage", e.getMessage());
            return  "endTheGame";
        } catch (NoCellException | IncorrectSignException | NotAIIDException | NoVariantsException | NoPlayerInGameException | NotEmptyCellException | NoGameException | GameIsNotFullException e) {
            e.printStackTrace();
            if (gameService.isYourStep(playGroundId, playerId)) {
                return  "yourStep";
            } else {
                return  "notYourStep";
            }
        }
    }

}
