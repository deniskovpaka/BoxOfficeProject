package net.proselyte.boxoffice.dao;

import java.sql.*;
import java.util.List;

/**
 * The AbstractDao class represents base CRUD operations.
 *
 * @author deniskovpaka
 */
public abstract class AbstractDao<T extends Identification<PrimaryKey>,
        PrimaryKey extends Integer> implements GenericDao<T, PrimaryKey> {
    /**
     * Connection represents the context in JDBC.
     */
    private Connection connection;
    /**
     * The table name in Data Base.
     */
    private String tableNameInDB;

    /**
     * AbstractDao constructor.
     * @param connection a context.
     */
    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Return a sql request in order to insert a new record.
     * Example: INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    abstract String getCreateQuery();

    /**
     * Return a sql request in order to update a record.
     * Example: UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    abstract String getUpdateQuery();

    /**
     * Return a list of objects corresponding to the ResultSet.
     * @param rs set of unique data.
     * @throws SQLException during parsing of the ResultSet.
     */
    abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    /**
     * Construct INSERT statement for the specific object.
     * @param statement
     * @param object specific object.
     * @throws SQLException during construction INSERT statement.
     */
    abstract void prepareStatementForInsert(PreparedStatement statement,
                                            T object) throws SQLException;

    /**
     * Construct UPDATE statement for the specific object.
     * @param statement
     * @param object specific object.
     * @throws SQLException during construction UPDATE statement.
     */
    abstract void prepareStatementForUpdate(PreparedStatement statement,
                                            T object) throws SQLException;

    /**
     * Return a sql request in order to get all records.
     * Example: SELECT * FROM [Table];
     */
    protected String getSelectQuery() {
        return "SELECT * FROM " + getTableNameInDB() + " ";
    }

    /**
     * Return a sql request in order to delete record in DB.
     * Example: DELETE FROM [Table] WHERE id = ?;
     */
    protected String getDeleteQuery() {
        return "DELETE FROM " + getTableNameInDB() + " WHERE id = ?;";
    }

    /**
     * Set the unique table name which is associated
     * with this DAO instance.
     * @param tableNameInDB
     */
    protected void setTableNameInDB(String tableNameInDB) {
        this.tableNameInDB = tableNameInDB;
    }

    /**
     * Return unique table name associated
     * with this DAO instance.
     */
    protected String getTableNameInDB() {
        return tableNameInDB;
    }

    /**
     * See also the method {@link GenericDao#persist(Identification)}.
     */
    @Override
    public T persist(T object) throws SQLException {
        T persistInstance;
        String sql = getCreateQuery();
        Integer lastInsertedId = -1;
        try (PreparedStatement statement = connection.prepareStatement(sql,
                                            Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On persist modify more then 1 record: " + count);
            }
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                lastInsertedId = rs.getInt(1);
            } else
                throw new SQLException("Can't get the last inserted Id in table.");
        } catch (Exception e) {
            throw new SQLException(e);
        }
        sql = getSelectQuery() + " WHERE id = " + lastInsertedId + ";";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new SQLException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return persistInstance;
    }

    /**
     * See also the method {@link GenericDao#getByPrimaryKey(Object)}.
     */
    @Override
    public T getByPrimaryKey(Integer key) throws SQLException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        if (list == null || list.size() == 0) {
            throw new SQLException("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new SQLException("Received more than one record.");
        }
        return list.iterator().next();
    }

    /**
     * See also the method {@link GenericDao#update(Identification)}.
     */
    @Override
    public void update(T object) throws SQLException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * See also the method {@link GenericDao#delete(Identification)}.
     */
    @Override
    public void delete(T object) throws SQLException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new SQLException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * See also the method {@link GenericDao#getAll(String)}.
     */
    @Override
    public List<T> getAll(String sqlQuery) throws SQLException {
        List<T> list;
        String sql = getSelectQuery() + sqlQuery + ";";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return list;
    }

    /**
     * See also the method {@link GenericDao#getAll()}.
     */
    @Override
    public List<T> getAll() throws SQLException {
        return getAll("" /*Add nothing in order to get Object's list by default query*/);
    }

    /**
     * Returns connection which in JDBC is a context.
     */
    protected Connection getConnection() {
        return connection;
    }
}