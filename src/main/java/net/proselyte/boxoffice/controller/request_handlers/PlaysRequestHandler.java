package net.proselyte.boxoffice.controller.request_handlers;

import net.proselyte.boxoffice.dao.DaoFactory;
import net.proselyte.boxoffice.dao.GenericDao;
import net.proselyte.boxoffice.model.Play;
import net.proselyte.boxoffice.model.PlayGenre;
import net.proselyte.boxoffice.model.Seats;
import net.proselyte.boxoffice.model.helper.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The PlaysRequestHandler class is responsible
 * for the processing plays request.
 *
 * @author deniskovpaka
 */
public class PlaysRequestHandler extends RequestHandler {
    /**
     * PlaysRequestHandler constructor.
     */
    public PlaysRequestHandler(DaoFactory factory, Connection connection) {
        super(factory, connection);
    }

    /**
     * This method handles the request from the *JSP_PLAYS_FILENAME* file.
     * In case of the available seats the request will be forwarded to the
     * *JSP_SEATS_FILENAME* file, otherwise it will be returned to
     * the *JSP_PLAYS_FILENAME* file.
     * See also the method
     * {@link RequestHandler#processRequest(HttpServletRequest, HttpServletResponse)}.
     */
    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Integer inputtedPlayId = Integer.valueOf(req.getParameter(JSP_USER_INPUT_ATTRIBUTE));
            GenericDao dao = getFactory().getDao(getConnection(), Seats.class);
            String sqlQuery = "WHERE play_id = " + inputtedPlayId;
            Seats seats = (Seats) getDaoRecord(dao, sqlQuery);
            if (seats.hasAvailableSeats()) {
                boolean[] seatsList = Utils.booleansClassArrayToPrimitiveArray(seats.getSeatsList());
                setRequestAttribute(JSP_PLAY_ID_PARAMETER, inputtedPlayId, req);
                setRequestAttribute(JSP_SEATS_LIST_ATTRIBUTE, seatsList, req);
                forwardRequestToJSPFile(JSP_SEATS_FILENAME, req, resp);
            } else {
                List<Play> playsList = buildPlaysListForPlaysJSP(seats.getPlayId());
                setRequestAttribute(JSP_PLAYS_LIST_ATTRIBUTE, playsList, req);
                forwardRequestToJSPFile(JSP_PLAYS_FILENAME, req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method builds a one genre plays list for
     * the *JSP_PLAYS_FILENAME* file.
     * @param playId the ID to identify the play genre.
     * @return plays list.
     * @throws SQLException during genre list building.
     */
    private List<Play> buildPlaysListForPlaysJSP(int playId) throws SQLException {
        List<Play> playsList = getListPlaysOfOneGenre(playId);
        removePlayIfSeatsAreSold(playsList);
        if (playsList.isEmpty())
            throw new UnsupportedOperationException("All plays are reserved!!!"); // TODO Need to clarify with Mentor. IMHO - all plays should be shown
        return playsList;
    }

    /**
     * This method builds a one genre plays list.
     * @param playId the ID to identify the play genre.
     * @return one genre plays list.
     * @throws SQLException during read data from DB.
     */
    private List<Play> getListPlaysOfOneGenre(int playId)
            throws SQLException {
        GenericDao dao = getFactory().getDao(getConnection(), PlayGenre.class);
        List<Play> playsOfOneGenre = new ArrayList<>();

        String sqlQuery = "WHERE play_id = " + playId;
        PlayGenre playGenre = (PlayGenre) getDaoRecord(dao, sqlQuery);

        int genreId = playGenre.getGenreId();
        sqlQuery = "WHERE genre_id = " + genreId;
        List<PlayGenre> playGenreList = dao.getAll(sqlQuery);
        dao = getFactory().getDao(getConnection(), Play.class);
        for (PlayGenre pGenre : playGenreList) {
            Play play = (Play) dao.getByPrimaryKey(pGenre.getPlayId());
            playsOfOneGenre.add(play);
        }
        return playsOfOneGenre;
    }

    /**
     * This method removes play from the list of plays in case
     * of all seats are reserved/sold.
     * @param playsList list of plays that need to be checked.
     * @throws SQLException during read data from DB.
     */
    private void removePlayIfSeatsAreSold(List<Play> playsList)
            throws SQLException {
        GenericDao dao = getFactory().getDao(getConnection(), Seats.class);
        String sqlQuery;
        for(Play play : playsList) {
            sqlQuery = "WHERE play_id = " + play.getId();
            Seats seats = (Seats) getDaoRecord(dao, sqlQuery);
            if (!seats.hasAvailableSeats())
                playsList.remove(play);
        }
    }
}