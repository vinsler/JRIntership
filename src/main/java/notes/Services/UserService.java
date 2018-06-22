package notes.Services;

import notes.dao.Store;
import notes.dao.UsersStore;
import notes.exception.ValidationException;
import notes.model.User;


import java.util.List;

public class UserService {
    private static final Store USERSTORE = UsersStore.getInstance();

    public void add(User user){
        if (user.getName() == null) {
            throw new ValidationException("ERR_NAME");
        }  else if (user.getLogin() == null) {
            throw new ValidationException("ERR_LOGIN");
        } else if (user.getPassword() == null) {
            throw new ValidationException("ERR_PASSWORD");
        }
        USERSTORE.add(user);
    }

    public void update (User user, Integer i){
        if (user == null) {
            throw new ValidationException("ERR_USER_NOT_FOUND");
        } else if (i == null || i <= 0) {
            throw new ValidationException("ERR_UPDATE");
        }
        USERSTORE.update(user, i);
    }

    public void delete(Integer i){
        if (i == null || i <= 0) {
            throw new ValidationException("ERR_DELETE");
        }
        USERSTORE.delete(i);
    }

    public User findOne(Integer i){
        if (i == null || i <= 0) {
            throw new ValidationException("ERR_ID");
        }
        return (User) USERSTORE.findOne(i);
    }

    public List<User> findAll(){
        return USERSTORE.findAll();

    }
}
