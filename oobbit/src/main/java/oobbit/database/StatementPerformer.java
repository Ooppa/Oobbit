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
public class StatementPerformer {

    private Connection connection;

    @Resource(name = "DatabaseSettings")
    private DatabaseSettings settings;

    public StatementPerformer() {
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
            Logger.getLogger(StatementPerformer.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1); // Force quit (TODO?)
        }
    }

    public ResultSet query(String query) throws SQLException {
        Statement statement = null;
        ResultSet results = null;

        statement = connection.createStatement();
        results = statement.executeQuery(query);

        if(statement.execute(query)) {
            results = statement.getResultSet();
        }

        // Can't do that releaseResources(results, statement);

        return results;
    }

    private void releaseResources(ResultSet results, Statement statement) {
        if(results!=null) {
            try {
                results.close();
            } catch(SQLException ex) {
            } // ignore
        }

        if(statement!=null) {
            try {
                statement.close();
            } catch(SQLException ex) {
            } // ignore
        }
    }

}
