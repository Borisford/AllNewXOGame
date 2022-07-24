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
import su.ANV.services.GameService;
import su.ANV.services.PlayerService;
import su.ANV.subEntities.PlayGroundLogic;

@Controller
@RequestMapping("/gameplay/front/find")
public class MultiFindFrontController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public String goStartFind(Model model) {
        return "redirect:/gameplay/front/main";
    }

    @PostMapping
    public String findGame(Model model, @RequestParam Long playerKey, @RequestParam Long playerId, @RequestParam String playGroundKey) {
        PlayGroundEntity playGroundEntity;
        Long playGroundId = null;
        model.addAttribute("playerKey", playerKey);
        model.addAttribute("playerId", playerId);
        try {
            //Long playGroundKeyL = Long.valueOf(playGroundKey);
            playGroundEntity = gameService.addPlayerToPlayerToPlayGround(playerKey, playGroundKey);
            playGroundId = playGroundEntity.getId();
            model.addAttribute("playGroundKey", playGroundEntity.getPlayGroundKey());
            model.addAttribute("playGroundId", playGroundId);
            model.addAttribute("strings", PlayGroundLogic.getStringsNum(playGroundEntity));
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Формат номера игры некорректен");
            return "gameNumber";
        } catch (NoGameException | NoPlayerException | GameIsFullException | PlayerAlreadyInGameException e) {
            model.addAttribute("message", e.getMessage());
            return "gameNumber";
        } catch (NoCellException | IncorrectSignException e) {
            e.printStackTrace();
        }
        try {
            model.addAttribute("symbol", gameService.getSymbol(playerId, playGroundId));
        } catch (NoPlayerInGameException e) {
            e.printStackTrace();
        }
        return "notYourStep";
    }

}
