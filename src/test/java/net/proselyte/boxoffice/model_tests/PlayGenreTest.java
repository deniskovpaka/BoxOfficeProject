package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.PlayGenre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * The PlayGenreTest covers all PlayGenre's class
 * methods by unit tests.
 *
 * @author deniskovpaka
 */
@RunWith(PowerMockRunner.class)
public class PlayGenreTest {
    private final Integer ID        = 1;
    private final Integer GENRE_ID  = 2;
    private final Integer PLAY_ID   = 3;
    private PlayGenre playGenre;

    @Before
    public void setUp()
            throws Exception {
        playGenre = new PlayGenre();
    }

    @Test
    public void shouldReturnProperPlayGenreIdThatWasSetBefore()
            throws Exception {
        assertThat(playGenre, notNullValue());
        playGenre.setId(ID);
        assertThat(playGenre.getId(), is(ID));
    }

    @Test
    public void shouldReturnNullIfPlayGenreIdWasNotSet()
            throws Exception {
        assertThat(playGenre, notNullValue());
        assertThat(playGenre.getId(), nullValue());
    }

    @Test
    public void shouldReturnProperGenreIdFromPlayGenreInstanceThatWasSetBefore()
            throws Exception {
        assertThat(playGenre, notNullValue());
        playGenre.setGenreId(GENRE_ID);
        assertThat(playGenre.getGenreId(), is(GENRE_ID));
    }

    @Test
    public void shouldReturnGenreIdAsNullIfItWasNotSetToPlayGenreInstanceBefore()
            throws Exception {
        assertThat(playGenre, notNullValue());
        assertThat(playGenre.getGenreId(), nullValue());
    }

    @Test
    public void shouldReturnProperPlayIdFromPlayGenreInstanceThatWasSetBefore()
            throws Exception {
        assertThat(playGenre, notNullValue());
        playGenre.setPlayId(PLAY_ID);
        assertThat(playGenre.getPlayId(), is(PLAY_ID));
    }

    @Test
    public void shouldReturnPlayIdAsNullIfItWasNotSetToPlayGenreInstanceBefore()
            throws Exception {
        assertThat(playGenre, notNullValue());
        assertThat(playGenre.getPlayId(), nullValue());
    }
}