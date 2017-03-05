package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.Ticket;
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
import static org.hamcrest.core.IsNot.not;

/**
 * The TicketTest covers all Ticket's class
 * methods by unit tests.
 *
 * @author deniskovpaka
 */
@RunWith(PowerMockRunner.class)
public class TicketTest {
    private final Integer ID            = 1;
    private final Integer PLAY_ID       = 2;
    private final String VISITOR_NAME   = "visitorName";
    private Ticket ticket;

    @Before
    public void setUp()
            throws Exception {
        ticket = new Ticket();
    }

    @Test
    public void shouldReturnTicketNumbersArrayExactlyTheSameAsItWasSetBefore()
            throws Exception {
        assertThat(ticket, notNullValue());
        /** Get current array of ticket numbers from ticket */
        Integer[] ticketNumbers = ticket.getTicketNumber();
        assertThat(ticketNumbers.length, is(not(0)));

        /** Set numbers with random values */
        for (Integer ticket : ticketNumbers)
            ticket = new Random().nextInt(ticketNumbers.length);

        ticket.setTicketNumber(ticketNumbers);
        assertThat(ticket.getTicketNumber(), is(ticketNumbers));
    }

    @Test
    public void shouldReturnUnchangedTicketNumbersArrayInCaseOfIncorrectSizeOfTheNewTicketNumbersArray()
            throws Exception {
        assertThat(ticket, notNullValue());
        /** Create new ticket numbers array */
        Integer[] ticketNumbers = new Integer[ticket.getTicketNumber().length];

        /** Set numbers with random values */
        for (Integer ticket : ticketNumbers)
            ticket = new Random().nextInt(ticketNumbers.length);

        ticket.setTicketNumber(ticketNumbers);
        assertThat(ticket.getTicketNumber(), is(ticketNumbers));

        /** Made a new incorrect size of the copyTicketNumbers array */
        int copyTicketNumbersSize = ticketNumbers.length + 1 /** Extra number */;
        Integer[] newTicketNumbers = Arrays.copyOf(ticketNumbers,
                copyTicketNumbersSize);
        newTicketNumbers[copyTicketNumbersSize - 1] = 1 /** A random value*/;

        /** Try to set incorrect size of the ticket numbers array */
        ticket.setTicketNumber(newTicketNumbers);

        /** In that case the ticket numbers array shouldn't be changed */
        assertThat(ticket.getTicketNumber(), is(ticketNumbers));
    }

    @Test
    public void shouldReturnProperPlayIdThatWasSetBefore()
            throws Exception {
        assertThat(ticket, notNullValue());
        ticket.setPlayId(PLAY_ID);
        assertThat(ticket.getPlayId(), is(PLAY_ID));
    }

    @Test
    public void shouldReturnNullIfPlayIdWasNotSet()
            throws Exception {
        assertThat(ticket, notNullValue());
        assertThat(ticket.getPlayId(), nullValue());
    }

    @Test
    public void shouldReturnProperVisitorNameThatWasSetBefore()
            throws Exception {
        assertThat(ticket, notNullValue());
        ticket.setVisitorName(VISITOR_NAME);
        assertThat(ticket.getVisitorName(), is(VISITOR_NAME));
    }

    @Test
    public void shouldReturnNullIfVisitorNameWasNotSet()
            throws Exception {
        assertThat(ticket, notNullValue());
        assertThat(ticket.getVisitorName(), nullValue());
    }

    @Test
    public void shouldReturnProperTicketIdThatWasSetBefore()
            throws Exception {
        assertThat(ticket, notNullValue());
        ticket.setId(ID);
        assertThat(ticket.getId(), is(ID));
    }

    @Test
    public void shouldReturnNullIfTicketIdWasNotSet()
            throws Exception {
        assertThat(ticket, notNullValue());
        assertThat(ticket.getId(), nullValue());
    }
}