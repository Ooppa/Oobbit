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
import oobbit.entities.Category;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class Categories extends BasicORM {

    public boolean isCategory(String id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM categories WHERE category_id = ?;");
        statement.setString(1, id);
        
        ResultSet query = statement.executeQuery();
        
        return query.next();
    }
    
    public Category getOne(String id) throws SQLException, NothingWasFoundException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM categories WHERE category_id = ?;");
        statement.setString(1, id);
        
        ResultSet query = statement.executeQuery();

        if(query.next()) {
            Category category = new Category();
            category.parse(query);
            return category;
        } else {
            throw new NothingWasFoundException("No such category!");
        }
    }
    
    public List<Category> getAll(int limit) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM oobbit.links LIMIT ?;");
        statement.setInt(1, limit);

        return parseResultSet(statement.executeQuery());
    }
    
    private ArrayList<Category> parseResultSet(ResultSet set) throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();

        while(set.next()) {
            Category category = new Category();
            category.parse(set);
            categories.add(category);
        }

        return categories;
    }
}
