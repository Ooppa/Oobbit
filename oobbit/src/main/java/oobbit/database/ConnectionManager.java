/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * Performs queries to the connection given
 *
 * @author Ooppa
 */
@Component
public class ConnectionManager {

    private Connection connection;

    @Resource(name = "DatabaseSettings")
    private DatabaseSettings settings;

    public ConnectionManager() {
    }

    /**
     * Connects to the Database described in the database properties file.
     */
    @PostConstruct
    public void connect() {
        try {
            connection = DriverManager.getConnection(settings.getConnectorString());
        } catch(SQLException ex) {
            connection = null;
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1); // Force quit (TODO?)
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
