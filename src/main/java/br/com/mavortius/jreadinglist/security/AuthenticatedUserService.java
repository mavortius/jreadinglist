package br.com.mavortius.jreadinglist.security;

import br.com.mavortius.jreadinglist.domain.User;
import br.com.mavortius.jreadinglist.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticatedUserService implements UserDetailsService {

    private final UserService service;

    public AuthenticatedUserService(UserService service) {
        this.service = service;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.getUser(username)
                            .orElseThrow(() -> new UsernameNotFoundException(
                                    String.format("Login failed for username %s",
                                            username)
                            ));
        return new AuthenticatedUser(user);
    }
}
