package pl.coderslab.charity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "{not_blank}")
    @Email(message = "{email.correctness}")
    private String email;

    @NotBlank(message = "{not_blank}")
    private String password;
    private String firstName;
    private String lastName;

    @Pattern(regexp = "[0-9]{9}", message = "{phone.reg}")
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

}
