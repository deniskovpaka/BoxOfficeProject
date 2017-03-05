package net.proselyte.boxoffice.model;

import net.proselyte.boxoffice.dao.Identification;
import net.proselyte.boxoffice.model.helper.Utils;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Seats class represents a set of seats for
 * a certain play.
 *
 * @author deniskovpaka
 */
public class Seats implements Identification<Integer> {
    final static Logger logger = Logger.getLogger(Seats.class.getName());
    private Boolean[] seatsList;
    private Integer id;
    private Integer playId;

    public Seats() {
        /**
         * The SEATS_SIZE should be synchronized
         * with {create_table.sql->seats.seats_list}.
         */
        final int SEATS_SIZE = 10;
        seatsList = new Boolean[SEATS_SIZE];
        Arrays.fill(seatsList, Boolean.FALSE);
    }

    /**
     * Returns the play's ID which associated
     * with available seats.
     */
    public Integer getPlayId() {
        return playId;
    }

    /**
     * Set the play's ID which associated
     * with available seats.
     */
    public void setPlayId(Integer playId) {
        this.playId = playId;
    }

    /**
     * Use this method to get seat
     * current state.
     * @param seatNumber place.
     * @return TRUE if the seat is reserved, FALSE otherwise.
     * NOTE: The FALSE will be return in case of incorrect seatNumber value.
     */
    public Boolean getSeatState(int seatNumber) {
        if (Utils.isSizeValueIncorrect(seatNumber)) {
            logger.log(Level.WARNING, "Return FALSE due to the seatNumber value" +
                    " was set incorrectly!!! \n");
            return Boolean.FALSE;
        }
        return seatsList[seatNumber];
    }

    /**
     * Use setSeatState method to set seat
     * current state.
     * @param seatNumber place.
     * @param state TRUE if the seat is reserved, FALSE otherwise.
     * NOTE: The seat state won't be set in case of incorrect seatNumber value.
     */
    public void setSeatState(int seatNumber, Boolean state) {
        if (Utils.isSizeValueIncorrect(seatNumber)) {
            logger.log(Level.WARNING, "The seatNumber value was set incorrectly!!!");
            return;
        }
        seatsList[seatNumber] = state;
    }

    /**
     * Set list of seats in the play.
     * @param seatsList list of seats.
     */
    public void setSeatsList(Boolean[] seatsList) {
        if (Utils.isSizeValueIncorrect(seatsList.length - 1)) {
            logger.log(Level.WARNING, "The seatsList.length value was set incorrectly!!!");
            return;
        }
        this.seatsList = seatsList;
    }

    /**
     * Returns list of all seats in the play.
     */
    public Boolean[] getSeatsList() {
        return seatsList;
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

    /**
     * Return TRUE if at least one seat is available (not reserved),
     * FALSE otherwise.
     */
    public boolean hasAvailableSeats() {
        for (Boolean isSeatReserved : seatsList) {
            if (!isSeatReserved) return true;
        }
        return false;
    }
}