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
    public static final String JSP_INITIALIZE_REQUEST           = "initializeRequest";
    public static final String JSP_PLAYS_REQUEST                = "playsRequest";
    public static final String JSP_SEATS_REQUEST                = "seatsRequest";
    public static final String JSP_TICKETS_REQUEST              = "ticketRequest";

    /**
     * JSP file names.
     * They needs to pass request from java side to JSP.
     */
    public static final String JSP_INIT_FILENAME                = "./pages/init.jsp";
    public static final String JSP_PLAYS_FILENAME               = "./pages/plays.jsp";
    public static final String JSP_SEATS_FILENAME               = "./pages/seats.jsp";
    public static final String JSP_TICKETS_FILENAME             = "./pages/tickets.jsp";

    /**
     * JSP parameters/attributes names.
     */
    public static final String JSP_PLAY_ID_PARAMETER            = "play_id";
    public static final String JSP_SEAT_NUMBERS_PARAMETER       = "seatNumbers";
    public static final String JSP_SEATS_LIST_ATTRIBUTE         = "seatsList";
    public static final String JSP_PLAYS_LIST_ATTRIBUTE         = "playsList";
    public static final String JSP_PLAYS_NAMES_LIST_ATTRIBUTE   = "playNamesList";
    public static final String JSP_USER_INPUT_ATTRIBUTE         = "jspUserInput";
    public static final String JSP_USER_NAME_ATTRIBUTE          = "user_name";
    public static final String JSP_TICKET_ATTRIBUTE             = "ticketTableList";
    public static final String JSP_REQUEST_NAME                 = "jspRequest";

    /**
     * Current DAO factory.
     */
    private DaoFactory factory;

    /**
     * Context.
     */
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
     * The processRequest method processing a specific
     * request from a JSP file.
     * The specific RequestHandler instance is related
     * with the specific JSP file.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public abstract void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

    /**
     * Return the current DAO factory.
     */
    protected DaoFactory getFactory() {
        return factory;
    }

    /**
     * Return the current context.
     */
    protected Connection getConnection() {
        return connection;
    }

    /**
     * The setRequestAttribute method used to set a attribute.
     * @param attributeName
     * @param data
     * @param req
     * @throws ServletException
     * @throws IOException
     */
    protected void setRequestAttribute(String attributeName, Object data,
                                       HttpServletRequest req)
            throws ServletException, IOException {
        req.setAttribute(attributeName, data);
    }

    /**
     * The forwardRequestToJSPFile method used to
     * forward a current request to another JSP file.
     * @param jspFileName
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void forwardRequestToJSPFile(String jspFileName,
                                           HttpServletRequest req,
                                           HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(jspFileName).forward(req, resp);
    }

    /**
     * The forwardRequestToHandler method used to
     * forward a current request to another RequestHandler class.
     * @param requestHandlerName
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void forwardRequestToHandler(String requestHandlerName,
                                           HttpServletRequest req,
                                           HttpServletResponse resp)
            throws ServletException, IOException {
        RequestHandler requestHandler =
                RequestHandlerFactory.createRequestHandler(requestHandlerName,
                                                getFactory(), getConnection());
        requestHandler.processRequest(req, resp);
    }

    /**
     * The getDaoRecord method MUST return only
     * one dao instance from DB.
     * @param dao
     * @param sqlQuery query.
     * @return Only one dao instance must be returned.
     * @throws SQLException
     */
    protected Object getDaoRecord(GenericDao dao, String sqlQuery)
            throws SQLException {
        List daoRecord = dao.getAll(sqlQuery);
        if (daoRecord == null) {
            throw new SQLException("The record not found.!!!");
        } else if (daoRecord.size() != 1 /** UNIQUENESS_SIZE */) {
            throw new SQLException("The record not UNIQUE in database.!!!");
        }
        return daoRecord.get(0);
    }
}