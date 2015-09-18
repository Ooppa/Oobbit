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
public class NotValidObjectException extends Exception {

    public NotValidObjectException() {
    }

    public NotValidObjectException(String message) {
        super(message);
    }

    public NotValidObjectException(Throwable cause) {
        super(cause);
    }

    public NotValidObjectException(String message, Throwable cause) {
        super(message, cause);
    }

}
