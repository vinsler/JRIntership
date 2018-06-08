package notes;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final Connector connector = new Connector();

    private Connector() {}

    public static Connector getInstance() {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
            return connector;
        } catch (SQLException e) {
            System.err.println("Error connect driver!");
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error connect to datebase!");
            throw new RuntimeException();
        }
    }
}
