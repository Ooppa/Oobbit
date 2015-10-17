/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import oobbit.orm.Users;
import oobbit.orm.exceptions.NotLoggedInException;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String me(Model model) throws NothingWasFoundException, SQLException, NotLoggedInException {
        model.addAttribute("user", users.getCurrentUser());
        model.addAttribute("roles", users.getCurrentUserRoles());

        return "me";
    }

    @RequestMapping("/users/{id}")
    public String whois(@PathVariable int id, Model model) throws NothingWasFoundException, SQLException {
        model.addAttribute("user", users.get(id));
        model.addAttribute("roles", users.getCurrentUserRoles());
        
        return "whois";
    }

    @RequestMapping("/login")
    public String login(
            Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        model.addAttribute("haserror", params.containsKey("error"));
        model.addAttribute("roles", users.getCurrentUserRoles());
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(
            HttpSession session,
            Model model
    ) {
        session.invalidate(); // Clear session
        return "redirect:/login";
    }
}
