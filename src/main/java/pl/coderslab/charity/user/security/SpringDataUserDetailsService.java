package pl.coderslab.charity.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.user.AppUser;
import pl.coderslab.charity.user.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class SpringDataUserDetailsService implements UserDetailsService{

    private final UserService userService;

    @Autowired
    public SpringDataUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser byEmail = userService.findByEmail(email);
        if (byEmail == null){
            throw new UsernameNotFoundException(email);
        }else{
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            byEmail.getRoles().forEach(role
                    -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
            return new CurrentUser(byEmail.getEmail(), byEmail.getPassword(), grantedAuthorities, byEmail);
        }
    }

}
