package springboot.rl.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.rl.config.auth.PrincipalDetails;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }
}