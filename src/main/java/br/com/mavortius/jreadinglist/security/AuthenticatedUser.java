package br.com.mavortius.jreadinglist.security;

import br.com.mavortius.jreadinglist.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticatedUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public AuthenticatedUser(User user) {
        super(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(user.getRolesAsListString()));
        this.user = user;
    }

    @Override
    public String toString() {
        return user.getFullname();
    }
}
