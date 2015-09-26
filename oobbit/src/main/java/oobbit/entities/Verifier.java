/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.entities;

import java.sql.SQLException;
import oobbit.orm.Categories;
import oobbit.orm.exceptions.NotValidObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class Verifier {

    @Autowired
    private Categories categories;

    /**
     * Attempts to verify a link.
     *
     * @param link Link to verify
     *
     * @return true if success, else exception
     *
     * @throws NotValidObjectException thrown if the verifying process failed.
     * @throws SQLException            SQL failure
     */
    public boolean verify(Link link) throws NotValidObjectException, SQLException {
        checkBounds(3, 255, link.getTitle(), "Title");
        checkBounds(5, 100000, link.getContent(), "Content");
        checkBounds(5, 255, link.getUrl(), "Link");
        checkCategory(link.getCategory());

        return true;
    }

    private boolean checkCategory(String category) throws SQLException, NotValidObjectException {
        if(!categories.isCategory(category)) {
            throw new NotValidObjectException("There is no such category.");
        }
        return true;
    }

    private boolean checkBounds(int min, int max, String string, String identifier) throws NotValidObjectException {
        if(string==null) {
            throw new NotValidObjectException(identifier+" should not be empty.");
        }

        if(string.length()<min) {
            throw new NotValidObjectException(identifier+" should not less than "+min+" characters.");
        }

        if(string.length()>max) {
            throw new NotValidObjectException(identifier+" should not more than "+max+" characters.");
        }

        return true;
    }

}
