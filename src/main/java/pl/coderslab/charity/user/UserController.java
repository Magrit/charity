package pl.coderslab.charity.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.donation.DonationRepository;

@Controller
public class UserController {

    private final UserService userService;
    private final DonationRepository donationRepository;

    public UserController(UserService userService, DonationRepository donationRepository) {
        this.userService = userService;
        this.donationRepository = donationRepository;
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
                                     @RequestParam String email, @RequestParam String phoneNumber) {
        AppUser currentUser = userService.getCurrentUser();
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phoneNumber);
        userService.saveUser(currentUser);
        return "redireect:/profile";
    }

    @GetMapping("/donation")
    public String donationsInfo(Model model){
        AppUser currentUser = userService.getCurrentUser();
        model.addAttribute("donationsList", donationRepository.findAllByUser(currentUser));
        return "donation";
    }

    @GetMapping("/donation/{id}")
    public String donation(@PathVariable long id, Model model){
        model.addAttribute("donation", donationRepository.getOne(id));
        return "donation-info";
    }
}
