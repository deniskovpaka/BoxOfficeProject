package net.proselyte.boxoffice.dao;

/**
 * The Identification interface uses to
 * set the instance ID.
 *
 * @author deniskovpaka
 */
public interface Identification<PrimaryKey> {
    /**
     * Return instance ID;
     */
    PrimaryKey getId();

    /**
     * Set instance ID.
     */
    void setId(PrimaryKey key);
}
