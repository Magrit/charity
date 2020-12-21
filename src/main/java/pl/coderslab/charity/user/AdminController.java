package pl.coderslab.charity.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public AdminController(UserService userService, InstitutionRepository institutionRepository,
                           DonationRepository donationRepository) {
        this.userService = userService;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
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

}
