package net.proselyte.boxoffice.model.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The DataBaseProperties class is responsible for
 * the creation of the connection to database.
 *
 * @author deniskovpaka
 */
public class DataBaseProperties {
    /**
     * Database properties.
     * The *DATABASE_PROPERTIES_FILE_NAME* file contains all
     * current properties which uses in order to connection
     * to the DB. It should be updated in case of using another database.
     */
    private final String DATABASE_PROPERTIES_FILE_NAME      = "database/database.properties";
    private final String DATABASE_USER_PROPERTIES           = "user";
    private final String DATABASE_PASSWORD_PROPERTIES       = "password";
    private final String DATABASE_URL_PROPERTIES            = "url";
    private final String DATABASE_DRIVER_PROPERTIES         = "driver";

    /**
     * The classes variables used to store
     * current database properties.
     * Applied for the establishing connection to DB.
     */
    private String dataBaseUser;
    private String dataPassword;
    private String dataBaseUrl;
    private String dataBaseDriver;

    /**
     * DataBaseProperties constructor.
     * Private constructor to restrict new instances.
     */
    private DataBaseProperties() {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream((DATABASE_PROPERTIES_FILE_NAME))) {
            Properties prop = new Properties();
            prop.load(in);

            dataBaseUser    = prop.getProperty(DATABASE_USER_PROPERTIES);
            dataPassword    = prop.getProperty(DATABASE_PASSWORD_PROPERTIES);
            dataBaseUrl     = prop.getProperty(DATABASE_URL_PROPERTIES);
            dataBaseDriver  = prop.getProperty(DATABASE_DRIVER_PROPERTIES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bill Pugh Solution for singleton pattern.
     */
    private static class Holder {
        private static final DataBaseProperties INSTANCE = new DataBaseProperties();
    }

    /**
     * Return DataBaseProperties instance.
     */
    public static DataBaseProperties getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Return database user.
     */
    public String getDataBaseUser() {
        return dataBaseUser;
    }

    /**
     * Return database password.
     */
    public String getDataBasePassword() {
        return dataPassword;
    }

    /**
     * Return database url.
     */
    public String getDataBaseUrl() {
        return dataBaseUrl;
    }

    /**
     * Return database driver.
     */
    public String getDataBaseDriver() {
        return dataBaseDriver;
    }
}