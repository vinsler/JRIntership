package notes.dao;

import java.util.ArrayList;

public interface Store<T, ID> {
    public void add(T t);
    public void update(T t, ID i);
    public void delete(ID i);
    public T findOne(ID i);
    public ArrayList<T> findAll();
}
