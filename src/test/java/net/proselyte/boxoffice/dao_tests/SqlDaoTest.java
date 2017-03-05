package net.proselyte.boxoffice.dao_tests;

import net.proselyte.boxoffice.dao.*;
import net.proselyte.boxoffice.model.Genre;
import net.proselyte.boxoffice.model.Play;
import net.proselyte.boxoffice.model.PlayGenre;
import org.junit.After;
import org.junit.Before;

import org.junit.runners.Parameterized;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

/**
 * The SqlDaoTest
 *
 * @author deniskovpaka
 */
public class SqlDaoTest extends AbstractDaoTest<Connection> {
    private Connection connection;
    private GenericDao dao;
    private static final DaoFactory<Connection> factory = new BoxOfficeDaoFactory();

    @Parameterized.Parameters
    public static Collection getParameters() {
        Genre genre = new Genre();
        genre.setGenreName("den"); // TODO remove and add )))
        Play play = new Play();
        play.setPlayName("kov");
        PlayGenre playGenre = new PlayGenre();
        playGenre.setPlayId(10);
        playGenre.setGenreId(4);
        return Arrays.asList(new Object[][] {
                { Genre.class, genre },
                { Play.class, play },
                {PlayGenre.class, playGenre }
        });
    }

    public SqlDaoTest(Class clazz, Identification<Integer> notPersistedDto) {
        super(clazz, notPersistedDto);
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