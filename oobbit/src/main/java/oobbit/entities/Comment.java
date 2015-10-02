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

/**
 *
 * @author Ooppa
 */
public class Comment implements Result {

    private int id;
    private int linkId;
    private int creatorId;
    private User creator;

    @Size(min = 2, max = 5000)
    private String content;

    private Date createTime;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17*hash+this.id;
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
        final Comment other = (Comment) obj;
        if(this.id!=other.id) {
            return false;
        }
        return true;
    }

    @Override
    public void parse(ResultSet set) throws SQLException {
        this.id = set.getInt("comment_id");
        this.linkId = set.getInt("link_id");
        this.creatorId = set.getInt("creator");
        this.content = set.getString("content");
        this.createTime = set.getTimestamp("create_time");
    }

}
