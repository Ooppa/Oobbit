/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Ooppa
 */
public class User implements Result {

    private int id;
    
    @Size(min=2, max=30)
    private String username;
    
    @Email
    private String email;
    
    private int accessLevel;
    private Date createTime;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29*hash+this.id;
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
        final User other = (User) obj;
        if(this.id!=other.id) {
            return false;
        }
        return true;
    }

    @Override
    public void parse(ResultSet set) throws SQLException {
        this.id = set.getInt("user_id");
        this.username = set.getString("username");
        this.email = set.getString("email");
        this.accessLevel = set.getInt("access_level");
        this.createTime = set.getTimestamp("create_time");
    }

}
