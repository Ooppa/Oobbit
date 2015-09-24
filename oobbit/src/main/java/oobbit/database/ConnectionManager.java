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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Handles the connection to the database.
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
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Unexpected SQLException occured.", ex);
            System.exit(1); // Force quit (TODO?)
        }
    }

    public Connection getConnection() throws SQLException {
        if(connection.isClosed()) {
            connect(); // Reconnect if the connection is closed
        }
        return connection;
    }

    /*
     * Pings the database every minute starting from 10 seconds after startup.
     * This will keep the connection to the database up and prevent timeout.
     */
    @Scheduled(initialDelay = 10000, fixedRate = 60000)
    public void pingDatabaseConnection() throws SQLException {
        try(Statement statement = connection.createStatement()) {
            statement.execute("/* ping */ SELECT 1;"); // Returns int = 1
            statement.close();
        }
    }

}
