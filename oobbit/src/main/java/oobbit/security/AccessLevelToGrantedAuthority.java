/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class AccessLevelToGrantedAuthority {

    public SimpleGrantedAuthority accessLevelToSimpleGrantedAuthority(int accessLevel) {
        switch(accessLevel) {
            case 0:
                return new SimpleGrantedAuthority("BANNED");
            case 1:
                return new SimpleGrantedAuthority("USER");
            case 10:
                return new SimpleGrantedAuthority("MODERATOR");
            case 100:
                return new SimpleGrantedAuthority("ADMINISTRATOR");
            default:
                throw new AssertionError();
        }
    }

}
