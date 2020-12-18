package pl.coderslab.charity.donation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.user.AppUser;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 1, message = "{quantity.min}")
    private int quantity;

    @ManyToMany
    @NotNull(message = "{radiobutton.notNull}")
    private List<Category> categories;

    @ManyToOne (fetch = FetchType.LAZY)
    @NotNull(message = "{radiobutton.notNull}")
    private Institution institution;

    @NotBlank(message = "{not_blank}")
    private String street;
    @NotBlank(message = "{not_blank}")
    private String city;
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "{zpiCode.pattern}")
    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "{date.future}")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

    @ManyToOne
    private AppUser user;

}
