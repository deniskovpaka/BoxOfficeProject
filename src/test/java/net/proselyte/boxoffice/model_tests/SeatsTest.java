package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.Seats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * The SeatsTest covers all Seats's class
 * methods by unit tests.
 *
 * @author deniskovpaka
 */
@RunWith(PowerMockRunner.class)
public class SeatsTest {
    private final Integer ID        = 1;
    private final Integer PLAY_ID   = 2;
    private Seats seats;

    @Before
    public void setUp()
            throws Exception {
        seats = new Seats();
    }

    @Test
    public void shouldReturnProperSeatsIdThatWasSetBefore()
            throws Exception {
        assertThat(seats, notNullValue());
        seats.setId(ID);
        assertThat(seats.getId(), is(ID));
    }

    @Test
    public void shouldReturnNullIfSeatsIdWasNotSet()
            throws Exception {
        assertThat(seats, notNullValue());
        assertThat(seats.getId(), nullValue());
    }

    @Test
    public void shouldReturnNotReservedStateForAllSeats()
            throws Exception {
        assertThat(seats, notNullValue());
        /**
         * All seat's state must be set to FALSE right
         * after Seats instance was created.
         */
        for (int seatPosition = 0; seatPosition < seats.getSeatsList().length;
             seatPosition++) {
            Boolean seatState = seats.getSeatState(seatPosition);
            assertThat(seatState, is(false));
        }
    }

    @Test
    public void shouldReturnReservedStateForAllSeats()
            throws Exception {
        assertThat(seats, notNullValue());
        Boolean reservedSeatState = Boolean.TRUE;
        for (int seatPosition = 0; seatPosition < seats.getSeatsList().length;
             seatPosition++) {
            Boolean currentSeatState = seats.getSeatState(seatPosition);
            assertThat(currentSeatState, is(false));

            /** Change current seat state to Reserved */
            seats.setSeatState(seatPosition, reservedSeatState);
            currentSeatState = seats.getSeatState(seatPosition);
            assertThat(currentSeatState, is(true));
        }
    }

    @Test
    public void shouldReturnNotReservedStateInCaseOfIncorrectSeatNumber()
            throws Exception {
        assertThat(seats, notNullValue());
        int incorrectSeatNumber = seats.getSeatsList().length + 1 /** Extra number */;
        assertThat(seats.getSeatState(incorrectSeatNumber), is(false));

        incorrectSeatNumber = -1 /** Set invalid seat number */;
        assertThat(seats.getSeatState(incorrectSeatNumber), is(false));
    }

    @Test
    public void shouldReturnSeatsListAsItWasSetBefore()
            throws Exception {
        assertThat(seats, notNullValue());
        /** Get current SeatsList state */
        Boolean[] newSeatsList = seats.getSeatsList();

        /** Change the newSeatsList state */
        for (int i = 0; i < newSeatsList.length; i++)
            newSeatsList[i] = !newSeatsList[i];

        /** Set the newSeatsList state to seats */
        seats.setSeatsList(newSeatsList);
        for (int seatPosition = 0; seatPosition < seats.getSeatsList().length;
             seatPosition++)
            assertThat(seats.getSeatState(seatPosition), is(newSeatsList[seatPosition]));
    }

    @Test
    public void shouldReturnUnchangedSeatsListInCaseOfIncorrectSizeOfTheNewSeatsList()
            throws Exception {
        assertThat(seats, notNullValue());
        /** Get current SeatsList state */
        Boolean[] currentSeatsList = seats.getSeatsList();

        /** Made a new incorrect size of the SeatsList states */
        int copyOfCurrentSeatsListSize = currentSeatsList.length + 1 /** Extra number */;
        Boolean[] copyOfCurrentSeatsList = Arrays.copyOf(currentSeatsList,
                                                copyOfCurrentSeatsListSize);
        copyOfCurrentSeatsList[copyOfCurrentSeatsListSize - 1] = Boolean.FALSE;

        /** Change SeatsList states */
        for (int i = 0; i < copyOfCurrentSeatsList.length; i++) {
            copyOfCurrentSeatsList[i] = !copyOfCurrentSeatsList[i];
        }

        /** Try to set incorrect size of the SeatsList states */
        seats.setSeatsList(copyOfCurrentSeatsList);

        /** In that case the SeatsList shouldn't be changed */
        currentSeatsList = seats.getSeatsList();
        int pos = 0;
        for (Boolean currentState : currentSeatsList)
            assertNotEquals(currentState, copyOfCurrentSeatsList[pos++]);
    }

    @Test
    public void shouldReturnTrueIfAtLeastOneSeatIsAvailable()
            throws Exception {
        assertThat(seats, notNullValue());

        /** Set all seats to reserved state */
        for (int i = 0; i < seats.getSeatsList().length; i++)
            seats.setSeatState(i, Boolean.TRUE);

        /** Set random seat number to available state */
        int randomSeatsNumber = new Random().nextInt(seats.getSeatsList().length);
        seats.setSeatState(randomSeatsNumber, Boolean.FALSE);

        assertThat(seats.hasAvailableSeats(), is(true));
    }

    @Test
    public void shouldReturnProperPlayIdThatWasSetBefore()
            throws Exception {
        assertThat(seats, notNullValue());
        seats.setPlayId(PLAY_ID);
        assertThat(seats.getPlayId(), is(PLAY_ID));
    }

    @Test
    public void shouldReturnNullIfPlayIdWasNotSet()
            throws Exception {
        assertThat(seats, notNullValue());
        assertThat(seats.getPlayId(), nullValue());
    }
}