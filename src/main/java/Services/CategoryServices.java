package Services;

import DAO.Implementations.CategoryDAO;
import Models.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryServices {
    private CategoryDAO categoryDb = new CategoryDAO();

    public Category getById(long id) throws SQLException {
        return categoryDb.getById(id);
    }

    public List<Category> getAll() throws SQLException {
        return categoryDb.getAll();
    }

    public void create(Category category) throws SQLException {
        categoryDb.create(category);
    }

    public void update(Category category, long id) throws SQLException {
        categoryDb.update(category, id);
    }

    public void delete(long id) throws SQLException {
        categoryDb.delete(id);
    }
}
