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
     * Creates list of all plays in DB
     * and passed them to the *JSP_PLAYS_FILENAME* file.
     * See also the method
     * {@link RequestHandler#processRequest(HttpServletRequest, HttpServletResponse)}.
     */
    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        try {
            String userChoise = req.getParameter(JSP_USER_INPUT_ATTRIBUTE);
            System.out.println("userChoise = " + userChoise);

//            GenericDao dao = getFactory().getDao(getConnection(), Play.class);
//            List<Play> playsList = dao.getAll();
//            setRequestAttribute(JSP_PLAYS_LIST_ATTRIBUTE, playsList, req);
//            forwardRequestToJSPFile(JSP_PLAYS_FILENAME, req, resp);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}