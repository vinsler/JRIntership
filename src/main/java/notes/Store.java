package notes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Store {
    private static final String PROPERTIES = "src/main/resources/query.properties";
    private final Connector connector = Connector.getInstance();
    private final Properties prop = new Properties();

    {
        try {
            prop.load(new FileInputStream(PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException("Error, can't find file PROPERTIES!", e);
        }
    }

    public void insertNote(Note note, int id) throws SQLException {
        Properties prop = getQuery("add");
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("add"))) {
            java.sql.Date sqlDate = new java.sql.Date(note.getCreateDate().getTime());

            preparedStatement.setInt(1, note.getId());
            preparedStatement.setString(2, note.getName());
            preparedStatement.setString(3, note.getDescription());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setInt(5, note.getStatus());
            preparedStatement.executeUpdate();
        }
    }

    public void updateNote(Note note, int id) throws SQLException {
        Connection connection = connector.getConnection();
        Properties prop = getQuery("update");
        java.sql.Date sqlDate = new java.sql.Date(note.getCreateDate().getTime());
        PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("update"));

        preparedStatement.setString(2, note.getName());
        preparedStatement.setString(3, note.getDescription());
        preparedStatement.setInt(5, note.getStatus());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public void deleteNote(Note note, int id) throws SQLException {
        Connection connection = connector.getConnection();
        Properties prop = getQuery("delete");
        PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("delete"));

        preparedStatement.setInt(1, note.getId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public Note findNote(Note note, int id) throws SQLException { // return NULL || new Note
        Properties prop = getQuery("findById");
        try (Connection connection = connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("findById"))){
            preparedStatement.setInt(1, note.getId());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return saveToNote(result, note);
            } else {
                return null;
            }
        }
    }

    public ArrayList<Note> findAllNote(Note note) throws SQLException { // return Arraylist || NULL
        ArrayList<Note> arrListNote = new ArrayList<Note>();
        Properties prop = getQuery("findAll");
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(prop.getProperty("findAll"))){
            ResultSet result = preparedStatement.executeQuery();
            int num = 0;
            while (result.next()) {
                saveToNote(result, arrListNote.get(num));
                num++;
            }
            return arrListNote;
        }
    }

    private Note saveToNote(ResultSet resultSet, Note note) throws SQLException {
        while (resultSet.next()) {
            note.setId(resultSet.getInt(1));
            note.setName(resultSet.getString(2));
            note.setDescription(resultSet.getString(3));
            note.setCreateDate(resultSet.getDate(4));
            note.setStatus(resultSet.getInt(5));
        }
        return note;
    }

    private Properties getQuery(String query) {
        if (prop.getProperty(query) == null) {
            throw new RuntimeException("Error, can't find syntax - " + query);
        }
        return prop;
    }
}

// todo for view test
//        String.format("id : %d \t name : %s \n description : %s \n createDate : %s \t status : %s",
//                resultSet.getInt(1),
//                resultSet.getString(2),
//                resultSet.getString(3),
//                resultSet.getString(4),
//                resultSet.getString(5));
//    }