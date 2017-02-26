package net.proselyte.boxoffice.dao;

import net.proselyte.boxoffice.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The BoxOfficeDaoFactory class is responsible
 * for creation a specific DAO instance.
 *
 * @author deniskovpaka
 */
public class BoxOfficeDaoFactory implements DaoFactory<Connection> {

    /**
     * List of specific properties for the establishing
     * connection to DB. // TODO how to move this parameters in the config file???
     */
    private String user         = "postgres";
    private String password     = "p@ssword";
    private String url          = "jdbc:postgresql://localhost:5432/";
    private String driver       = "org.postgresql.Driver";

    /**
     * See also the method {@link DaoFactory#getConnection()}.
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * This map stores all DAO's objects.
     */
    private Map<Class, DaoCreator> creators;

    /**
     * BoxOfficeDaoFactory constructor.
     */
    public BoxOfficeDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<>();
        /**
         * Creation of all DAO instances.
         */
        creators.put(Play.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new PlayDao(connection);
            }
        });
        creators.put(Genre.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new GenreDao(connection);
            }
        });
        creators.put(Seats.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new SeatsDao(connection);
            }
        });
        creators.put(PlayGenre.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new PlayGenreDao(connection);
            }
        });
        creators.put(Ticket.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new TicketDao(connection);
            }
        });
    }

    /**
     * See also the method {@link DaoFactory#getDao(Object, Class)}.
     */
    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws SQLException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new SQLException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }
}