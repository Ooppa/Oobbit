/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm;

import java.sql.Connection;
import java.sql.SQLException;
import oobbit.database.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ooppa
 */
public abstract class BasicORM {

    @Autowired
    private ConnectionManager connection;

    public Connection getConnection() throws SQLException {
        return connection.getConnection();
    }

}
