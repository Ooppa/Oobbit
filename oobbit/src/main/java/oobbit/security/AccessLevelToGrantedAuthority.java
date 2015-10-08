/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class AccessLevelToGrantedAuthority {

    @Deprecated
    public SimpleGrantedAuthority accessLevelToSimpleGrantedAuthority(int accessLevel) {
        switch(accessLevel) {
            case 0:
                return new SimpleGrantedAuthority("ROLE_BANNED");
            case 1:
                return new SimpleGrantedAuthority("ROLE_USER");
            case 10:
                return new SimpleGrantedAuthority("ROLE_MODERATOR");
            case 100:
                return new SimpleGrantedAuthority("ROLE_ADMINISTRATOR");
            default:
                throw new AssertionError();
        }
    }

    /**
     * Returns a list of GrantedAuthorities based on the given access level.
     *
     * @param accessLevel access level as an integer
     *
     * @return List of GrantedAuthorities
     */
    public List<GrantedAuthority> accessLevelToSimpleGrantedAuthorityList(int accessLevel) {
        ArrayList<GrantedAuthority> auths = new ArrayList<>();

        switch(accessLevel) {
            case 0:
                auths.add(new SimpleGrantedAuthority("ROLE_BANNED"));
            case 1:
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            case 10:
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                auths.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));
            case 100:
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                auths.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));
                auths.add(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));
        }

        return auths;
    }

}
