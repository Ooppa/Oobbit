/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import oobbit.orm.Users;
import oobbit.orm.exceptions.NotLoggedInException;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ooppa
 */
@Controller
public class UserController {
    
    @Autowired
    private Users users;
    
    @Secured({"ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping("/me")
    public String me(Model model) throws NothingWasFoundException, SQLException {
        try {
            model.addAttribute("user", users.getCurrentUser());
        } catch(NotLoggedInException ex) {
            return "redirect:/login"; // Force login, TODO
        }
        
        return "me";
    }
    
    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate(); // Clear session
        return "redirect:/login";
    }
}
