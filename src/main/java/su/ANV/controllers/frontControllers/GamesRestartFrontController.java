package su.ANV.controllers.frontControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import su.ANV.services.PlayerService;

@Controller
@RequestMapping("/gameplay/front/restart")
public class GamesRestartFrontController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public String goStartRestart(Model model) {
        return "redirect:/gameplay/front/main";
    }

    @PostMapping
    public String restartAGame(Model model, @RequestParam Long playerKey, @RequestParam Long playerId) {
        model.addAttribute("playerKey", playerKey);
        model.addAttribute("playerId", playerId);
        return  "gamesStart";
    }

}
