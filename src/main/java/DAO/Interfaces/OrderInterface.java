package DAO.Interfaces;

import java.util.List;

public interface OrderInterface<T> {
    public void createOrder(T obj);
    public List<T> getAll();
    public double calculateCheckDepth();
    public double calculateAverageCheck();
}
