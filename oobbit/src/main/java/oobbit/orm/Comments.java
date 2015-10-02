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
import java.util.List;
import oobbit.entities.Comment;
import oobbit.entities.User;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class Comments extends BasicORM {

    public void add(Comment comment) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO `oobbit`.`comments` (`comment_id`, `link_id`, `creator`, `content`, `create_time`) VALUES (NULL, ?, ?, ?, CURRENT_TIMESTAMP);");
        statement.setInt(1, comment.getLinkId());
        statement.setInt(2, comment.getCreatorId());
        statement.setString(3, comment.getContent());
        
        statement.executeUpdate();
        statement.close();
    }

    public List<Comment> getAllForLinkWithUser(int linkId, int limit) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM `comments` AS q1 LEFT JOIN users AS q2 ON q1.creator = q2.user_id WHERE q1.link_id = ? LIMIT ?;");
        statement.setInt(1, linkId);
        statement.setInt(2, limit);

        return parseResults(statement.executeQuery());
    }

    /**
     * Updates the given comment's content.
     *
     * @param comment
     *
     * @return ID to the link in which the comment is posted to
     *
     * @throws SQLException PreparedStatement failed to execute
     */
    public int update(Comment comment) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE `oobbit`.`comments` SET `content` = ? WHERE `comments`.`comment_id` = ?;");
        statement.setString(1, comment.getContent());
        statement.setInt(2, comment.getId());

        statement.executeUpdate();

        return comment.getLinkId();
    }

    /**
     * Returns one comment without User object attached to it.
     *
     * @param commentId
     *
     * @return
     *
     * @throws SQLException             PreparedStatement failed to execute
     * @throws NothingWasFoundException
     */
    public Comment getOne(int commentId) throws SQLException, NothingWasFoundException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM `comments` WHERE `comment_id` = ?;");
        statement.setInt(1, commentId);

        ResultSet query = statement.executeQuery();

        if(query.next()) {
            Comment comment = new Comment();
            comment.parse(query);

            return comment;
        } else {
            throw new NothingWasFoundException();
        }
    }

    /**
     * Removes a single comment. Please note that this is not called when a Link
     * is removed, the database handles the deletion of associated comments by
     * itself via CASCADE.
     *
     * @param commentId
     *
     * @throws SQLException PreparedStatement failed to execute
     */
    public void remove(int commentId) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM `oobbit`.`comments` WHERE `comments`.`comment_id` = ?");
        statement.setInt(1, commentId);

        statement.executeUpdate();
        statement.close();
    }

    private List<Comment> parseResults(ResultSet results) throws SQLException {
        ArrayList<Comment> comments = new ArrayList<>();

        while(results.next()) {
            Comment comment = new Comment();
            comment.parse(results);

            User user = new User();
            user.parse(results);

            comment.setCreator(user);

            comments.add(comment);
        }

        return comments;
    }

}
