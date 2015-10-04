/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oobbit.entities.LinkConnection;
import oobbit.entities.Verifier;
import oobbit.orm.exceptions.NotValidLinkConnectionException;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class LinkConnections extends BasicORM {
    
    @Autowired
    private Verifier verifier;
    
    public int add(LinkConnection linkConnection) throws SQLException, NotValidLinkConnectionException {
        verifier.verify(linkConnection); // first verify then add if successful
        
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO `oobbit`.`connections` (`source_link_id`, `destination_link_id`, `title`, `creator`, `create_time`, `edit_time`) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);");
        statement.setInt(1, linkConnection.getSourceLinkId());
        statement.setInt(2, linkConnection.getDestinationLinkId());
        statement.setString(3, linkConnection.getTitle());
        statement.setInt(4, linkConnection.getCreator());
        
        statement.executeUpdate();
        statement.close();
        
        return linkConnection.getSourceLinkId();
    }
    
    public void update(LinkConnection linkConnection) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE `oobbit`.`connections` SET `title` = ? WHERE `connections`.`source_link_id` = ? AND `connections`.`destination_link_id` = ?;");
        statement.setString(1, linkConnection.getTitle());
        statement.setInt(2, linkConnection.getSourceLinkId());
        statement.setInt(3, linkConnection.getDestinationLinkId());
        
        statement.executeUpdate();
        statement.close();
    }
    
    public ArrayList<LinkConnection> getAll(int linkId) throws SQLException, NothingWasFoundException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM `connections` WHERE `source_link_id` = ?;");
        statement.setInt(1, linkId);
        
        return parseResults(statement.executeQuery());
    }
    
    public LinkConnection getOne(int sourceId, int destinationId) throws SQLException, NothingWasFoundException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM `connections` WHERE `source_link_id` = ? AND `destination_link_id` = ?;");
        statement.setInt(1, sourceId);
        statement.setInt(2, destinationId);
        
        ResultSet query = statement.executeQuery();
        
        if(query.next()) {
            LinkConnection connection = new LinkConnection();
            connection.parse(query);
            
            return connection;
        }
        
        throw new NothingWasFoundException("No connection found between given links.");
    }
    
    public void delete(int sourceId, int destinationId) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM `oobbit`.`connections` WHERE `connections`.`source_link_id` = ? AND `connections`.`destination_link_id` = ?;");
        statement.setInt(1, sourceId);
        statement.setInt(2, destinationId);
        
        statement.executeUpdate();
        statement.close();
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
