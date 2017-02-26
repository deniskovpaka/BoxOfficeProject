package net.proselyte.boxoffice.dao;

import net.proselyte.boxoffice.model.Ticket;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The TicketDao class is responsible
 * for interaction with ticket table in DB.
 *
 * @author deniskovpaka
 */
public class TicketDao extends AbstractDao<Ticket, Integer> {
    /**
     * Constructor.
     * @param connection is a context.
     */
    public TicketDao(Connection connection) {
        super(connection);
        setTableNameInDB("ticket");
    }

    /**
     * See also the method {@link AbstractDao#getCreateQuery()}.
     */
    @Override
    String getCreateQuery() {
        return "INSERT INTO " + getTableNameInDB() + " (ticket_number, play_id, visitor_name) \n" +
                "VALUES (?, ?, ?);";
    }

    /**
     * See also the method {@link AbstractDao#getUpdateQuery()}.
     */
    @Override
    String getUpdateQuery() {
        return "UPDATE " + getTableNameInDB() + " SET ticket_number = ?, play_id = ?\n" +
        ", visitor_name = ? WHERE id = ?;";
    }

    /**
     * See also the method {@link AbstractDao#parseResultSet(ResultSet)}.
     */
    @Override
    List<Ticket> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Ticket> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                Array intArr = rs.getArray("ticket_number");
                Integer[] ticketNumber = (Integer[]) intArr.getArray();
                ticket.setTicketNumber(ticketNumber);
                ticket.setPlayId(rs.getInt("play_id"));
                ticket.setVisitorName(rs.getString("visitor_name"));
                result.add(ticket);
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
                                   Ticket ticket) throws SQLException {
        try {
            Array ticketNumber = getConnection().createArrayOf("integer", ticket.getTicketNumber());
            statement.setArray(1, ticketNumber);
            statement.setInt(2, ticket.getPlayId());
            statement.setString(3, ticket.getVisitorName());
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
                                   Ticket ticket) throws SQLException {
        try {
            Array ticketNumber = getConnection().createArrayOf("integer", ticket.getTicketNumber());
            statement.setArray(1, ticketNumber);
            int playId = (ticket.getPlayId() == null) ? 0 : ticket.getPlayId();
            statement.setInt(2, playId);
            statement.setString(3, ticket.getVisitorName());
            statement.setInt(4, ticket.getId());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}