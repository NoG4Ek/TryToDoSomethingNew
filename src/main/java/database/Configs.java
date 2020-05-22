package database;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Attribute class for connecting to the database
 */
public class Configs {
    private final ResourceBundle configs = ResourceBundle.getBundle("properties/dbConfigs", Locale.getDefault());
    protected String dbHost = configs.getString("host");
    protected String dbPort = configs.getString("port");
    protected String dbUser = configs.getString("user");
    protected String dbPass = configs.getString("pass");
    protected String dbName = configs.getString("name");
}
