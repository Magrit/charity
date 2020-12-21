package pl.coderslab.charity.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final Validator validator;

    public AdminController(UserService userService, InstitutionRepository institutionRepository,
                           DonationRepository donationRepository, @Qualifier("getValidator") Validator validator) {
        this.userService = userService;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.validator = validator;
    }

    @ModelAttribute(name = "user")
    public AppUser currentUserInfo(){
        return userService.getCurrentUser();
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("adminsList", userService.findByRole("ROLE_ADMIN"));
        return "admin/admin";
    }

    @RequestMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable long id, RedirectAttributes attributes) {
        AppUser userById = userService.findById(id);
        List<Role> roleAdmin = userById.getRoles().stream()
                .filter(role -> role.getName().equals("ROLE_ADMIN"))
                .collect(Collectors.toList());
        if (roleAdmin.size() == 0){
            userService.deleteUser(userById);
            List<Donation> allByUser = donationRepository.findAllByUser(userById);
            donationRepository.deleteAll(allByUser);
            return "redirect:/admin";
        } else if (userService.countAllByRole("ROLE_ADMIN") > 1) {
            userService.deleteUser(userById);
            return "redirect:/admin";
        } else {
            attributes.addFlashAttribute("message", "Nie da się usunąć ostatniego administratora");
            return "admin";
        }
    }

    @GetMapping("/{id}")
    public String userInfo(@PathVariable long id, Model model){
        model.addAttribute("userInfo", userService.findById(id));
        return "admin/info";
    }

    @GetMapping("/users")
    public String usersInfo(Model model){
        model.addAttribute("usersList", userService.findByRole("ROLE_USER"));
        return "admin/users-info";
    }

    @GetMapping("/institutions")
    public String institutionsInfo(Model model){
        model.addAttribute("institutionsList", institutionRepository.findAll());
        return "admin/institutions";
    }

    @GetMapping("/institution/{id}")
    public String institutionInfo(@PathVariable long id, Model model){
        model.addAttribute("institutionInfo", institutionRepository.getOne(id));
        return "admin/institution-info";
    }

    @RequestMapping("/institution/delete/{id}")
    public String deleteInstitution(@PathVariable long id){
        institutionRepository.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/profile")
    public String adminProfile(Model model){
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "admin/profile";
    }

    @GetMapping("/profile/edit")
    public String changeProfile(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "admin/profile-edit";
    }

    @PostMapping("/profile/edit")
    public String performChanges(@RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName,
                                 @RequestParam String email, @RequestParam String phoneNumber, Model model) {
        AppUser currentUser = userService.getCurrentUser();
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phoneNumber);
        Set<ConstraintViolation<AppUser>> violations = validator.validate(currentUser);
        if (violations.isEmpty()){
            userService.saveUser(currentUser);
            return "redirect:/admin/profile";
        }
        model.addAttribute("errors", violations);
        return "admin/profile-edit";
    }

    @GetMapping("/password/edit")
    public String changePassword(){
        return "/admin/password-edit";
    }

    @PostMapping("/password/edit")
    public String performNewPassword(@RequestParam String password, @RequestParam String confirmPassword){
        if (password.equals(confirmPassword)){
            userService.changePassword(userService.getCurrentUser(), password);
            return "redirect:/admin/profile";
        }
        return "/admin/password-edit";
    }
}
