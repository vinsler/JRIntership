package notes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Store {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String PROPERTIES = "src/main/resources/query.properties";

    public static void main(String[] args) {
    }

    public void insertNote(Note note, int id) throws SQLException {
        dbConnect dbconnect = new dbConnect();
        Connection connection = dbconnect.getConnection(URL, LOGIN, PASSWORD);
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(PROPERTIES));
        } catch (IOException e) {
            System.err.println("Can't find file Propeties!");
        }
        java.sql.Date sqlDate = new java.sql.Date(note.getCreateDate().getTime());

        PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("add"));
        preparedStatement.setInt(1, note.getId());
        preparedStatement.setString(2, note.getName());
        preparedStatement.setString(3, note.getDescription());
        preparedStatement.setDate(4, sqlDate);
        preparedStatement.setInt(5, note.getStatus());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
