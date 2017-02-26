package net.proselyte.boxoffice.controller.request_handlers;

import net.proselyte.boxoffice.dao.DaoFactory;
import net.proselyte.boxoffice.dao.GenericDao;
import net.proselyte.boxoffice.model.Play;
import net.proselyte.boxoffice.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The TicketRequestHandler class is responsible
 * for the processing ticket request.
 *
 * @author deniskovpaka
 */
public class TicketRequestHandler extends RequestHandler {
    /**
     * TicketRequestHandler constructor.
     */
    public TicketRequestHandler(DaoFactory factory, Connection connection) {
        super(factory, connection);
    }

    /**
     * This method prepare data for the *JSP_TICKETS_FILENAME* file,
     * in order to show current state of the ticket table in DB.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            GenericDao dao = getFactory().getDao(getConnection(), Ticket.class);
            List<Ticket> ticketsList = dao.getAll();
            setRequestAttribute(JSP_TICKET_ATTRIBUTE, ticketsList, req);
            Integer[] playsId = getTicketsPlaysId(ticketsList);
            String[] playNamesList = getOrderedPlaysNameList(playsId);
            setRequestAttribute(JSP_PLAYS_NAMES_LIST_ATTRIBUTE, playNamesList, req);
            forwardRequestToJSPFile(JSP_TICKETS_FILENAME, req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return array of all playId's from ticket table.
     * Uses to ordering play's name list for further processing
     * in *JSP_TICKETS_FILENAME* file.
     */
    private Integer[] getTicketsPlaysId(final List<Ticket> ticketsList) {
        Integer[] ticketsPlaysId = new Integer[ticketsList.size()];
        Integer position = 0;
        for (Ticket ticket : ticketsList)
            ticketsPlaysId[position++] = ticket.getPlayId();
        return ticketsPlaysId;
    }

    /**
     * Return ordering plays list according to
     * the order array.
     */
    private String[] getOrderedPlaysNameList(Integer[] order)
            throws SQLException {
        GenericDao dao = getFactory().getDao(getConnection(), Play.class);
        List<String> orderedPlaysList = new ArrayList<>();
        for (Integer primaryKey : order) {
            Play play = (Play) dao.getByPrimaryKey(primaryKey);
            orderedPlaysList.add(play.getPlayName());
        }
        return orderedPlaysList.toArray(new String[0]);
    }
}