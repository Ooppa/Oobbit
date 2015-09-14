/*
 * Aineopintojen harjoitustyö: Tietokantasovellus
 * Helsingin yliopisto Tietojenkäsittelytieteen laitos
 * Ooppa 2015 - GNU General Public License, version 3.
 */
package oobbit.orm;

import java.sql.SQLException;
import java.util.List;
import oobbit.orm.exceptions.NothingWasFoundException;

/**
 *
 * @author Ooppa
 */
public interface BasicORM {
    public List<?> getAll() throws SQLException, NothingWasFoundException;
    public Object getOne(int id) throws SQLException, NothingWasFoundException;
}
