package net.proselyte.boxoffice.dao;

import net.proselyte.boxoffice.model.PlayGenre;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The PlayGenreDao class is responsible
 * for interaction with play_genre table in DB.
 *
 * @author deniskovpaka
 */
public class PlayGenreDao extends AbstractDao<PlayGenre, Integer> {
    /**
     * Constructor.
     * @param connection is a context.
     */
    public PlayGenreDao(Connection connection) {
        super(connection);
        setTableNameInDB("play_genre");
    }

    /**
     * See also the method {@link AbstractDao#getCreateQuery()}.
     */
    @Override
    String getCreateQuery() {
        return "INSERT INTO " + getTableNameInDB() + " (play_id, genre_id) \n" +
                "VALUES (?, ?);";
    }

    /**
     * See also the method {@link AbstractDao#getUpdateQuery()}.
     */
    @Override
    String getUpdateQuery() {
        return "UPDATE " + getTableNameInDB() + " SET play_id = ?, genre_id = ? \n" +
                "WHERE id = ?;";
    }

    /**
     * See also the method {@link AbstractDao#parseResultSet(ResultSet)}.
     */
    @Override
    List<PlayGenre> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<PlayGenre> result = new LinkedList<PlayGenre>();
        try {
            while (rs.next()) {
                PlayGenre playGenre = new PlayGenre();
                playGenre.setId(rs.getInt("id"));
                playGenre.setPlayId(rs.getInt("play_id"));
                playGenre.setGenreId(rs.getInt("genre_id"));
                result.add(playGenre);
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
                                   PlayGenre playGenre) throws SQLException {
        try {
            int playId = (playGenre.getPlayId() == null) ? 0 : playGenre.getPlayId();
            int genreId = (playGenre.getGenreId() == null) ? 0 : playGenre.getGenreId();
            statement.setInt(1, playId);
            statement.setInt(2, genreId);
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
                                   PlayGenre playGenre) throws SQLException {
        try {
            statement.setInt(1, playGenre.getPlayId());
            statement.setInt(2, playGenre.getGenreId());
            statement.setInt(3, playGenre.getId());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}