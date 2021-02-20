package Services;

import DAO.Implementations.DishDAO;
import Models.Dish;

import java.sql.SQLException;
import java.util.List;

public class DishService {
    private DishDAO dishDb = new DishDAO();

    public List<Dish> getAll() throws SQLException {
        return dishDb.getAll();
    }

    public List<Dish> getByCategory(String category) throws SQLException {
        return dishDb.getByCategory(category);
    }

    public void delete(int dishId) throws SQLException {
        dishDb.delete(dishId);
    }

    public void create(Dish dish) {
        dishDb.create(dish);
    }

    public void update(Dish dish, int id) throws SQLException {
        dishDb.update(dish, id);
    }

    public Dish getById(int id) throws SQLException {
        return dishDb.getById(id);
    }
}
