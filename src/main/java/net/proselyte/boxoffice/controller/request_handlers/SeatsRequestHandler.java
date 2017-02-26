package net.proselyte.boxoffice.controller.request_handlers;

import net.proselyte.boxoffice.dao.DaoFactory;
import net.proselyte.boxoffice.dao.GenericDao;
import net.proselyte.boxoffice.model.Seats;
import net.proselyte.boxoffice.model.Ticket;
import net.proselyte.boxoffice.model.helper.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The SeatsRequestHandler class is responsible
 * for the processing seats request.
 *
 * @author deniskovpaka
 */
public class SeatsRequestHandler extends RequestHandler {
    /**
     * SeatsRequestHandler constructor.
     */
    public SeatsRequestHandler(DaoFactory factory, Connection connection) {
        super(factory, connection);
    }

    /**
     * This method handles the request from the *JSP_SEATS_FILENAME* file,
     * also makes a new record to ticket table in DB.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Integer inputtedPlayId = Integer.valueOf(req.getParameter(JSP_PLAY_ID_PARAMETER));
            GenericDao dao = getFactory().getDao(getConnection(), Seats.class);
            Seats seats = (Seats) dao.getByPrimaryKey(inputtedPlayId);
            String[] seatNumbers = req.getParameterValues(JSP_SEAT_NUMBERS_PARAMETER);
            updateSeatsToReservedState(seatNumbers, seats);
            String userName = req.getParameter(JSP_USER_NAME_ATTRIBUTE);
            createRecordToTicketTable(inputtedPlayId, seatNumbers, userName);
            dao.update(seats);
            forwardRequestToHandler(RequestHandler.JSP_TICKETS_REQUEST, req, resp); // TODO
            //forwardRequestToJSPFile(JSP_INIT_FILENAME, req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set seats to reserved state.
     */
    private void updateSeatsToReservedState(String[] seatThatNeedToBeReserved, Seats seats) {
        for (String seatNumber : seatThatNeedToBeReserved) {
            int sNumber = Integer.parseInt(seatNumber);
            seats.setSeatState(sNumber, true);
        }
    }

    /**
     * Creates a new ticket record to DB.
     * @param playId
     * @param seatNumbers
     * @param userName
     * @throws SQLException during saving ticket to DB.
     */
    private void createRecordToTicketTable(Integer playId, String[] seatNumbers,
                                           String userName)
            throws SQLException {
        GenericDao dao = getFactory().getDao(getConnection(), Ticket.class);
        Ticket ticket = new Ticket();
        /** Save play ID */
        ticket.setPlayId(playId);
        /** Save visitor/user name */
        ticket.setVisitorName(userName);
        Integer[] seatNumber = Utils.convertArray(seatNumbers, Integer::parseInt, Integer[]::new);
        /** Save reserved tickets */
        ticket.setTicketNumber(seatNumber);
        /** Save ticket instance */
        dao.persist(ticket);
    }
}