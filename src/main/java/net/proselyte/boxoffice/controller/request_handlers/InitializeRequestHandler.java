package net.proselyte.boxoffice.controller.request_handlers;


import net.proselyte.boxoffice.dao.DaoFactory;
import net.proselyte.boxoffice.dao.GenericDao;
import net.proselyte.boxoffice.model.Play;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The InitializeRequestHandler class is responsible
 * for the processing initial request.
 * This class creates data in order to show
 * them in the *plays.jsp*.
 *
 * @author deniskovpaka
 */
public class InitializeRequestHandler extends RequestHandler {
    /**
     * InitializeRequestHandler constructor.
     */
    public InitializeRequestHandler(DaoFactory factory, Connection connection) {
        super(factory, connection);
    }

    /**
     * See also the method
     * {@link RequestHandler#processRequest(HttpServletRequest, HttpServletResponse)}.
     */
    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            GenericDao dao = getFactory().getDao(getConnection(), Play.class);
            List<Play> playsList = dao.getAll();
            setRequestAttribute(PLAYS_LIST_ATTRIBUTE, playsList, req);
            forwardRequestToJSPFile(JSP_PLAYS_FILENAME, req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}