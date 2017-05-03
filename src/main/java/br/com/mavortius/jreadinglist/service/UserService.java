package br.com.mavortius.jreadinglist.service;

import br.com.mavortius.jreadinglist.domain.User;
import br.com.mavortius.jreadinglist.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUser(String username) {
        return repository.findReaderByUsername(username);
    }

    public void save(User user) {
        if(user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
        }
        repository.save(user);
    }
}
