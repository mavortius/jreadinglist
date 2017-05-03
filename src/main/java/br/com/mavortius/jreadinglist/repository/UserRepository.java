package br.com.mavortius.jreadinglist.repository;

import br.com.mavortius.jreadinglist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findReaderByUsername(String username);
}
