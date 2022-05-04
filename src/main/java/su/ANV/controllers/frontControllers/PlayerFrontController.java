package su.ANV.controllers.frontControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import su.ANV.entities.PlayerEntity;
import su.ANV.exeptions.NoNameException;
import su.ANV.exeptions.NotUniquePlayerException;
import su.ANV.services.PlayerService;

@Controller
@RequestMapping("/gameplay/front/player")
public class PlayerFrontController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public String goStartPlayer(Model model) {
        return "redirect:/gameplay/front/main";
    }

    @PostMapping
    public String inputName(Model model) {
        model.addAttribute("massage", "Выберете себе имя.");
        return "addPlayer";
    }
}
