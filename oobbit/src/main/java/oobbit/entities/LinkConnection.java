/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Ooppa
 */
public class LinkConnection implements Result {

    private int sourceLinkId;
    private int destinationLinkId;
    private String title;
    private int creator;
    private Date createTime;
    private Date editTime;

    public LinkConnection() {
    }

    public int getSourceLinkId() {
        return sourceLinkId;
    }

    public void setSourceLinkId(int sourceLinkId) {
        this.sourceLinkId = sourceLinkId;
    }

    public int getDestinationLinkId() {
        return destinationLinkId;
    }

    public void setDestinationLinkId(int destinationLinkId) {
        this.destinationLinkId = destinationLinkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13*hash+this.sourceLinkId;
        hash = 13*hash+this.destinationLinkId;
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
        final LinkConnection other = (LinkConnection) obj;
        if(this.sourceLinkId!=other.sourceLinkId) {
            return false;
        }
        if(this.destinationLinkId!=other.destinationLinkId) {
            return false;
        }
        return true;
    }

    @Override
    public void parse(ResultSet set) throws SQLException {
        this.sourceLinkId = set.getInt("source_link_id");
        this.destinationLinkId = set.getInt("destination_link_id");
        this.title = set.getString("title");
        this.creator = set.getInt("creator");
        this.createTime = set.getTimestamp("create_time");
        this.editTime = set.getTimestamp("edit_time");
    }

}
