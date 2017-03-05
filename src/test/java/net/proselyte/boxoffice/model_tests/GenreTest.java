package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * The GenreTest covers all Genre's class
 * methods by unit tests.
 *
 * @author deniskovpaka
 */
@RunWith(PowerMockRunner.class)
public class GenreTest {
    private final Integer GENRE_ID  = 10;
    private final String GENRE_NAME = "genreName";
    private Genre genre;

    @Before
    public void setUp()
            throws Exception {
        genre = new Genre();
    }

    @Test
    public void shouldReturnProperGenreNameThatWasSetBefore()
            throws Exception {
        assertNotNull(genre);
        genre.setGenreName(GENRE_NAME);
        assertEquals(genre.getGenreName(), GENRE_NAME);
    }

    @Test
    public void shouldReturnNullIfGenreNameWasNotSet()
            throws Exception {
        assertNotNull(genre);
        assertNull(genre.getGenreName());
    }

    @Test
    public void shouldReturnProperGenreIdThatWasSetBefore()
            throws Exception {
        assertNotNull(genre);
        genre.setId(GENRE_ID);
        assertEquals(genre.getId(), GENRE_ID);
    }

    @Test
    public void shouldReturnNullIfGenreIdWasNotSet()
            throws Exception {
        assertNotNull(genre);
        assertNull(genre.getId());
    }
}