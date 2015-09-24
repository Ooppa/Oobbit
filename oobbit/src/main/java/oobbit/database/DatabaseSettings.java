/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Ooppa
 */
@ImportResource("classpath:database.xml")
@Configuration
public class DatabaseSettings {

    private String host;
    private String database;
    private String user;
    private String password;
    private String salt;

    public DatabaseSettings() {
    }

    public DatabaseSettings(String host, String database, String user, String password, String salt) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
        this.salt = salt;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getConnectorString() {
        return "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+password;
    }
}
