package springboot.rl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/loginForm")
    public String login(){
        return "user/loginForm";
    }


}
