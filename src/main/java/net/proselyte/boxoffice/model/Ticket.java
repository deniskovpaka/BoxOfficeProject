package net.proselyte.boxoffice.model;


import net.proselyte.boxoffice.dao.Identification;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Ticket class represents a ticket instance
 * related to Play and Visitor.
 *
 * @author deniskovpaka
 */
public class Ticket implements Identification<Integer> {
    final static Logger logger = Logger.getLogger(Ticket.class.getName());
    private Integer id;
    /**
     * The TICKETS_SIZE should be synchronized
     * with {create_table.sql->seats.seats_list && ticket.ticket_number}.
     */
    private final int TICKETS_SIZE = 10;
    private Integer ticketNumber[] = new Integer[TICKETS_SIZE];
    private Integer playId;
    private String visitorName;

    /**
     * Return a unique ticket number.
     */
    public Integer[] getTicketNumber() {
        return ticketNumber;
    }

    /**
     * Set a unique ticket number.
     */
    public void setTicketNumber(Integer[] ticketNumber) {
        if (Utils.isSizeValueIncorrect(ticketNumber.length - 1)) {
            logger.log(Level.WARNING, "The ticketNumber.length value was set incorrectly!!!");
            return;
        }
        this.ticketNumber = ticketNumber;
    }

    /**
     * Return a play Id related to this ticket.
     */
    public Integer getPlayId() {
        return playId;
    }

    /**
     * Set a play Id related to this ticket.
     */
    public void setPlayId(Integer playId) {
        this.playId = playId;
    }

    /**
     * Return a visitor(user) name related to this ticket.
     */
    public String getVisitorName() {
        return visitorName;
    }

    /**
     * Set a visitor(user) name related to this ticket.
     */
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    /**
     * See also the method {@link Identification#getId()}.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * See also the method {@link Identification#setId(Object)}.
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}