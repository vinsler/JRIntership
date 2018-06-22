package notes.dao;

import notes.dao.util.Connector;
import notes.exception.SqlAccessException;
import notes.model.Note;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NoteStore implements Store<Note, Integer>{
    private static final Store INSTANCE = new NoteStore();
    private static final String PROPERTIES_URL = "src/main/resources/NoteQuery.properties";
    private static final Connector CONNECTOR = Connector.getInstance();
    private static final Properties PROPERTIES = new Properties();
    private static final String SQL_ERR_MSG = "Error executing Note query!";

    static {
        try {
            PROPERTIES.load(new FileInputStream(PROPERTIES_URL));
        } catch (IOException e) {
            throw new IllegalArgumentException("ERROR! Can't find file " + PROPERTIES_URL, e);
        }
    }

    private NoteStore(){ }

    public static Store getInstance(){
        return INSTANCE;
    }

    public void add(Note note) {
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("add"))) {

            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            preparedStatement.setInt(1, note.getId());
            preparedStatement.setString(2, note.getName());
            preparedStatement.setString(3, note.getDescription());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setInt(5, note.getStatus());
            preparedStatement.setInt(6, note.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public void update(Note note, Integer id) {
        try (
        Connection connection = CONNECTOR.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery("update"))) {
            preparedStatement.setString(1, note.getName());
            preparedStatement.setString(2, note.getDescription());
            preparedStatement.setInt(3, note.getStatus());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public void delete(Integer id) {
        try (
        Connection connection = CONNECTOR.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery("delete"));
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public Note findOne(Integer id){
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("findById"))){
            preparedStatement.setInt(1, id);
            try (ResultSet result = preparedStatement.executeQuery()){
                if (!result.next()) {
                    return null;
                }
                else {
                    return constructNote(result);
                }
            }
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public List<Note> findAll(){
        List<Note> listNote = new ArrayList<>();
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("findAll"))){
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                listNote.add(constructNote(result));
            }
            return listNote;
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    private Note constructNote(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        note.setId(resultSet.getInt(1));
        note.setName(resultSet.getString(2));
        note.setDescription(resultSet.getString(3));
        note.setCreateDate(resultSet.getDate(4));
        note.setStatus(resultSet.getInt(5));
        return note;
    }

    private String getQuery(String query) {
        if (PROPERTIES.getProperty(query) == null) {
            throw new RuntimeException("Error, can't find syntax - " + query);
        }
        return PROPERTIES.getProperty(query);
    }
}