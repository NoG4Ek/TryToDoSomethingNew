package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.*;

/**
 * Getting DBConnection class, using attribute {@link Configs}
 */
public class DatabaseConnection extends Configs {
    static Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    private static DatabaseConnection instance;
    private Connection connection;
    private final String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" +
            dbName + "?allowPublicKeyRetrieval=true&useUnicode=true&serverTimezone=UTC&useSSL=false";

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, dbUser, dbPass);
        } catch (SQLException | ClassNotFoundException e) {
            logger.info("Problems with setup a DBConnection: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            logger.info("Problems with closing a DBConnection");
        }
    }

    /**
     * Singleton
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DatabaseConnection();
                }
            } catch (SQLException ignored) {
                // It's no use
            }
        }

        return instance;
    }
}
