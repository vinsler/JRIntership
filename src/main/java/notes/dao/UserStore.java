package notes.dao;

import notes.dao.util.Connector;
import notes.exception.SqlAccessException;
import notes.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserStore implements Store<User, Integer> {
    private static final UserStore INSTANCE = new UserStore();
    private static final Connector CONNECTOR = Connector.getInstance();
    private static final String PROPERTIES_URL = "src/main/resources/UsersQuery.properties";
    private static final Properties PROPERTIES = new Properties();
    private static final String SQL_ERR_MSG = "Error executing User query!";

    static {
        try {
            PROPERTIES.load(new FileInputStream(PROPERTIES_URL));
        } catch (IOException e){
            throw new IllegalArgumentException("ERROR! Can't find file " + PROPERTIES_URL, e);
        }
    }

    private UserStore(){}

    public static UserStore getInstance(){
        return INSTANCE;
    }

    public void add(User user) {
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("add"))) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public void update(User user, Integer id) {
        try (Connection connection = CONNECTOR.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery("update"))) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public void delete(Integer id) {
        try (Connection connection = CONNECTOR.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery("delete"))) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public User findOne(Integer id) {
        try (Connection connection = CONNECTOR.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery("findById"))) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return constructUser(resultSet);
            }
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public List<User> findAll (){
        List<User> listUsers = new ArrayList<>();
        try (Connection connection = CONNECTOR.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery("findAll"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listUsers.add(constructUser(resultSet));
            }
            return listUsers;
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    private User constructUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setLogin(resultSet.getString(3));
        user.setPassword(resultSet.getString(4));
        return user;
    }

    private String getQuery(String query){
        if (PROPERTIES.getProperty(query) == null) {
            throw new RuntimeException("ERROR! Can't find syntax - " + query);
        }
        return PROPERTIES.getProperty(query);
    }
}
