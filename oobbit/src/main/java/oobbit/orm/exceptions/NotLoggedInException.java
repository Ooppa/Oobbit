/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm.exceptions;

/**
 *
 * @author Ooppa
 */
public class NotLoggedInException extends Exception {

    public NotLoggedInException() {
    }

    public NotLoggedInException(String message) {
        super(message);
    }

    public NotLoggedInException(Throwable cause) {
        super(cause);
    }

    public NotLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
