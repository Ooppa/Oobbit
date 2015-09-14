/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.entities;

/**
 *
 * @author Ooppa
 */
public class Category {

    private int id;
    private String title;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67*hash+this.id;
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
        if(this.id!=other.id) {
            return false;
        }
        return true;
    }
}
