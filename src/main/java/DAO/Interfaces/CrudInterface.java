package DAO.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface CrudInterface<T> {
    public void create(T obj);
    public void update(T obj, long id);
    public void delete(long id);
    public T getById(long id) throws SQLException;
    public List<T> getAll() throws SQLException;
}
