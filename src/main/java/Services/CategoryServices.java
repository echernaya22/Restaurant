package Services;

import DAO.Implementations.CategoryDAO;

import java.sql.SQLException;
import java.util.List;

public class CategoryServices {
    private CategoryDAO categoryDb = new CategoryDAO();

    public Category getById(int id) throws SQLException {
        return categoryDb.getById(id);
    }

    public List<Category> getAll() throws SQLException {
        return categoryDb.getAll();
    }

    public void create(Category category) throws SQLException {
        categoryDb.create(category);
    }

    public void update(Category category, int id) throws SQLException {
        categoryDb.update(category, id);
    }

    public void delete(int id) throws SQLException {
        categoryDb.delete(id);
    }
}
