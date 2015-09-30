/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oobbit.entities.Link;
import oobbit.entities.User;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class Links extends BasicORM {

    public int add(Link link) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO `oobbit`.`links`(`link_id`,`title`,`content`,`link`,`category`,`creator`,`create_time`,`edit_time`) "
                +"VALUES(NULL, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, NULL);", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, link.getTitle());
        statement.setString(2, link.getContent());
        statement.setString(3, link.getUrl());
        statement.setString(4, link.getCategory());
        statement.setInt(5, link.getCreatorId());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next()) {
            return rs.getInt(1);
        }

        return -1; // failed
    }

    public int update(Link link) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE `oobbit`.`links` SET `title` = ?, `content` = ?, `link` = ?, `edit_time` = CURRENT_TIMESTAMP WHERE `links`.`link_id` = ?;");
        statement.setString(1, link.getTitle());
        statement.setString(2, link.getContent());
        statement.setString(3, link.getUrl());
        statement.setInt(4, link.getId());

        statement.executeUpdate();

        return link.getId();
    }

    public Link getOne(int id) throws SQLException, NothingWasFoundException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM oobbit.links AS q1 LEFT JOIN users AS q2 ON q1.creator = q2.user_id WHERE link_id = ?;");
        statement.setInt(1, id);

        ResultSet query = statement.executeQuery();

        if(query.next()) {
            Link link = new Link();
            link.parse(query);

            User user = new User();
            user.parse(query);
            link.setCreator(user);

            return link;
        } else {
            throw new NothingWasFoundException();
        }
    }

    public List<Link> getAll(int limit, String category) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM oobbit.links AS q1 LEFT JOIN users AS q2 ON q1.creator = q2.user_id WHERE q1.category = ? LIMIT ?;");
        statement.setString(1, category);
        statement.setInt(2, limit);

        return parseResults(statement.executeQuery());
    }

    public List<Link> getAll(int limit) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM oobbit.links AS q1 LEFT JOIN users AS q2 ON q1.creator = q2.user_id LIMIT ?;");
        statement.setInt(1, limit);

        return parseResults(statement.executeQuery());
    }

    private ArrayList<Link> parseResults(ResultSet results) throws SQLException {
        ArrayList<Link> links = new ArrayList<>();

        while(results.next()) {
            Link link = new Link();
            link.parse(results);

            User user = new User();
            user.parse(results);

            link.setCreator(user);

            links.add(link);
        }

        return links;
    }

    public void remove(int linkId) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM `oobbit`.`links` WHERE `links`.`link_id` = ?;");
        statement.setInt(1, linkId);
        
        statement.executeUpdate();
        statement.close();
    }
}
