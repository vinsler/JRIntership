package notes.dao.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static final Connector CONNECTOR = new Connector();
    private static final Properties PROPERTIES = new Properties();

    private Connector() {}

    static  {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
            PROPERTIES.load(CONNECTOR.getClass().getClassLoader().getResourceAsStream("driver.properties"));
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Error connect driver!");
        }
    }

    public static Connector getInstance(){
        return CONNECTOR;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    PROPERTIES.getProperty("url"),
                    PROPERTIES.getProperty("login"),
                    PROPERTIES.getProperty("password")
            );
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connect to datebase!");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Error class mysql driver!");
        }
    }
}
