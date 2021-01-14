package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.user.AppUser;
import pl.coderslab.charity.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserService userService;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository,
                          UserService userService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userService = userService;
    }

    @ModelAttribute("name")
    public String userName(){
        AppUser currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return currentUser.getFirstName();
        }
        return "";
    }


    @RequestMapping("/")
    public String homeAction(Model model) {
        AppUser currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            int roleAdmin = currentUser.getRoles().stream()
                    .filter(role -> role.getName().equals("ROLE_ADMIN"))
                    .collect(Collectors.toList()).size();
            if (roleAdmin == 1) {
                return "redirect:/admin";
            }
        }
        List<Institution> institutions = institutionRepository.queryInstitution(8);
        model.addAttribute("institutions", institutions);
        List<Donation> donationList = donationRepository.findAll();
        int quantityCounter = donationList.stream().mapToInt(Donation::getQuantity).sum();
        model.addAttribute("quantityCounter", quantityCounter);
        model.addAttribute("donationsCounter", donationList.size());
        return "index";
    }
}
