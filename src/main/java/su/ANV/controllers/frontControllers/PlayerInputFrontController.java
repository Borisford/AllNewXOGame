package su.ANV.controllers.frontControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import su.ANV.entities.PlayerEntity;
import su.ANV.exeptions.NoNameException;
import su.ANV.exeptions.NotUniquePlayerException;
import su.ANV.services.PlayerService;

@Controller
@RequestMapping("/gameplay/front/player/input")
public class PlayerInputFrontController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public String goStartInput(Model model) {
        return "redirect:/gameplay/front/main";
    }

    @PostMapping
    public String inputName(Model model, @RequestParam String name) {
        PlayerEntity playerEntity;
        try {
            playerEntity = playerService.createPlayerEntity(name);
            model.addAttribute("playerKey", playerEntity.getPlayerKey());
            model.addAttribute("playerId", playerEntity.getId());
            return  "gamesStart";
        } catch (NotUniquePlayerException | NoNameException e) {
            model.addAttribute("massage", e.getMessage());
            return "addPlayer";
        } catch (Exception e) {
            model.addAttribute("massage", e.getMessage());
        }
        return  "massagePage";
    }
}
