/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oobbit.entities.ErrorObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Replaces white-label error-pages.
 *
 * @see oobbit.entities.ErrorObject
 * @author Ooppa
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";
    private static final boolean INCLUDE_STACKTRACE = true;

    @Autowired
    private ErrorAttributes errorAttributes;

    /**
     * Displays the error to the user using ErrorObject.
     *
     * @param request  HttpServletRequest made by the user
     * @param response HttpServletResponse made by the server
     * @param model    Model to be included in the page
     *
     * @return template as a string
     */
    @RequestMapping(value = PATH)
    public String errorPage(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        model.addAttribute("error",
                new ErrorObject(
                        response.getStatus(),
                        errorAttributes.getErrorAttributes(
                                requestAttributes,
                                INCLUDE_STACKTRACE
                        )
                )
        );

        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
