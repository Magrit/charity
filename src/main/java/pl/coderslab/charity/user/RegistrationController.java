package pl.coderslab.charity.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String performRegistration(@RequestParam String confirmPassword, @Valid AppUser appUser,
                                      BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors() || !appUser.getPassword().equals(confirmPassword)) {
            return "/register";
        }
        userService.saveNewUser(appUser);
        attributes.addFlashAttribute("message", "Pomyślnie zarejestrowano nowego użytkownika");
        return "redirect:/";
    }
}
