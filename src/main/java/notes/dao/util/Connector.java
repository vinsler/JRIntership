package notes.dao.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static final String PROPERTIES_URL = "src/main/resources/driver.properties";
    private static final Properties PROPERTIES = new Properties();
    private static final Connector CONNECTOR = new Connector();

    static {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
            PROPERTIES.load(new FileInputStream(PROPERTIES_URL));
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Exception in initializer");
        }
    }

    private Connector() {
    }

    public static Connector getInstance() {
        return CONNECTOR;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    PROPERTIES.getProperty("url"),
                    PROPERTIES.getProperty("login"),
                    PROPERTIES.getProperty("password")
            );
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database!");
        }
    }
}
