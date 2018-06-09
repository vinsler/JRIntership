package notes.dao;

import notes.dao.util.Connector;
import notes.exception.SQLAccessException;
import notes.model.Note;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Store {
    private static final Store INSTANCE = new Store();
    private static final String PROPERTIES_URL = "src/main/resources/query.properties";
    private static final String SQL_ERROR_MSG = "Error executing query";
    private static final Connector CONNECTOR = Connector.getInstance();
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileInputStream(PROPERTIES_URL));
        } catch (IOException e) {
            throw new IllegalArgumentException("Error, can't find file PROPERTIES_URL!", e);
        }
    }

    private Store() {
    }

    private static Store getInstance() {
        return INSTANCE;
    }

    public void add(Note note) {
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("add"))) {
            Date date = new Date(note.getCreateDate().getTime());
            preparedStatement.setString(1, note.getName());
            preparedStatement.setString(2, note.getDescription());
            preparedStatement.setDate(3, date);
            preparedStatement.setInt(4, note.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLAccessException(SQL_ERROR_MSG, e);
        }
    }

    public void update(Note note, int id) {
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("update"))) {
            preparedStatement.setString(1, note.getName());
            preparedStatement.setString(2, note.getDescription());
            preparedStatement.setInt(3, note.getStatus());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLAccessException(SQL_ERROR_MSG, e);
        }
    }

    public void delete(int id) {
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("delete"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLAccessException(SQL_ERROR_MSG, e);
        }
    }

    public Note findOne(int id) {
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("findById"));
             ResultSet result = preparedStatement.executeQuery()) {
            preparedStatement.setInt(1, id);
            if (!result.next()) {
                return null;
            }
            return constructNote(result);
        } catch (SQLException e) {
            throw new SQLAccessException(SQL_ERROR_MSG, e);
        }
    }

    public List<Note> findAll() {
        List<Note> notes = new ArrayList<>();
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("findAll"));
             ResultSet result = preparedStatement.executeQuery()) {
            while (result.next()) {
                notes.add(constructNote(result));
            }
            return notes;
        } catch (SQLException e) {
            throw new SQLAccessException(SQL_ERROR_MSG, e);
        }
    }

    private Note constructNote(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        while (resultSet.next()) {
            note.setId(resultSet.getInt(1));
            note.setName(resultSet.getString(2));
            note.setDescription(resultSet.getString(3));
            note.setCreateDate(resultSet.getDate(4));
            note.setStatus(resultSet.getInt(5));
        }
        return note;
    }

    private String getQuery(String query) {
        if (PROPERTIES.getProperty(query) == null) {
            throw new SQLAccessException("Error, can't find query - " + query);
        }
        return PROPERTIES.getProperty(query);
    }
}
