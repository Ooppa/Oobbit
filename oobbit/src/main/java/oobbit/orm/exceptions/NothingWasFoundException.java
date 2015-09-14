/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm.exceptions;

/**
 * Thrown when query returns null.
 * @author Ooppa
 */
public class NothingWasFoundException extends Exception {

    public NothingWasFoundException() {
        super("The query performed returned null.");
    }

    public NothingWasFoundException(String message) {
        super(message);
    }

    public NothingWasFoundException(Throwable cause) {
        super(cause);
    }

    public NothingWasFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
