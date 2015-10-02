/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.security;

import java.sql.SQLException;
import oobbit.entities.Comment;
import oobbit.entities.Link;
import oobbit.entities.Verifier;
import oobbit.orm.exceptions.NotValidObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class Sanitizer {
    
    @Autowired
    private SanitizationHelper sanitizer;
    
    @Autowired
    private Verifier verifier;
    
    public Link sanitizeAndVerify(Link link) throws NotValidObjectException, SQLException {
        if(verifier.verify(link)){
            link.setContent(sanitizer.sanitizeNormal(link.getContent()));
        }

        return link;
    }
    
    public Comment sanitize(Comment comment) {
        comment.setContent(sanitizer.sanitizeNormal(comment.getContent()));
        
        return comment;
    }
    
    
}
