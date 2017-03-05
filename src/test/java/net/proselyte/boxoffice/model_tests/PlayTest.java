package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.Play;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * The PlayTest covers all Play's class
 * methods by unit tests.
 *
 * @author deniskovpaka
 */
@RunWith(PowerMockRunner.class)
public class PlayTest {
    private final Integer PLAY_ID   = 10;
    private final String PLAY_NAME  = "playName";
    private Play play;

    @Before
    public void setUp()
            throws Exception {
        play = new Play();
    }

    @Test
    public void shouldReturnProperPlayNameThatWasSetBefore()
            throws Exception {
        assertNotNull(play);
        play.setPlayName(PLAY_NAME);
        assertEquals(play.getPlayName(), PLAY_NAME);
    }

    @Test
    public void shouldReturnNullIfPlayNameWasNotSet()
            throws Exception {
        assertNotNull(play);
        assertNull(play.getPlayName());
    }

    @Test
    public void shouldReturnProperPlayIdThatWasSetBefore()
            throws Exception {
        assertNotNull(play);
        play.setId(PLAY_ID);
        assertEquals(play.getId(), PLAY_ID);
    }

    @Test
    public void shouldReturnNullIfPlayIdWasNotSet()
            throws Exception {
        assertNotNull(play);
        assertNull(play.getId());
    }
}