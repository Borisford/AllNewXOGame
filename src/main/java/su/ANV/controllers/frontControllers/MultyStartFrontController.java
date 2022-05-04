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

@Controller
@RequestMapping("/gameplay/front/start/multi")
public class MultyStartFrontController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public String goStartMulty(Model model) {
        return "redirect:/gameplay/front/main";
    }

    @PostMapping
    public String startMultyGame(Model model, @RequestParam Long playerKey, @RequestParam Long playerId) {
        PlayGroundEntity playGroundEntity;
        try {
            playGroundEntity = gameService.startMultiPlayerGame(playerKey);
            model.addAttribute("playerKey", playerKey);
            model.addAttribute("playerId", playerId);
            model.addAttribute("playGroundKey", playGroundEntity.getPlayGroundKey());
            model.addAttribute("playGroundId", playGroundEntity.getId());
            model.addAttribute("strings", playGroundEntity.getStringsNum());
        } catch (BadNumberOfPlayersException | GameIsFullException | PlayerAlreadyInGameException | BadPlaygroundSideException | NoCellException | IncorrectSignException e) {
            e.printStackTrace();
        }
        return  "yourStep";
    }

}
