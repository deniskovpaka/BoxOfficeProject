package net.proselyte.boxoffice.dao;

import net.proselyte.boxoffice.model.Play;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * The PlayDao class is responsible
 * for interaction with play table in DB.
 *
 * @author deniskovpaka
 */
public class PlayDao extends AbstractDao<Play, Integer> {
    /**
     * Constructor.
     * @param connection is a context.
     */
    public PlayDao(Connection connection) {
        super(connection);
        setTableNameInDB("play");
    }

    /**
     * See also the method {@link AbstractDao#getCreateQuery()}.
     */
    @Override
    String getCreateQuery() {
        return "INSERT INTO " + getTableNameInDB() + " (play_name) \n" +
                "VALUES (?);";
    }

    /**
     * See also the method {@link AbstractDao#getUpdateQuery()}.
     */
    @Override
    String getUpdateQuery() {
        return "UPDATE " + getTableNameInDB() + " SET play_name = ? WHERE play_id = ?;";
    }

    /**
     * See also the method {@link AbstractDao#parseResultSet(ResultSet)}.
     */
    @Override
    protected List<Play> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Play> result = new LinkedList<Play>();
        try {
            while (rs.next()) {
                Play play = new Play();
                play.setId(rs.getInt("id"));
                play.setPlayName(rs.getString("play_name"));
                result.add(play);
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
    protected void prepareStatementForInsert(PreparedStatement statement,
                                             Play play) throws SQLException {
        try {
            statement.setString(1, play.getPlayName());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * See also the method
     * {@link AbstractDao#prepareStatementForUpdate(PreparedStatement, Identification)}.
     */
    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement,
                                             Play play) throws SQLException {
        try {
            statement.setString(1, play.getPlayName());
            statement.setInt(2, play.getId());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}