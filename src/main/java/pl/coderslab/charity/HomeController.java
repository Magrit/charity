package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;

import java.util.List;

@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions = institutionRepository.queryInstitution(8);
        model.addAttribute("institutions", institutions);
        List<Donation> donationList = donationRepository.findAll();
        int quantityCounter = donationList.stream().mapToInt(Donation::getQuantity).sum();
        model.addAttribute("quantityCounter", quantityCounter);
        model.addAttribute("donationsCounter", donationList.size());
        return "index";
    }
}
