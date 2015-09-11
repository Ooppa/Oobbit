/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Default controller for the application.
 *
 * @author Ooppa
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        return "empty";
    }
    
    @RequestMapping("/demo/front")
    public String frontPage(Model model) {
        return "front";
    }
    
    @RequestMapping("/demo/linklist")
    public String listLinks(Model model) {
        return "linklist";
    }
    
    @RequestMapping("/demo/linkadd")
    public String addLink(Model model) {
        return "linkadd";
    }
    
    @RequestMapping("/demo/linkview")
    public String viewLink(Model model) {
        return "linkview";
    }

}
