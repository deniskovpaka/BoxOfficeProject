package net.proselyte.boxoffice.controller.request_handlers;

import net.proselyte.boxoffice.dao.DaoFactory;
import net.proselyte.boxoffice.dao.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The RequestHandler class is responsible for
 * processing specific request from JSP file.
 *
 * @author deniskovpaka
 */
public abstract class RequestHandler {
    /**
     * JSP requester's name.
     */
    public static final String JSP_INITIALIZE_REQUEST   = "initializeRequest";
    public static final String JSP_PLAYS_REQUEST        = "playsRequest";
    public static final String JSP_SEATS_REQUEST        = "seatsRequest";
    public static final String JSP_TICKETS_REQUEST      = "ticketRequest";

    /**
     * JSP file names.
     * They needs to pass request from java side to JSP.
     */
    public static final String JSP_INIT_FILENAME    = "./pages/init.jsp";
    public static final String JSP_PLAYS_FILENAME   = "./pages/plays.jsp";
    public static final String JSP_SEATS_FILENAME   = "./pages/seats.jsp";
    public static final String JSP_TICKETS_FILENAME = "./pages/tickets.jsp";

    /**
     * JSP parameters/attributes names.
     */
    public static final String JSP_PLAY_ID_PARAMETER            = "play_id";
    public static final String JSP_SEAT_NUMBERS_PARAMETER       = "seatNumbers";
    public static final String SEATS_LIST_ATTRIBUTE             = "seatsList";
    public static final String PLAYS_LIST_ATTRIBUTE             = "playsList";
    public static final String JSP_USER_INPUT_ATTRIBUTE         = "jspUserInput";
    public static final String JSP_USER_NAME_ATTRIBUTE          = "user_name";

    /**
     * The UNIQUE constraint identifies in DB table.
     */
    private final int UNIQUENESS_SIZE = 1;

    private DaoFactory factory;
    private Connection connection;

    /**
     * RequestHandler constructor.
     * @param factory DAO factory.
     * @param connection is a context JDBC.
     */
    public RequestHandler(DaoFactory factory, Connection connection) {
        this.factory = factory;
        this.connection = connection;
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public abstract void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

    protected void setRequestAttribute(String attributeName, Object data,
                                       HttpServletRequest req)
            throws ServletException, IOException {
        req.setAttribute(attributeName, data);
    }

    protected void forwardRequestToJSPFile(String jspFileName,
                                           HttpServletRequest req,
                                           HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(jspFileName).forward(req, resp);
    }

    protected DaoFactory getFactory() {
        return factory;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected Object getDaoRecord(GenericDao dao, String sqlQuery)
            throws SQLException {
        List daoRecord = dao.getAll(sqlQuery);
        /*
        * Only one dao instance must be returned.
        */
        if (daoRecord == null || daoRecord.size() != UNIQUENESS_SIZE) {
            throw new SQLException("The record not found.!!!");
        }
        return daoRecord.get(0);
    }
}