package net.proselyte.boxoffice.dao;

import net.proselyte.boxoffice.model.*;
import net.proselyte.boxoffice.model.helper.DataBaseProperties;

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
     * DataBaseProperties instance.
     * Used to the establishing connection to DB.
     */
    private static final DataBaseProperties DATABASE_PROPERTIES_INSTANCE
                                            = DataBaseProperties.getInstance();

    /**
     * See also the method {@link DaoFactory#getConnection()}.
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_PROPERTIES_INSTANCE.getDataBaseUrl(),
                                                            DATABASE_PROPERTIES_INSTANCE.getDataBaseUser(),
                                                            DATABASE_PROPERTIES_INSTANCE.getDataBasePassword());
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
            Class.forName(DATABASE_PROPERTIES_INSTANCE.getDataBaseDriver());
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