/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, ver-sion 3.
 */
package oobbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Starting point for the Oobbit application.
 *
 * @author Ooppa
 */
@EnableAsync
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex) {
            System.out.println("You are missing the JDBC Driver (com.mysql.jdbc.Driver) "
                    + "which is essential for this software to work. "
                    + "Please make sure it's included pom.xml of this software.");
            System.exit(1); // Force quit (TODO?)
        }

        SpringApplication.run(Application.class, args);
    }
}
