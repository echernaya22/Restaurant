package DAO.Interfaces;

import Models.Dish;

import java.sql.SQLException;
import java.util.List;

public interface DishInterface extends CrudInterface<Dish> {
    public List<Dish> getByCategory(String str) throws SQLException;
}
