/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import java.sql.SQLException;
import java.util.List;
import oobbit.entities.Link;
import oobbit.orm.Links;
import oobbit.orm.exceptions.NothingWasFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ooppa
 */
@RestController
public class TestController {

    @Autowired
    private Links links;

    @ResponseBody
    @RequestMapping(value = "/listlinks", produces = "application/json")
    public List<Link> listLinks() throws SQLException, NothingWasFoundException {
        return links.getAll();
    }

}
