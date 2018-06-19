package notes.Services;

import notes.dao.UsersStore;
import notes.exception.ServiceResourceException;
import notes.exception.ServiceValidationException;
import notes.exception.SqlAccessException;
import notes.model.Users;

import java.util.ArrayList;

// todo check exist table BEFORE use userstore !!!

public class UserService {
    private static final UsersStore USERSTORE = UsersStore.getInstance();
    private static final String ERR_ID = "Service, check id!";
    private static final String ERR_NAME = "Service, check name!";
    private static final String ERR_LOGIN = "Service, check login!";
    private static final String ERR_PASSWORD = "Service, check password!";
    private static final String ERR_UPDATE_DELETE = "Service, check that the data for the actions is correct!";
    private static final String ERR_NOT_FOUND = "Service, resources not found!";

    public void add(Users users){
        if (users.getId() == 0) {
            throw new ServiceValidationException(ERR_ID);
        } else if (users.getName() == null) {
            throw new ServiceValidationException(ERR_NAME);
        }  else if (users.getLogin() == null) {
            throw new ServiceValidationException(ERR_LOGIN);
        } else if (users.getPassword() == null) {
            throw new ServiceValidationException(ERR_PASSWORD);
        }
        try {
            USERSTORE.add(users);
        } catch (RuntimeException e) {
            throw new SqlAccessException(e.toString());
        }
    }

    public void update (Users users, Integer i){
        if (i == 0) {
            throw new ServiceValidationException(ERR_UPDATE_DELETE);
        }
        try {
            USERSTORE.update(users, i);
        } catch (RuntimeException e) {
            throw new SqlAccessException(e.toString());
        }
    }

    public void delete(Integer i){
        if (i == 0) {
            throw new ServiceValidationException(ERR_UPDATE_DELETE);
        }
        try {
            USERSTORE.delete(i);
        } catch (RuntimeException e) {
            throw new SqlAccessException(e.toString());
        }
    }

    public Users findOne(Integer i){
        Users users = USERSTORE.findOne(i);
        if (users != null) {
            return users;
        }
        throw new ServiceResourceException(ERR_NOT_FOUND);
    }

    public ArrayList<Users> findAll(){
        ArrayList<Users> listUsers = new ArrayList<>();
        listUsers = USERSTORE.findAll();
        if (!listUsers.isEmpty()) {
            return listUsers;
        }
        throw new ServiceResourceException(ERR_NOT_FOUND);
    }
}
