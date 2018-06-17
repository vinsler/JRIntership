package notes.Services;

import notes.dao.UsersStore;
import notes.model.Users;

import java.util.ArrayList;

public class UserService {
    private static final UsersStore userstore = UsersStore.getInstance();

    public void add(Users users){
        // check
        userstore.add(users);
        // check
    }

    public void update (Users users, Integer i){
        // check
        userstore.update(users, i);
        // check
    }

    public void delete(Integer i){
        // check
        userstore.delete(i);
        // check
    }

    public Users findOne(Integer i){
        Users users = new Users();
        // check
        userstore.findOne(i);
        // check
        return users;
    }

    public ArrayList<Users> findAll(){
        ArrayList<Users> aList = new ArrayList<>();
        // check
        aList = userstore.findAll();
        // check
        return aList;
    }


}
