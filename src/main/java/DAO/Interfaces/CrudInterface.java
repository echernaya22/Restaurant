package DAO.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface CrudInterface<T> {
    public void create(T obj);
    public void update(T obj, int id);
    public void delete(int id);
    public T getById(int id) throws SQLException;
    public List<T> getAll() throws SQLException;
}
