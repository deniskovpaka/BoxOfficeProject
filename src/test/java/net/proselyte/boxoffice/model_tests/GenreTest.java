package net.proselyte.boxoffice.model_tests;

import net.proselyte.boxoffice.model.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

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
        assertThat(genre, notNullValue());
        genre.setGenreName(GENRE_NAME);
        assertThat(genre.getGenreName(), is(GENRE_NAME));
    }

    @Test
    public void shouldReturnNullIfGenreNameWasNotSet()
            throws Exception {
        assertThat(genre, notNullValue());
        assertThat(genre.getGenreName(), nullValue());
    }

    @Test
    public void shouldReturnProperGenreIdThatWasSetBefore()
            throws Exception {
        assertThat(genre, notNullValue());
        genre.setId(GENRE_ID);
        assertThat(genre.getId(), is(GENRE_ID));
    }

    @Test
    public void shouldReturnNullIfGenreIdWasNotSet()
            throws Exception {
        assertThat(genre, notNullValue());
        assertThat(genre.getId(), nullValue());
    }
}