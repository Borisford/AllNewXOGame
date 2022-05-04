package su.ANV.controllers.frontControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gameplay/front/about")
public class AboutRestFrontController {
    @GetMapping
    public String aboutRest(Model model) {
        return "redirect:/gameplay/front/main";
    }

    @PostMapping
    public String startAGame(Model model) {
        return  "aboutREST";
    }
}
