/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import java.sql.SQLException;
import javax.validation.Valid;
import oobbit.entities.Category;
import oobbit.entities.Link;
import oobbit.orm.Categories;
import oobbit.orm.Links;
import oobbit.orm.exceptions.NotValidObjectException;
import oobbit.orm.exceptions.NothingWasFoundException;
import oobbit.security.Sanitizer;
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
public class LinkController {

    @Autowired
    private Sanitizer sanitizer;

    @Autowired
    private Links links;

    @Autowired
    private Categories categories;

    private final int DEFAULT_AMOUNT_OF_LINKS = 25;

    @RequestMapping("/all/recent")
    public String list(Model model) throws SQLException {
        model.addAttribute("category", new Category("all", "All", "All categories"));
        model.addAttribute("links", links.getAll(DEFAULT_AMOUNT_OF_LINKS));
        return "linklist";
    }

    @RequestMapping("/{category}/recent")
    public String listCategory(@PathVariable String category, Model model) throws SQLException, NothingWasFoundException {
        model.addAttribute("links", links.getAll(DEFAULT_AMOUNT_OF_LINKS, category));
        model.addAttribute("category", categories.getOne(category));
        return "linklist";
    }

    @RequestMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) throws SQLException, NothingWasFoundException {
        model.addAttribute("link", links.getOne(id));
        return "linkview";
    }

    @Secured({"ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "{category}/add",
            method = RequestMethod.GET)
    public String add(@PathVariable String category, Model model) throws SQLException, NothingWasFoundException {
        model.addAttribute("category", categories.getOne(category));
        model.addAttribute("link", new Link()); // new empty link
        return "linkadd";
    }

    @Secured({"ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "{category}/add",
            method = RequestMethod.POST)
    public String doAdd(@Valid Link link, BindingResult bindingResult, Model model) throws SQLException, NothingWasFoundException, NotValidObjectException {
        if(bindingResult.hasErrors()) {
            return "linkadd"; // check for errors
        }
        
        link.setCreatorId(22); // TODO
        int addedId = links.add(sanitizer.sanitizeAndVerify(link));
        return "redirect:/view/"+addedId; // redirect to created link
    }
    
    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/edit/link/{linkId}",
            method = RequestMethod.GET)
    public String edit(@PathVariable int linkId, Model model) throws SQLException, NothingWasFoundException {
        model.addAttribute("link", links.getOne(linkId));
        return "linkedit";
    }
    
    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/edit/link/{linkId}",
            method = RequestMethod.POST)
    public String doEdit(@PathVariable int linkId, @Valid Link link, BindingResult bindingResult, Model model) throws SQLException, NothingWasFoundException, NotValidObjectException {
        if(bindingResult.hasErrors()) {
            return "linkedit"; // check for errors
        }
        
        //link.setId(linkId);
        
        links.update(link);
        
        return "redirect:/view/"+link.getId(); // redirect to edited link
    }
    
    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/remove/link/{linkId}",
            method = RequestMethod.POST)
    public String deRemove(@PathVariable int linkId, Model model) throws SQLException, NothingWasFoundException, NotValidObjectException {
        String category = links.getOne(linkId).getCategory();
        links.remove(linkId);
        
        return "redirect:/"+ category +"/recent"; // redirect to category in which the link was previously
    }
}
