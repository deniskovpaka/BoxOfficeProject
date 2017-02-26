package net.proselyte.boxoffice.dao;


import net.proselyte.boxoffice.model.Seats;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The SeatsDao class is responsible
 * for interaction with seats table in DB.
 *
 * @author deniskovpaka
 */
public class SeatsDao extends AbstractDao<Seats, Integer>{
    /**
     * Constructor.
     * @param connection is a context.
     */
    public SeatsDao(Connection connection) {
        super(connection);
        setTableNameInDB("seats");
    }

    /**
     * See also the method {@link AbstractDao#getCreateQuery()}.
     */
    @Override
    String getCreateQuery() {
        return "INSERT INTO " + getTableNameInDB() + " (play_id, seats_list) \n" +
                "VALUES (?, ?);";
    }

    /**
     * See also the method {@link AbstractDao#getUpdateQuery()}.
     */
    @Override
    String getUpdateQuery() {
        return "UPDATE " + getTableNameInDB() + " SET play_id = ?, seats_list = ? \n" +
                "WHERE id = ?;";
    }

    /**
     * See also the method {@link AbstractDao#parseResultSet(ResultSet)}.
     */
    @Override
    List<Seats> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Seats> result = new LinkedList<Seats>();
        try {
            while (rs.next()) {
                Seats seat = new Seats();
                seat.setId(rs.getInt("id"));
                seat.setPlayId(rs.getInt("play_id"));
                Array booleanArray = rs.getArray("seats_list");
                Boolean[] seatsList = (Boolean[]) booleanArray.getArray();
                seat.setSeatsList(seatsList);
                result.add(seat);
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
                                   Seats seats) throws SQLException {
        try {
            int playId = (seats.getPlayId() == null) ? 0 : seats.getPlayId();
            statement.setInt(1, playId);
            Array seatsList = getConnection().createArrayOf("boolean", seats.getSeatsList());
            statement.setArray(2, seatsList);
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
                                   Seats seats) throws SQLException {
        try {
            statement.setInt(1, seats.getPlayId());
            Array seatsList = getConnection().createArrayOf("boolean", seats.getSeatsList());
            statement.setArray(2, seatsList);
            statement.setInt(3, seats.getId());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}