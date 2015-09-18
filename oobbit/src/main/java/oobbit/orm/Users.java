/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oobbit.entities.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class Users extends BasicORM {

    public boolean attemptLogin(String email, String password) {
        // TODO
        return false;
    }

    public User get(int id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT user_id, username, email, access_level, create_time FROM oobbit.users WHERE id = ?;");
        statement.setInt(1, id);

        ResultSet query = statement.executeQuery();

        User user = new User();
        user.parse(query);
        return user;
    }

}
