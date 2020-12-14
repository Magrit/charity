package pl.coderslab.charity.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.user.AppUser;

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

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository,
                              InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    private AppUser getCurrentUser() {
        return new AppUser();
    }

    @ModelAttribute(name = "categoriesList")
    public List<Category> getCategoriesList() {
        return categoryRepository.findAll();
    }

    @ModelAttribute(name = "institutionsList")
    public List<Institution> getInstitutionsList() {
        return institutionRepository.findAll();
    }

    @GetMapping("/form")
    public String donationForm(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("phone", getCurrentUser().getPhoneNumber());
        return "form";
    }

    @PostMapping("/form")
    public String performForm(@RequestParam(required = false) List<Long> categories,
                              @RequestParam(required = false) Long institution,
                              @RequestParam(required = false) String phone, @RequestParam String date,
                              @RequestParam String time, @Valid @ModelAttribute("donation") Donation donation,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "/form";
        } else {
            getCurrentUser().setPhoneNumber(phone);
            donation.setCategories(categoryRepository.findAllById(categories));
            donation.setPickUpTime(LocalTime.parse(time));
            donation.setPickUpDate(LocalDate.parse(date));
            donation.setInstitution(institutionRepository.getOne(institution));
            donationRepository.save(donation);
            return "redirect:/";
        }
    }
}
