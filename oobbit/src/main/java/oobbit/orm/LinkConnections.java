/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oobbit.entities.LinkConnection;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class LinkConnections {
    
    public int add(LinkConnection link) throws SQLException {
        return -1; // TODO
    }
    
    public ArrayList<LinkConnection> getAll(int linkId) throws SQLException, NothingWasFoundException {
        return null; // TODO
    }
    
    private ArrayList<LinkConnection> parseResults(ResultSet results) throws SQLException {
        ArrayList<LinkConnection> linkConnections = new ArrayList<>();
        
        while(results.next()) {
            LinkConnection linkConnection = new LinkConnection();
            linkConnection.parse(results);
            
            linkConnections.add(linkConnection);
        }
        
        return linkConnections;
    }
}
