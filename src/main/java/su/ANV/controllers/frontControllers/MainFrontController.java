package su.ANV.controllers.frontControllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gameplay/front/main")
public class MainFrontController {



    @GetMapping
    public String start(Model model) {
        return "main";
    }
}
