/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.security;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ooppa
 */
@Component
public class SanitizationHelper {

    private static final PolicyFactory strict = new HtmlPolicyBuilder()
            .allowElements("strong", "b", "i", "u")
            .toFactory();

    private static final PolicyFactory normal = new HtmlPolicyBuilder()
            .allowElements("a")
            .disallowWithoutAttributes("a")
            .allowAttributes("href").onElements("a")
            .allowAttributes("title").onElements("a")
            .allowElements("b", "strong", "u", "i")
            .toFactory();

    /**
     * Sanitizes the input using a Strict policy, only following HTML tags:
     * strong, b, i and u.
     *
     * @param input Input String
     *
     * @return Sanitized String
     */
    public String sanitizeStrict(String input) {
        return sanitize(input, strict);
    }

    /**
     * Sanitizes the input using a Loose policy, only following HTML tags: a
     * (with attributes href and title), b, strong, u, i and p.
     *
     * @param input Input String
     *
     * @return Sanitized String
     */
    public String sanitizeNormal(String input) {
        return sanitize(input, normal);
    }

    /*
     * Sanitizes a String using given policy.
     */
    private String sanitize(String input, PolicyFactory policy) {
        return policy.sanitize(input);
    }
}
