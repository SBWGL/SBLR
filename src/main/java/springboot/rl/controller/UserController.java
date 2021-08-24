package springboot.rl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/loginForm")
    public String login(){
        return "user/loginForm";
    }


}
