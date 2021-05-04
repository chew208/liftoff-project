package mnewbold.liftoffproject.controller;

import mnewbold.liftoffproject.models.User;
import mnewbold.liftoffproject.models.data.UserRepository;
import mnewbold.liftoffproject.models.dto.LoginFormDTO;
import mnewbold.liftoffproject.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//hi
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;

    public static User GuestUser() {
        return new User("Guest", "", "guest");
    }

    private static void setupCommonAttributes(Model model, User user, String title) {
        Boolean loggedin = (user != null && !user.isGuest());
        model.addAttribute("loggedin", loggedin);
        model.addAttribute("user", user);
        model.addAttribute("title", title);
    }

    @GetMapping("/")
    public String getPage_Home(HttpServletRequest request, Model model) {
        setupCommonAttributes(model, getUserFromSession(request.getSession()), "Home");
        return "index";
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        System.out.println("inside displayRegistrationForm");
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
        System.out.println("inside processRegistrationForm");

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword(), "user");
        System.out.println("Attempting to save user " + newUser.getUsername());
        userRepository.save(newUser);
        System.out.println("User " + newUser.getUsername() + " saved to database, id: " + newUser.getId() + ".");
        setUserInSession(request.getSession(), newUser);
        System.out.println("User " + newUser.getUsername() + " stored in session.");

        return "redirect:";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        System.out.println("inside displayLoginForm");

        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
//            return null;
            return new User("Guest", "", "guest");
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();

    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "logout";
    }

    @GetMapping("/search")
    public String getPage_Search() {
        return "search";
    }

}