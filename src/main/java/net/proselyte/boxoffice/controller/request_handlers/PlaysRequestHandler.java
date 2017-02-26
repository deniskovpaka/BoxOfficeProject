package net.proselyte.boxoffice.controller.request_handlers;

import net.proselyte.boxoffice.dao.DaoFactory;
import net.proselyte.boxoffice.dao.GenericDao;
import net.proselyte.boxoffice.model.Play;
import net.proselyte.boxoffice.model.PlayGenre;
import net.proselyte.boxoffice.model.Seats;
import net.proselyte.boxoffice.model.Utils;

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
     * See also the method
     * {@link RequestHandler#processRequest(HttpServletRequest, HttpServletResponse)}.
     */
    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userInputtedPlayId = req.getParameter(JSP_USER_INPUT_ATTRIBUTE);
        Integer inputtedPlayId = Integer.valueOf(userInputtedPlayId);
        try {
            GenericDao dao = getFactory().getDao(getConnection(), Seats.class);
            String sqlQuery = "WHERE play_id = " + inputtedPlayId;
            Seats seats = (Seats) getDaoRecord(dao, sqlQuery);
            if (seats.hasAvailableSeats()) {
                boolean[] seatsList = Utils.booleansClassArrayToPrimitiveArray(seats.getSeatsList());
                setRequestAttribute(JSP_PLAY_ID_PARAMETER, inputtedPlayId, req);
                setRequestAttribute(SEATS_LIST_ATTRIBUTE, seatsList, req);
                forwardRequestToJSPFile(JSP_SEATS_FILENAME, req, resp);
            } else {
                List<Play> playsList = buildPlaysListForPlaysJSP(seats.getPlayId());
                setRequestAttribute(PLAYS_LIST_ATTRIBUTE, playsList, req);
                forwardRequestToJSPFile(JSP_PLAYS_FILENAME, req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Play> buildPlaysListForPlaysJSP(int playId) throws SQLException {
        List<Play> playsList = getPlaysOfOneGenre(playId);
        removePlayIfSeatsAreSold(playsList);
        if (playsList.isEmpty())
            throw new UnsupportedOperationException("All plays are reserved!!!"); // TODO Need to clarify with Mentor.
        return playsList;
    }

    private void removePlayIfSeatsAreSold(List<Play> playsList) throws SQLException {
        GenericDao dao = getFactory().getDao(getConnection(), Seats.class);
        String sqlQuery;
        for(Play play : playsList) {
            sqlQuery = "WHERE play_id = " + play.getId();
            Seats seats = (Seats) getDaoRecord(dao, sqlQuery);
            if (!seats.hasAvailableSeats())
                playsList.remove(play);
        }
    }

    private List<Play> getPlaysOfOneGenre(int playId) throws SQLException {
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
}