package mnewbold.liftoffproject.controller;

import mnewbold.liftoffproject.models.Insect;
import mnewbold.liftoffproject.models.Plant;
import mnewbold.liftoffproject.models.data.InsectRepository;
import mnewbold.liftoffproject.models.data.PlantRepository;
import mnewbold.liftoffproject.models.dto.AddPlantFormDTO;
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
public class FlowerController {

    @Autowired
    private PlantRepository plantRepository;

//    @GetMapping("addbugg")
//    public String createInsect (Model model){
//        model.addAttribute("bug", new Insect());
//        return "addbug";
//    }

    @GetMapping("addplant")
    public String getPlantPage(Model model){
//        model.addAttribute("bug",this.insectRepository.findAll());
        model.addAttribute("plant", new Plant());
        return "addplant";
    }

    @PostMapping("addplant")
    public String processRegistrationForm(@ModelAttribute @Valid AddPlantFormDTO addPlantFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
        System.out.println("inside processRegistrationForm");

        if (errors.hasErrors()) {
            model.addAttribute("title", "AddPlant");
            return "addplant";
        }

        Plant existingPlant = plantRepository.findByFlowerName(addPlantFormDTO.getFlowerName());

        if (existingPlant != null) {
            errors.rejectValue("plantname", "plantname.alreadyexists", "A bug with that plantname already exists");
            model.addAttribute("title", "AddBug");
            return "addplant";
        }

        Plant newPlant = new Plant(addPlantFormDTO.getFlowerName());
        System.out.println("Attempting to save bug " + newPlant.getFlowerName());
        plantRepository.save(newPlant);
        System.out.println("User " + newPlant.getFlowerName() + " saved to database, id: " + newPlant.getId() + ".");

        return "redirect:";
    }

}
