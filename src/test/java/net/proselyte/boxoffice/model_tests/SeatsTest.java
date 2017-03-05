package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.Seats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Random;

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
        assertNotNull(seats);
        seats.setId(ID);
        assertEquals(seats.getId(), ID);
    }

    @Test
    public void shouldReturnNullIfSeatsIdWasNotSet()
            throws Exception {
        assertNotNull(seats);
        assertNull(seats.getId());
    }

    @Test
    public void shouldReturnNotReservedStateForAllSeats()
            throws Exception {
        assertNotNull(seats);
        /**
         * All seat's state must be set to FALSE right
         * after Seats instance was created.
         */
        for (int seatPosition = 0; seatPosition < seats.getSeatsList().length;
             seatPosition++) {
            Boolean seatState = seats.getSeatState(seatPosition);
            assertFalse(seatState);
        }
    }

    @Test
    public void shouldReturnReservedStateForAllSeats()
            throws Exception {
        assertNotNull(seats);
        Boolean reservedSeatState = Boolean.TRUE;
        for (int seatPosition = 0; seatPosition < seats.getSeatsList().length;
             seatPosition++) {
            Boolean currentSeatState = seats.getSeatState(seatPosition);
            assertFalse(currentSeatState);

            /** Change current seat state to Reserved */
            seats.setSeatState(seatPosition, reservedSeatState);
            currentSeatState = seats.getSeatState(seatPosition);
            assertTrue(currentSeatState);
        }
    }

    @Test
    public void shouldReturnNotReservedStateInCaseOfIncorrectSeatNumber()
            throws Exception {
        assertNotNull(seats);
        int incorrectSeatNumber = seats.getSeatsList().length + 1 /** Extra number */;
        assertFalse(seats.getSeatState(incorrectSeatNumber));

        incorrectSeatNumber = -1 /** Set invalid seat number */;
        assertFalse(seats.getSeatState(incorrectSeatNumber));
    }

    @Test
    public void shouldReturnSeatsListAsItWasSetBefore()
            throws Exception {
        assertNotNull(seats);
        /** Get current SeatsList state */
        Boolean[] newSeatsList = seats.getSeatsList();

        /** Change the newSeatsList state */
        for (int i = 0; i < newSeatsList.length; i++)
            newSeatsList[i] = !newSeatsList[i];

        /** Set the newSeatsList state to seats */
        seats.setSeatsList(newSeatsList);
        for (int seatPosition = 0; seatPosition < seats.getSeatsList().length;
             seatPosition++)
            assertEquals(seats.getSeatState(seatPosition), newSeatsList[seatPosition]);
    }

    @Test
    public void shouldReturnUnchangedSeatsListInCaseOfIncorrectSizeOfTheNewSeatsList()
            throws Exception {
        assertNotNull(seats);
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
        assertNotNull(seats);

        /** Set all seats to reserved state */
        for (int i = 0; i < seats.getSeatsList().length; i++)
            seats.setSeatState(i, Boolean.TRUE);

        /** Set random seat number to available state */
        int randomSeatsNumber = new Random().nextInt(seats.getSeatsList().length);
        seats.setSeatState(randomSeatsNumber, Boolean.FALSE);

        assertTrue(seats.hasAvailableSeats());
    }
}