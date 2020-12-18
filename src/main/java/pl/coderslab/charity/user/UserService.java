package pl.coderslab.charity.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser getCurrentUser(){
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser == "anonymousUser"){
            return null;
        }
        return ((CurrentUser)currentUser).getAppUser();
    }

    public void saveNewUser(AppUser user){
        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(roleUser));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveUser(AppUser user){
        userRepository.save(user);
    }

    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<AppUser> findByRole(String role) {
        Role roleByName = roleRepository.findByName(role);
        return userRepository.findAllByRoles(roleByName);
    }

    public AppUser findById(long id) {
        return userRepository.getOne(id);
    }

    public int countAllByRole(String role) {
        Role byName = roleRepository.findByName(role);
        return userRepository.findAllByRoles(byName).size();
    }

    public void deleteUser(AppUser user) {
        userRepository.delete(user);
    }
}
