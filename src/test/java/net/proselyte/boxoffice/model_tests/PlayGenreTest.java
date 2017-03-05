package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.PlayGenre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

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
        assertNotNull(playGenre);
        playGenre.setId(ID);
        assertEquals(playGenre.getId(), ID);
    }

    @Test
    public void shouldReturnNullIfPlayGenreIdWasNotSet()
            throws Exception {
        assertNotNull(playGenre);
        assertNull(playGenre.getId());
    }

    @Test
    public void shouldReturnProperGenreIdFromPlayGenreInstanceThatWasSetBefore()
            throws Exception {
        assertNotNull(playGenre);
        playGenre.setGenreId(GENRE_ID);
        assertEquals(playGenre.getGenreId(), GENRE_ID);
    }

    @Test
    public void shouldReturnGenreIdAsNullIfItWasNotSetToPlayGenreInstanceBefore()
            throws Exception {
        assertNotNull(playGenre);
        assertNull(playGenre.getGenreId());
    }

    @Test
    public void shouldReturnProperPlayIdFromPlayGenreInstanceThatWasSetBefore()
            throws Exception {
        assertNotNull(playGenre);
        playGenre.setPlayId(PLAY_ID);
        assertEquals(playGenre.getPlayId(), PLAY_ID);
    }

    @Test
    public void shouldReturnPlayIdAsNullIfItWasNotSetToPlayGenreInstanceBefore()
            throws Exception {
        assertNotNull(playGenre);
        assertNull(playGenre.getPlayId());
    }
}