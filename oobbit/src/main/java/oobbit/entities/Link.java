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
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author Ooppa
 */
public class Link implements Result {

    private int id;

    @Size(min = 3, max = 255)
    private String title;

    @Size(min = 5, max = 100000)
    private String content;

    @URL
    private String url;

    private String category;
    private int creatorId;
    private User creator;
    private Date createTime;
    private Date editTime;

    public Link() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public boolean isEdited() {
        return this.editTime!=null;
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
        final Link other = (Link) obj;
        if(this.id!=other.id) {
            return false;
        }
        return true;
    }

    @Override
    public void parse(ResultSet set) throws SQLException {
        this.id = set.getInt("link_id");
        this.title = set.getString("title");
        this.content = set.getString("content");
        this.url = set.getString("link");
        this.category = set.getString("category");
        this.creatorId = set.getInt("creator");
        this.creator = null; // set later
        this.createTime = set.getTimestamp("create_time");
        this.editTime = set.getTimestamp("edit_time");
    }

}
