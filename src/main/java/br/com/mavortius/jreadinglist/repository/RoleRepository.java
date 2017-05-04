package br.com.mavortius.jreadinglist.repository;

import br.com.mavortius.jreadinglist.domain.Role;
import br.com.mavortius.jreadinglist.security.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(RoleAuthority authority);
}
