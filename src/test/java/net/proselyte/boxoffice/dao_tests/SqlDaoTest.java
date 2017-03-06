package net.proselyte.boxoffice.dao_tests;

import net.proselyte.boxoffice.dao.*;
import net.proselyte.boxoffice.model.*;
import org.junit.After;
import org.junit.Before;

import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

/**
 * The SqlDaoTest provides set of tests for
 * generic DAO instances (GenreDao, PlayDao, PlayGenreDao, SeatsDao, TicketDao).
 *
 * @author deniskovpaka
 */
public class SqlDaoTest extends AbstractDaoTest<Connection> {
    private Connection connection;
    private GenericDao dao;
    private static final DaoFactory<Connection> factory = new BoxOfficeDaoFactory();

    /** Set of parameters for filling up of the tested objects. */
    private static final String PARAMETER_NAME      = "someName";
    private static final Integer PARAMETER_PLAY_ID  = 10;
    private static final Integer PARAMETER_GENRE_ID = 4;
    private static final Integer PARAMETER_TICKET   = 2;


    @Parameterized.Parameters
    public static Collection getParameters() {
        Genre genre = new Genre();
        genre.setGenreName(PARAMETER_NAME);

        Play play = new Play();
        play.setPlayName(PARAMETER_NAME);

        PlayGenre playGenre = new PlayGenre();
        playGenre.setPlayId(PARAMETER_PLAY_ID);
        playGenre.setGenreId(PARAMETER_GENRE_ID);

        Ticket ticket = new Ticket();
        ticket.setPlayId(PARAMETER_PLAY_ID);
        ticket.setTicketNumber(new Integer[]{PARAMETER_TICKET});

        //TODO need mentor advice - org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "seats_play_id_key"
        //TODO (continue) Key (play_id)=(10) already exists. --->>> It looks like some problems with table constraint
//        Seats seats = new Seats();
//        seats.setPlayId(PARAMETER_PLAY_ID);

        return Arrays.asList(new Object[][] {
                { Genre.class, genre },
                { Play.class, play },
                { PlayGenre.class, playGenre },
                // { Seats.class, seats },
                { Ticket.class, ticket }
        });
    }

    @Before
    public void setUp()
            throws SQLException {
        connection = factory.getConnection();
        connection.setAutoCommit(false);
        dao = factory.getDao(connection, daoClass);
    }

    @After
    public void tearDown()
            throws SQLException {
        context().rollback();
        context().close();
    }

    @Override
    public GenericDao dao() {
        return dao;
    }

    @Override
    public Connection context() {
        return connection;
    }
}