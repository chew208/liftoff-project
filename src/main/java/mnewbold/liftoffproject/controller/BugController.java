package mnewbold.liftoffproject.controller;

import mnewbold.liftoffproject.models.Insect;
import mnewbold.liftoffproject.models.User;
import mnewbold.liftoffproject.models.data.InsectRepository;
import mnewbold.liftoffproject.models.data.UserRepository;
import mnewbold.liftoffproject.models.dto.AddBugFormDTO;
import mnewbold.liftoffproject.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class BugController {

    @Autowired
    private InsectRepository insectRepository;

//    @GetMapping("addbugg")
//    public String createInsect (Model model){
//        model.addAttribute("bug", new Insect());
//        return "addbug";
//    }

    @GetMapping("addbug")
    public String getBugPage(Model model){
//        model.addAttribute("bug",this.insectRepository.findAll());
        model.addAttribute("bug", new Insect());
        return "addbug";
    }

    @PostMapping("addbug")
    public String processRegistrationForm(@ModelAttribute @Valid AddBugFormDTO addBugFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
        System.out.println("inside processRegistrationForm");

        if (errors.hasErrors()) {
            model.addAttribute("title", "AddBug");
            return "addbug";
        }

        Insect existingBug = insectRepository.findByBugname(addBugFormDTO.getBugName());

        if (existingBug != null) {
            errors.rejectValue("bugname", "bugname.alreadyexists", "A bug with that bugname already exists");
            model.addAttribute("title", "AddBug");
            return "addbug";
        }

        Insect newBug = new Insect(addBugFormDTO.getBugName());
        System.out.println("Attempting to save bug " + newBug.getBugName());
        insectRepository.save(newBug);
        System.out.println("User " + newBug.getBugName() + " saved to database, id: " + newBug.getId() + ".");

        return "redirect:";
    }


}
