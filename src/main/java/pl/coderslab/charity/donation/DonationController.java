package pl.coderslab.charity.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/form")
    public String donationForm(Model model){
        model.addAttribute("categoriesList", categoryRepository.findAll());
        model.addAttribute("institutionsList", institutionRepository.findAll());
        return "form";
    }

    @PostMapping
    public String performForm(@RequestParam  List<Category> categories, @RequestParam int bags,
                              @RequestParam Institution institution, @RequestParam String address,
                              @RequestParam String city, @RequestParam String zipCode,
                              @RequestParam @Valid LocalDate pickUpDate,
                              @RequestParam LocalTime pickUpTime, @RequestParam String pickUpComment){
        Donation donation = new Donation(bags, categories, institution, address, city, zipCode, pickUpDate, pickUpTime,
                pickUpComment);
        donationRepository.save(donation);
        return "redirect:/";
    }
}
