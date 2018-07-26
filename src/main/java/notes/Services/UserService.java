package notes.Services;

import notes.dao.Store;
import notes.dao.UserStore;
import notes.exception.ValidationException;
import notes.model.User;

import java.util.List;

public class UserService {
    private Store USERSTORE = UserStore.getInstance();

    public void add(User user){
        checkAddUpdate(user);
        USERSTORE.add(user);
//        if (USERSTORE.findLogin(user) == null) {
//            USERSTORE.add(user);
//        } else {
//            throw new ValidationException("ERR_LOGIN");
//        }
    }

    public void update (User user, Integer i) {
        checkAddUpdate(user);
        if (i == null || i <= 0) {
            throw new ValidationException("ERR_USER_ID");
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

    private void checkAddUpdate(User user){
        if (user == null) {
            throw new ValidationException("ERR_USER_NOT_FOUND");
        }else if (user.getName() == null || user.getName().isEmpty()) {
            throw new ValidationException("ERR_NAME");
        }  else if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("ERR_LOGIN");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidationException("ERR_PASSWORD");
        }
    }
}
