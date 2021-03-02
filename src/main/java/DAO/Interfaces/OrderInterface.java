package DAO.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface OrderInterface<T> {
    public long createOrder(T obj);
    public List<T> getAll();
    public double calculateCheckDepth();
    public double calculateAverageCheck();
    public T getById(long id) throws SQLException;
}
