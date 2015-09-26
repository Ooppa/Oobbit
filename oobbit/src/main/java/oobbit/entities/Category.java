/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Ooppa
 */
public class Category implements Result {

    private String id;
    private String title;
    private String description;

    public Category() {
    }

    public Category(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23*hash+Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) {
            return false;
        }
        if(getClass()!=obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if(!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public void parse(ResultSet set) throws SQLException {
        this.id = set.getString("category_id");
        this.title = set.getString("title");
        this.description = set.getString("description");
    }
}
