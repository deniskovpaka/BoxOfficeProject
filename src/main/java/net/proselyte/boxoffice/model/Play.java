package net.proselyte.boxoffice.model;

import net.proselyte.boxoffice.dao.Identification;

/**
 * The Play class represents a unique play.
 *
 * @author deniskovpaka
 */
public class Play implements Identification<Integer> {
    private Integer id;
    private String playName;

    /**
     * Returns a unique play name.
     */
    public String getPlayName() {
        return playName;
    }

    /**
     * Set a unique play name.
     * @param playName
     */
    public void setPlayName(String playName) {
        this.playName = playName;
    }

    /**
     * See also the method {@link Identification#getId()}.
     */
    @Override
    public Integer getId() {
        return null;
    }

    /**
     * See also the method {@link Identification#setId(Object)}.
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}