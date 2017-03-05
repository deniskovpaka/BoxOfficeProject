package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.Play;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

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
        assertThat(play, notNullValue());
        play.setPlayName(PLAY_NAME);
        assertThat(play.getPlayName(), is(PLAY_NAME));
    }

    @Test
    public void shouldReturnNullIfPlayNameWasNotSet()
            throws Exception {
        assertThat(play, notNullValue());
        assertThat(play.getPlayName(), nullValue());
    }

    @Test
    public void shouldReturnProperPlayIdThatWasSetBefore()
            throws Exception {
        assertThat(play, notNullValue());
        play.setId(PLAY_ID);
        assertThat(play.getId(), is(PLAY_ID));
    }

    @Test
    public void shouldReturnNullIfPlayIdWasNotSet()
            throws Exception {
        assertThat(play, notNullValue());
        assertThat(play.getId(), nullValue());
    }
}