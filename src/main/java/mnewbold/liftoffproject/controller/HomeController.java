package mnewbold.liftoffproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/logout")
    public String getPage() {
        return "logout";
    }

    @GetMapping("/search")
    public String getPage1() {
        return "search";
    }

}
