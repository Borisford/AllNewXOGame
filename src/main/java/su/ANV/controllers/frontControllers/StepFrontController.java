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
        model.addAttribute("playerKey", playerKey);
        model.addAttribute("playerId", playerId);
        model.addAttribute("playGroundKey", playGroundKey);
        model.addAttribute("playGroundId", playGroundId);
        try {
            gameService.end(playGroundId);
        } catch (GameOverException e) {
            //e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return  "endTheGame";
        }
        PlayGroundEntity playGroundEntity = gameService.getPlayGround(playGroundId);
        try {
            model.addAttribute("strings", playGroundEntity.getStringsNum());
        } catch (NoCellException e) {
            e.printStackTrace();
        } catch (IncorrectSignException e) {
            e.printStackTrace();
        }
        //if (gameService.isYourStep(playGroundId, playerId) && cell > -1 && cell < gameService.getPlayGround(playGroundId).getContent().length) {
        if (gameService.isYourStep(playGroundId, playerId)) {
            try {
                playGroundEntity = gameService.steps(playerKey, playerId, playGroundKey, playGroundId, cell);
            } catch (NotAIIDException e) {
                e.printStackTrace();
            } catch (NoVariantsException e) {
                e.printStackTrace();
            } catch (NoCellException e) {
                //e.printStackTrace();
                model.addAttribute("message", "Выбранной ячейки не существует");
            } catch (NoPlayerInGameException e) {
                e.printStackTrace();
            } catch (NotEmptyCellException e) {
                //e.printStackTrace();
                model.addAttribute("message", "Выбранная ячейка не пуста");
            } catch (IncorrectSignException e) {
                e.printStackTrace();
            } catch (GameOverException e) {
                //e.printStackTrace();
                model.addAttribute("message", e.getMessage());
                return  "endTheGame";
            } catch (NoGameException e) {
                e.printStackTrace();
            } catch (GameIsNotFullException e) {
                //e.printStackTrace();
                model.addAttribute("message", "Ждем других игроков");
            }
        }
        try {
            model.addAttribute("strings", playGroundEntity.getStringsNum());
        } catch (NoCellException e) {
            e.printStackTrace();
        } catch (IncorrectSignException e) {
            e.printStackTrace();
        }
        if (gameService.isYourStep(playGroundId, playerId)) {
            return "yourStep";
        } else {
            return "notYourStep";
        }
    }

}
