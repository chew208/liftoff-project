package mnewbold.liftoffproject.controller;

import mnewbold.liftoffproject.models.data.InsectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BugController {

    @Autowired
    private InsectRepository insectRepository;

    @GetMapping("addbug")
    public String getBugPage(Model model){
        model.addAttribute("bugs",this.insectRepository.findAll());
        return "addbug";
    }
}
