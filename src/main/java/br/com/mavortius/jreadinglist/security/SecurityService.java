package br.com.mavortius.jreadinglist.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    public AuthenticatedUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            return (AuthenticatedUser) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public boolean hasRole(RoleAuthority authority) {
        AuthenticatedUser authenticatedUser = getAuthenticatedUser();

        if(authenticatedUser != null) {
            return authenticatedUser.hasRole(authority);
        } else {
            return false;
        }
    }

    public boolean hasAnyRole(RoleAuthority ... authorities) {
        for(RoleAuthority authority : authorities) {
            if(hasRole(authority)) {
                return true;
            }
        }
        return false;
    }

    public void login(String username, String password) throws Exception {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userDetails, password);

            authenticationManager.authenticate(token);

            if(token.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(token);
                logger.info(String.format("%s was authenticated successfully!", username));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(token);

        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }
}
