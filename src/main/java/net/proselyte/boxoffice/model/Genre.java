package net.proselyte.boxoffice.model;


import net.proselyte.boxoffice.dao.Identification;

/**
 * The Genre class represents a play genre.
 *
 * @author deniskovpaka
 */
public class Genre implements Identification<Integer> {
    private Integer id;
    private String genreName;

    /**
     * Returns a unique genre name.
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * Set a unique genre name.
     * @param genreName
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
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