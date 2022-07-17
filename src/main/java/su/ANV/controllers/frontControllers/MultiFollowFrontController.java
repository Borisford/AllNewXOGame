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
@RequestMapping("/gameplay/front/follow/multi")
public class MultiFollowFrontController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public String goStartFollow(Model model) {
        return "redirect:/gameplay/front/main";
    }

    @PostMapping
    public String followGame(Model model, @RequestParam Long playerKey, @RequestParam Long playerId) {
        model.addAttribute("playerKey", playerKey);
        model.addAttribute("playerId", playerId);
        model.addAttribute("message", "Присоединиться к игре по номеру");
        return "gameNumber";
    }

}
