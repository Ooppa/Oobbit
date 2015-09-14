/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ooppa
 */
public interface Result {

    /**
     * Parses the ResultSet and adds all data from it to it's fields.
     *
     * @param set ResultSet given by the JDBC driver
     *
     * @throws SQLException thrown if the process failed
     */
    public void parse(ResultSet set) throws SQLException;
}
