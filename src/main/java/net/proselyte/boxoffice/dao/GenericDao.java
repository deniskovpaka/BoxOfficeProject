package net.proselyte.boxoffice.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * The GenericDao interface is responsible
 * for the management of the object's state.
 * @param <T> object type.
 * @param <PrimaryKey> primary key type.
 *
 * @author deniskovpaka
 */
public interface GenericDao<T extends Identification<PrimaryKey>, PrimaryKey> {
    /**
     * Create a new record related to the existing object.
     * @param object existing object.
     * @return the object.
     * @throws SQLException during saving object in DB.
     */
    T persist(T object)  throws SQLException;

    /**
     * Return the object corresponding to the record in DB.
     * @param key primary key.
     * @return the object corresponding to the record, NULL otherwise.
     * @throws SQLException during getting object from DB.
     */
    T getByPrimaryKey(PrimaryKey key) throws SQLException;

    /**
     * Update object's record.
     * @param object existing object.
     * @throws SQLException during updating object in DB.
     */
    void update(T object) throws SQLException;

    /**
     * Delete object's record.
     * @param object existing object.
     * @throws SQLException during deletion object in DB.
     */
    void delete(T object) throws SQLException;

    /**
     * Get list of objects corresponding to all records in DB
     * according to the sqlQuery.
     * The sqlQuery extends the default query.
     * Example: SELECT * FROM [Table] + sqlQuery [WHERE ...];
     * @param sqlQuery specific query to DB.
     * @throws SQLException during getting objects from DB.
     */
    List<T> getAll(String sqlQuery) throws SQLException;

    /**
     * Get list of objects corresponding to all records in DB,
     * according to the default query.
     * Example: Default query is SELECT * FROM [Table];
     * @return list of one type objects.
     * @throws SQLException during getting objects from DB.
     */
    List<T> getAll() throws SQLException;
}