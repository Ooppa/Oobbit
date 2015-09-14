/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oobbit.database.StatementPerformer;
import oobbit.entities.Link;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class Links implements BasicORM {

    @Autowired
    private StatementPerformer performer;

    @Override
    public List<Link> getAll() throws SQLException {
        ResultSet query = performer.query("SELECT * FROM oobbit.links;");
        ArrayList<Link> links = new ArrayList<>();
        
        while(query.next()) {
            Link link = new Link();
            link.parse(query);
            links.add(link);
        }
        
        return links;
    }

    @Override
    public Link getOne(int id) throws SQLException, NothingWasFoundException {
        ResultSet query = performer.query("SELECT * FROM oobbit.links WHERE id="+id+";");
        
        while(query.next()) {
            Link link = new Link();
            link.parse(query);
            return link;
        }
        
        throw new NothingWasFoundException("There was no content with id: "+ id + ".");
    }
}
