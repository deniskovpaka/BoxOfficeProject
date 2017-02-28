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
     * This method define the user's choice in
     * order to find a specific play.
     * 1 - find play by PlayID.
     * 2 - find play by Play's name.
     * See also the method
     * {@link RequestHandler#processRequest(HttpServletRequest, HttpServletResponse)}.
     */
    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            Integer userPlayChoice = Integer.valueOf(req.getParameter(JSP_USER_INPUT_ATTRIBUTE));
            switch (userPlayChoice) {
                case 1:
                    choosePlayByID(req, resp);
                    break;
                case 2:
                    choosePlayByName(req, resp);
                    break;
                default:
                    throw new IOException("The choice for finding a play" +
                            "is not defined!!!");
            }
    }

    /**
     * Creates list of all plays in DB
     * and passed them to the *JSP_PLAYS_FILENAME* file.
     */
    private void choosePlayByID(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setPlaysListAttributeToRequest(req);
        forwardRequestToJSPFile(JSP_PLAYS_FILENAME, req, resp);
    }

    /**
     * Forward request to *JSP_INPUT_PLAYS_NAME_FILENAME* file in
     * order to user play name input.
     */
    private void choosePlayByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setPlaysListAttributeToRequest(req);
        forwardRequestToJSPFile(JSP_INPUT_PLAYS_NAME_FILENAME, req, resp);
    }

    private void setPlaysListAttributeToRequest(HttpServletRequest req)
            throws ServletException, IOException {
        try {
            GenericDao dao = getFactory().getDao(getConnection(), Play.class);
            List<Play> playsList = dao.getAll();
            setRequestAttribute(JSP_PLAYS_LIST_ATTRIBUTE, playsList, req);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}