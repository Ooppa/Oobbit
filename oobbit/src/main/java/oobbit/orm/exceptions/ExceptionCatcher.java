/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm.exceptions;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import oobbit.database.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Catches Exceptions thrown by the system.
 *
 * @author Ooppa
 */
@ControllerAdvice
public class ExceptionCatcher {
    
    @Autowired
    ConnectionManager manager;
    
    @RequestMapping(value = "/error/SQLError", produces = "application/json")
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public String catchSQLException(
            Model model,
            HttpServletRequest request,
            Exception exception) {
        
        try {
            if(manager.getConnection().isClosed()) {
                manager.connect(); // Reconnect if the connection is closed
            }
        } catch(SQLException ex) {
            Logger.getLogger(ExceptionCatcher.class.getName()).log(Level.SEVERE, "Failed to reconnect to the database.", ex);
        }
        
        model.addAttribute("title", exception.toString());
        model.addAttribute("content", Arrays.toString(exception.getStackTrace()));
        return "error";
    }
}
