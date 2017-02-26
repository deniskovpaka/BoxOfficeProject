package net.proselyte.boxoffice.controller.request_handlers;

import net.proselyte.boxoffice.dao.DaoFactory;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The RequestHandlerFactory class is responsible
 * for creation a specific request handler.
 *
 * @author deniskovpaka
 */
public class RequestHandlerFactory {
    final static Logger logger = Logger.getLogger(RequestHandlerFactory.class.getName());

    /**
     * Use createRequestHandler method to get
     * object of type RequestHandler.
     * @param requestHandlerName
     * @return specific request handler.
     */
    public static RequestHandler createRequestHandler(String requestHandlerName,
                                                      DaoFactory factory,
                                                      Connection connection) {
        if (requestHandlerName == null) {
            logger.log(Level.WARNING, "The RequestHandler creation FAILED!!!" +
                    " The requestHandlerName is NULL.");
            return null;
        }
        if (requestHandlerName.equalsIgnoreCase(RequestHandler.JSP_INITIALIZE_REQUEST)) {
            return new InitializeRequestHandler(factory, connection);
        } else if (requestHandlerName.equalsIgnoreCase(RequestHandler.JSP_PLAYS_REQUEST)) {
            return new PlaysRequestHandler(factory, connection);
        } else if (requestHandlerName.equalsIgnoreCase(RequestHandler.JSP_SEATS_REQUEST)) {
            return new SeatsRequestHandler(factory, connection);
        } else if (requestHandlerName.equalsIgnoreCase(RequestHandler.JSP_TICKETS_REQUEST)) {
            return new TicketRequestHandler(factory, connection);
        } else
            logger.log(Level.WARNING, "The RequestHandler creation FAILED!!!" +
                    " The requestHandlerName is UNKNOWN.");
        return null;
    }
}