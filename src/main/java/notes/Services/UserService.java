package notes.Services;

import notes.dao.UsersStore;
import notes.exception.ResourceException;
import notes.exception.ValidationException;
import notes.exception.SqlAccessException;
import notes.model.Users;

import java.util.ArrayList;
import java.util.List;

// todo check exist table BEFORE use userstore !!!

public class UserService {
    private static final UsersStore USERSTORE = UsersStore.getInstance();
//    private static final String ERR_ID = "Service, check id!";
//    private static final String ERR_NAME = "Service, check name!";
//    private static final String ERR_LOGIN = "Service, check login!";
//    private static final String ERR_PASSWORD = "Service, check password!";
//    private static final String ERR_UPDATE_DELETE = "Service, check that the data for the actions is correct!";
//    private static final String ERR_NOT_FOUND = "Service, resources not found!";

    public void add(Users users){
        if (users.getId() == 0) {
            throw new ValidationException("ERR_ID");
        } else if (users.getName() == null) {
            throw new ValidationException("ERR_NAME");
        }  else if (users.getLogin() == null) {
            throw new ValidationException("ERR_LOGIN");
        } else if (users.getPassword() == null) {
            throw new ValidationException("ERR_PASSWORD");
        }
        try {
            USERSTORE.add(users);
        } catch (SqlAccessException e) {
            e.printStackTrace();
        }
    }

    public void update (Users users, Integer i){
        if (i == 0) {
            throw new ValidationException("ERR_UPDATE");
        }
        try {
            USERSTORE.update(users, i);
        } catch (SqlAccessException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer i){
        if (i == 0) {
            throw new ValidationException("ERR_DELETE");
        }
        try {
            USERSTORE.delete(i);
        } catch (SqlAccessException e) {
            e.printStackTrace();
        }
    }

    public Users findOne(Integer i){
        try {
            Users users = USERSTORE.findOne(i);
            if (users != null) {
                return users;
            }
            return null;
        } catch (SqlAccessException e) {
            e.printStackTrace();
            throw new ResourceException("ERR_NOT_FOUND");
        }
    }

    public List<Users> findAll(){
        List<Users> listUsers = new ArrayList<>();
        try {
            listUsers = USERSTORE.findAll();
            if (!listUsers.isEmpty()) {
                return listUsers;
            }
            return listUsers;
        } catch (SqlAccessException e) {
            e.printStackTrace();
            throw new ResourceException("ERR_NOT_FOUND");
        }
    }
}
