package springboot.rl.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import springboot.rl.config.auth.PrincipalDetails;
import springboot.rl.model.Reservation;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@ModelAttribute Reservation reservation){
        return "index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}
