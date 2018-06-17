package notes.dao;


import notes.dao.util.Connector;
import notes.exception.SqlAccessException;
import notes.model.Users;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class UsersStore implements Store<Users, Integer> {
    private static final UsersStore INSTANCE = new UsersStore();
    private static final Connector CONNECTOR = Connector.getInstance();
    private static final String PROPERTIES_URL = "src/main/resources/UsersQuery.properties";
    private static final Properties PROPERTIES = new Properties();
    private static final String SQL_ERR_MSG = "Error executing Users query!";

    static {
        try {
            PROPERTIES.load(new FileInputStream(PROPERTIES_URL));
        } catch (IOException e){
            throw new IllegalArgumentException("ERROR! Can't find file " + PROPERTIES_URL, e);
        }
    }

    private UsersStore(){}

    public static UsersStore getInstance(){
        return INSTANCE;
    }

    public void add(Users users) {
        try (Connection connection = CONNECTOR.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuery("add"))) {
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getLogin());
            preparedStatement.setString(3, users.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlAccessException(SQL_ERR_MSG, e);
        }
    }

    public void update(Users users, Integer id) {
        try (Connection connection = CONNECTOR.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(getQuery("update"))) {
            preparedStatement.setString(1, users.getName());
            preparedStatement.setString(2, users.getLogin());
            preparedStatement.setString(3, users.getPassword());
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

    public Users findOne(Integer id) {
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

    public ArrayList<Users> findAll (){
        ArrayList<Users> listUsers = new ArrayList<>();
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

    private Users constructUser(ResultSet resultSet) throws SQLException {
        Users users = new Users();
        users.setId(resultSet.getInt(1));
        users.setName(resultSet.getString(2));
        users.setLogin(resultSet.getString(3));
        users.setPassword(resultSet.getString(4));
        return users;
    }

    private String getQuery(String query){
        if (PROPERTIES.getProperty(query) == null) {
            throw new RuntimeException("ERROR! Can't find syntax - " + query);
        }
        return PROPERTIES.getProperty(query);
    }

}
