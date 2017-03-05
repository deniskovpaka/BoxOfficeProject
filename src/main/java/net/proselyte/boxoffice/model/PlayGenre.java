package net.proselyte.boxoffice.model;


import net.proselyte.boxoffice.dao.Identification;

/**
 * The PlayGenre class represents a map
 * between play and genre.
 *
 * @author deniskovpaka
 */
public class PlayGenre implements Identification<Integer> {
    private Integer id;
    private Integer playId;
    private Integer genreId;

    /**
     * Return a unique play id.
     */
    public Integer getPlayId() {
        return playId;
    }

    /**
     * Set a unique play id.
     * @param playId
     */
    public void setPlayId(Integer playId) {
        this.playId = playId;
    }

    /**
     * Return a unique genre id.
     */
    public Integer getGenreId() {
        return genreId;
    }

    /**
     * Set a unique genre id.
     * @param genreId
     */
    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
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
}