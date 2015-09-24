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
public class FailedLoginException extends Exception {

    public FailedLoginException() {
    }

    public FailedLoginException(String message) {
        super(message);
    }

    public FailedLoginException(Throwable cause) {
        super(cause);
    }

    public FailedLoginException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
