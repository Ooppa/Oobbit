/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import java.sql.SQLException;
import javax.validation.Valid;
import oobbit.entities.LinkConnection;
import oobbit.orm.LinkConnections;
import oobbit.orm.Users;
import oobbit.orm.exceptions.NotValidLinkConnectionException;
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
@RequestMapping("/connection")
public class LinkConnectionController {

    @Autowired
    private LinkConnections connections;

    @Autowired
    private Users users;

    @Secured({"ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/{sourceId}/add",
            method = RequestMethod.GET)
    public String addConnection(
            @PathVariable int sourceId,
            Model model
    ) throws SQLException, NothingWasFoundException {
        LinkConnection linkConnection = new LinkConnection();
        linkConnection.setSourceLinkId(sourceId);

        model.addAttribute("linkConnection", linkConnection);
        model.addAttribute("roles", users.getCurrentUserRoles());
        return "connectionadd";
    }

    @Secured({"ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/{sourceId}/add",
            method = RequestMethod.POST)
    public String doAddConnection(
            @PathVariable int sourceId,
            @Valid LinkConnection linkConnection,
            BindingResult bindingResult,
            Model model
    ) throws SQLException, NothingWasFoundException, NotValidLinkConnectionException {
        if(bindingResult.hasErrors()) {
            linkConnection.setSourceLinkId(sourceId);
            model.addAttribute("roles", users.getCurrentUserRoles());
            return "connectionadd";
        }

        linkConnection.setSourceLinkId(sourceId);
        connections.add(linkConnection);
        return "redirect:/view/"+sourceId;
    }

    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/{sourceId}/{destinationId}/edit",
            method = RequestMethod.GET)
    public String editConnection(
            @PathVariable int sourceId,
            @PathVariable int destinationId,
            Model model
    ) throws SQLException, NothingWasFoundException {
        LinkConnection connection = connections.getOne(sourceId, destinationId);

        model.addAttribute("linkConnection", connection);
        model.addAttribute("roles", users.getCurrentUserRoles());
        return "connectionedit";
    }

    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/{sourceId}/{destinationId}/edit",
            method = RequestMethod.POST)
    public String doEditConnection(
            @PathVariable int sourceId,
            @PathVariable int destinationId,
            @Valid LinkConnection linkConnection,
            BindingResult bindingResult,
            Model model
    ) throws SQLException, NothingWasFoundException {
        if(bindingResult.hasErrors()) {
            linkConnection.setSourceLinkId(sourceId);
            linkConnection.setDestinationLinkId(destinationId);
            model.addAttribute("roles", users.getCurrentUserRoles());
            return "connectionedit";
        }

        linkConnection.setSourceLinkId(sourceId);
        linkConnection.setDestinationLinkId(destinationId);
        connections.update(linkConnection);
        return "redirect:/view/"+sourceId;
    }

    @Secured({"ROLE_MODERATOR", "ROLE_ADMINISTRATOR"})
    @RequestMapping(
            value = "/{sourceId}/{destinationId}/remove",
            method = RequestMethod.POST)
    public String doDeleteConnection(
            @PathVariable int sourceId,
            @PathVariable int destinationId
    ) throws SQLException, NothingWasFoundException {
        connections.delete(sourceId, destinationId);
        return "redirect:/view/"+sourceId;
    }

}
