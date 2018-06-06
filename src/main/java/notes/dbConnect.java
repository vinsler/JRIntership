package notes;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
    private Connection connection;

    public dbConnect() {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
        } catch (SQLException e) {
            System.err.println("Ошибка подключения драйвера!");
        }
    }

    public Connection getConnection(String url, String login, String password) {
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных!");
        }
        return connection;
    }
}
