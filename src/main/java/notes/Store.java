package notes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Store {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String PROPERTIES = "src/main/resources/query.properties";

    public static void main(String[] args) {
    }

    private Properties NewPropertiesTryLoadGetAction(String action){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(PROPERTIES));
            if (prop.getProperty(action) == null) {
                throw new Exception();
            }
            return prop;
        } catch (IOException e) {
            System.err.println("Can't find file PROPETIES!");
        } catch (Exception e) {
            System.err.println("Can't find syntax - " + action);
        }
        return null;
    }

    public void insertNote(Note note, int id) throws SQLException {
        Connection connection = new dbConnect().getConnection(URL, LOGIN, PASSWORD);
        Properties prop = NewPropertiesTryLoadGetAction("add");
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

    public void updateNote(Note note, int id) throws SQLException {
        Connection connection = new dbConnect().getConnection(URL, LOGIN, PASSWORD);
        Properties prop = NewPropertiesTryLoadGetAction("update");
        java.sql.Date sqlDate = new java.sql.Date(note.getCreateDate().getTime());
        PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("update"));

        preparedStatement.setString(2, note.getName());
        preparedStatement.setString(3, note.getDescription());
        preparedStatement.setDate(4, sqlDate);
        preparedStatement.setInt(5, note.getStatus());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public void deleteNote(Note note, int id) throws SQLException {
        Connection connection = new dbConnect().getConnection(URL, LOGIN, PASSWORD);
        Properties prop = NewPropertiesTryLoadGetAction("delete");
        PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("delete"));

        preparedStatement.setInt(1, note.getId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public void soutResult(ResultSet resultSet) throws SQLException {
        String.format("id : %d \t name : %s \n description : %s \n createDate : %s \t status : %s",
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5));
    }

    public void findNote(Note note, int id) throws SQLException {
        Connection connection = new dbConnect().getConnection(URL, LOGIN, PASSWORD);
        Properties prop = NewPropertiesTryLoadGetAction("findById");
        PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("findById"));

        preparedStatement.setInt(1, note.getId());
        ResultSet result = preparedStatement.executeQuery();
        if (result != null) {
            soutResult(result);
        }
        preparedStatement.close();
        connection.close();
    }

    public void findAllNote(Note note) throws SQLException {
        Connection connection = new dbConnect().getConnection(URL, LOGIN, PASSWORD);
        Properties prop = NewPropertiesTryLoadGetAction("findAll");
        PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("findAll"));

        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            soutResult(result);
        }

        preparedStatement.close();
        connection.close();
    }



}
