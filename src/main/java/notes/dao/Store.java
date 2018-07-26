package notes.dao;

import java.util.List;

public interface Store<T, ID> {
    void add(T t);
    void update(T t, ID i);
    void delete(ID i);
    T findOne(ID i);
    List<T> findAll();
    T findLogin(T t);
}
