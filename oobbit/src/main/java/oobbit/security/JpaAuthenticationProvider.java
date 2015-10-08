/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.security;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oobbit.entities.User;
import oobbit.orm.Users;
import oobbit.orm.exceptions.FailedLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Handles authentication in the application.
 *
 * @author Ooppa
 */
@Component
public class JpaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private Users users;

    @Autowired
    private AccessLevelToGrantedAuthority accessLevelToGrantedAuthority;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getPrincipal().toString();
        String password = a.getCredentials().toString();

        try {
            User user = users.attemptLogin(username, password);
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    password,
                    accessLevelToGrantedAuthority.accessLevelToSimpleGrantedAuthorityList(user.getAccessLevel())
            );
        } catch(SQLException ex) {
            Logger.getLogger(JpaAuthenticationProvider.class.getName()).log(Level.SEVERE, "SQLException was thrown while authenticating a user in JpaAuthenticationProvider.", ex);
        } catch(FailedLoginException ex) {
            Logger.getLogger(JpaAuthenticationProvider.class.getName()).log(Level.SEVERE, "FailedLoginException was thrown while tyring to authenticate a user.", ex);
        }

        throw new AuthenticationException("Unable to authenticate user "+username) {
        };
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }

}
