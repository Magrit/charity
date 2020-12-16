package pl.coderslab.charity.user;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, User> {
    AppUser findByEmail(String email);

    AppUser findByRoles(String role);
}
