package pl.coderslab.charity.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.user.AppUser;
import pl.coderslab.charity.user.UserService;

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

    private AppUser getCurrentUser(){
        return new AppUser();
    }

    @GetMapping("/form")
    public String donationForm(Model model){
        model.addAttribute("categoriesList", categoryRepository.findAll());
        model.addAttribute("institutionsList", institutionRepository.findAll());
        model.addAttribute("phone", getCurrentUser().getPhoneNumber());
        return "form";
    }

    @PostMapping("/form")
    public String performForm(@RequestParam List<Category> categories, @RequestParam @Valid int bags,
                              @RequestParam Institution institution, @RequestParam @Valid String address,
                              @RequestParam @Valid String city, @RequestParam String zipCode,
                              @RequestParam @Valid String phone, @RequestParam @Valid LocalDate date,
                              @RequestParam LocalTime time,  String comment, BindingResult result){
        if (result.hasErrors()){
            return "redirect:/donation/form";
        } else {
            getCurrentUser().setPhoneNumber(phone);
            Donation donation = new Donation(bags, categories, institution, address, city, zipCode, date, time, comment);
            donationRepository.save(donation);
            return "redirect:/";
        }
    }
}
