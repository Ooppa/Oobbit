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
import javax.annotation.Resource;
import oobbit.database.DatabaseSettings;
import oobbit.entities.User;
import oobbit.orm.exceptions.FailedLoginException;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class Users extends BasicORM {
    
    @Resource(name = "DatabaseSettings")
    private DatabaseSettings settings;

    public int attemptLogin(String email, String password) throws SQLException, FailedLoginException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM `users` WHERE `email` = ? AND `password` = ?;");
        statement.setString(1, email);
        statement.setString(1, hash(password));
        
        ResultSet query = statement.executeQuery();
        
        if(query.next()) {
            User user = new User();
            user.parse(query);
            return user.getId();
        }

        throw new FailedLoginException();
    }

    public User get(int id) throws SQLException, NothingWasFoundException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT user_id, username, email, access_level, create_time FROM oobbit.users WHERE id = ?;");
        statement.setInt(1, id);

        ResultSet query = statement.executeQuery();
        
        if(query.next()) {
            User user = new User();
            user.parse(query);
            return user;
        }
        
        throw new NothingWasFoundException("No user found with that id.");
    }

    /**
     * Creates a user and returns its id.
     *
     * @param username Username for the user
     * @param email Email for the user
     * @param password Hashed password
     *
     * @return
     *
     * @throws SQLException
     */
    public int registerNewUser(String username, String email, String password) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO `oobbit`.`users` (`user_id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (NULL, ?, ?, ?, '1', CURRENT_TIMESTAMP);", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, hash(password));

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next()) {
            return rs.getInt(1);
        }

        throw new SQLException("Could not add user!");
    }
    
    private String hash(String password) {
        return BCrypt.hashpw(password, settings.getSalt());
    }

}
