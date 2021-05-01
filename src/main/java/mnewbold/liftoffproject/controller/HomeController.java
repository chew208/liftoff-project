package mnewbold.liftoffproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /*
    @GetMapping("/login")
    public String getPage_Login() {
        return "login";
    }

    @GetMapping("/register")
    public String getPage_FillOut() {
        return "register";
    }
    */
    @GetMapping("/search")
    public String getPage_Search() {
        return "search";
    }

    /*@GetMapping("/logout")
    public String getPage_Bye() {
        return "logout";
    }*/

}
