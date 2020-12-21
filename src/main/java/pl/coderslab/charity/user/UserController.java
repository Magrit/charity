package pl.coderslab.charity.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.donation.DonationRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final DonationRepository donationRepository;
    private final Validator validator;

    public UserController(UserService userService, DonationRepository donationRepository, @Qualifier("getValidator") Validator validator) {
        this.userService = userService;
        this.donationRepository = donationRepository;
        this.validator = validator;
    }

    @GetMapping("/profile")
    public String profileInfo(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String profileEdit(Model model) {
        AppUser user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @PostMapping("/profile/edit")
    public String performProfileEdit(@RequestParam(required = false) String firstName,
                                     @RequestParam(required = false) String lastName,
                                     @RequestParam String email, @RequestParam String phoneNumber, Model model) {
        AppUser currentUser = userService.getCurrentUser();
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phoneNumber);
        Set<ConstraintViolation<AppUser>> violations = validator.validate(currentUser);
        if (violations.isEmpty()) {
            userService.saveUser(currentUser);
            return "redirect:/admin/profile";
        }
        model.addAttribute("errors", violations);
        return "admin/profile-edit";
    }

    @GetMapping("/donation")
    public String donationsInfo(Model model) {
        AppUser currentUser = userService.getCurrentUser();
        model.addAttribute("donationsList", donationRepository.findAllByUser(currentUser));
        return "donation";
    }

    @GetMapping("/donation/info/{id}")
    public String donationInfo(@PathVariable long id, Model model) {
        model.addAttribute("donation", donationRepository.getOne(id));
        return "donation-info";
    }

    @GetMapping("/profile/password")
    public String changePassword() {
        return "change-password";
    }

    @PostMapping("/profile/password")
    public String performNewPassword(@RequestParam String password, @RequestParam String confirmPassword) {
        if (password.equals(confirmPassword)) {
            userService.changePassword(userService.getCurrentUser(), password);
            return "redirect:/profile";
        }
        return "change-password";
    }
}
