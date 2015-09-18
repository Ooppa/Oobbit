/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import java.sql.SQLException;
import oobbit.entities.Link;
import oobbit.orm.Categories;
import oobbit.orm.Links;
import oobbit.orm.exceptions.NotValidObjectException;
import oobbit.orm.exceptions.NothingWasFoundException;
import oobbit.security.Sanitizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("category", "All");
        model.addAttribute("links", links.getAllWithUser(DEFAULT_AMOUNT_OF_LINKS));
        return "linklist";
    }

    @RequestMapping("/{category}/recent")
    public String listCategory(@PathVariable String category, Model model) throws SQLException {
        model.addAttribute("links", links.getAll(DEFAULT_AMOUNT_OF_LINKS, category));
        return "linklist";
    }

    @RequestMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) throws SQLException, NothingWasFoundException {
        model.addAttribute("link", links.getOne(id));
        return "linkview";
    }

    @RequestMapping(
            value = "{category}/add",
            method = RequestMethod.GET)
    public String add(@PathVariable String category, Model model) throws SQLException, NothingWasFoundException {
        model.addAttribute("category", categories.getOne(category));
        return "linkadd";
    }

    @RequestMapping(
            value = "{category}/add",
            method = RequestMethod.POST)
    public String doAdd(@ModelAttribute Link link, Model model) throws SQLException, NothingWasFoundException, NotValidObjectException {
        link.setCreatorId(22); // TODO

        int addedId = links.add(sanitizer.sanitizeAndVerify(link));
        return "redirect:/view/"+ addedId;
    }
}
