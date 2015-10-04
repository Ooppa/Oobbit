/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import java.sql.SQLException;
import javax.validation.Valid;
import oobbit.entities.Comment;
import oobbit.orm.Comments;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ooppa
 */
@Controller
@RequestMapping("/comments")
public class CommentController {
    
    @Autowired
    private Comments comments;
    
    // Adding comments is in the LinkController
    
    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/{id}/edit",
            method = RequestMethod.GET)
    public String editComment(@PathVariable int id, Model model) throws SQLException, NothingWasFoundException {
        Comment comment = comments.getOne(id);
        
        model.addAttribute("comment", comment);
        
        return "commentedit";
    }
    
    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/{id}/edit",
            method = RequestMethod.POST)
    public String doEditComment(@PathVariable int id, @Valid Comment comment, BindingResult bindingResult, Model model) throws SQLException, NothingWasFoundException {
        if(bindingResult.hasErrors()){
            return "commentedit";
        }
        
        comments.update(comment);
        
        Comment updated = comments.getOne(id);
        
        return "redirect:/view/"+updated.getLinkId();
    }
    
    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/{id}/remove",
            method = RequestMethod.POST)
    public String doDeleteComment(@PathVariable int id) throws SQLException, NothingWasFoundException {
        Comment comment = comments.getOne(id);
        
        comments.remove(comment.getId());
        
        return "redirect:/view/"+comment.getLinkId();
    }
    
}
