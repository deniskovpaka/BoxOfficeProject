package net.proselyte.boxoffice.dao;

import java.sql.SQLException;

/**
 * The DaoFactory class is responsible
 * for creation a set of DAO instances.
 *
 * @author deniskovpaka
 */
public interface DaoFactory<Connection> {

    interface DaoCreator<Connection> {
        GenericDao create(Connection connectionContext);
    }

    /**
     * This method uses to establish a connection to DB.
     * @return connection.
     * @throws SQLException during connection to DB.
     */
    Connection getConnection() throws SQLException;

    /**
     * Return dao instance.
     * @param context connection
     * @param daoClass
     * @throws SQLException
     */
    GenericDao getDao(Connection context, Class daoClass) throws SQLException;
}