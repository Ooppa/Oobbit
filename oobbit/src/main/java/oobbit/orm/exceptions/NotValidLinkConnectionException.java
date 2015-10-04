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
public class NotValidLinkConnectionException extends Exception {

    public NotValidLinkConnectionException() {
    }

    public NotValidLinkConnectionException(String message) {
        super(message);
    }

    public NotValidLinkConnectionException(Throwable cause) {
        super(cause);
    }

    public NotValidLinkConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
