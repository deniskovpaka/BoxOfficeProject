package net.proselyte.boxoffice.controller.request_handlers;

import net.proselyte.boxoffice.dao.DaoFactory;
import net.proselyte.boxoffice.dao.GenericDao;
import net.proselyte.boxoffice.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The TicketRequestHandler class is responsible
 * for the processing ticket request.
 *
 * @author deniskovpaka
 */
public class TicketRequestHandler extends RequestHandler {
    /**
     * ClientRequestHandler constructor.
     */
    public TicketRequestHandler(DaoFactory factory, Connection connection) {
        super(factory, connection);
    }

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            GenericDao dao = getFactory().getDao(getConnection(), Ticket.class);
            List<Ticket> ticketsList = dao.getAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        forwardRequestToJSPFile(JSP_INIT_FILENAME, req, resp);
    }
}