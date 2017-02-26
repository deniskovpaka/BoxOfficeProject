package net.proselyte.boxoffice.dao;


import net.proselyte.boxoffice.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * The GenreDao class is responsible
 * for interaction with genre table in DB.
 *
 * @author deniskovpaka
 */
public class GenreDao extends AbstractDao<Genre, Integer> {
    /**
     * Constructor.
     * @param connection is a context.
     */
    public GenreDao(Connection connection) {
        super(connection);
        setTableNameInDB("genre");
    }

    /**
     * See also the method {@link AbstractDao#getCreateQuery()}.
     */
    @Override
    String getCreateQuery() {
        return "INSERT INTO " + getTableNameInDB() + " (genre_name) \n" +
                "VALUES (?);";
    }

    /**
     * See also the method {@link AbstractDao#getUpdateQuery()}.
     */
    @Override
    String getUpdateQuery() {
        return "UPDATE " + getTableNameInDB() + " SET genre_name = ? WHERE genre_id = ?;";
    }

    /**
     * See also the method {@link AbstractDao#parseResultSet(ResultSet)}.
     */
    @Override
    List<Genre> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Genre> result = new LinkedList<Genre>();
        try {
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                genre.setGenreName(rs.getString("genre_name"));
                result.add(genre);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return result;
    }

    /**
     * See also the method
     * {@link AbstractDao#prepareStatementForInsert(PreparedStatement, Identification)}.
     */
    @Override
    void prepareStatementForInsert(PreparedStatement statement,
                                   Genre genre) throws SQLException {
        try {
            statement.setString(1, genre.getGenreName());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * See also the method
     * {@link AbstractDao#prepareStatementForUpdate(PreparedStatement, Identification)}.
     */
    @Override
    void prepareStatementForUpdate(PreparedStatement statement,
                                   Genre genre) throws SQLException {
        try {
            statement.setString(1, genre.getGenreName());
            statement.setInt(2, genre.getId());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}