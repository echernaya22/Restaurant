package Services;

import DAO.Implementations.CategoryDAO;
import Models.Category;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.*;

public class CategoryServiceTest {

    private Connection connection;
    private CategoryServices categoryServices = new CategoryServices();

    public CategoryServiceTest() {
    }

    @BeforeClass
    public void before() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=RestaurantTest;user=admin;password=12345";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCategoryById() throws SQLException {
        Category category = new Category();
        category.setCategoryId(1);
        category.setName("mains");
        Category expected = categoryServices.getById(1);

        Assert.assertEquals(category, expected);
    }

    @Test
    public void getCategoryByIdWhichNotExist() throws SQLException {
        Category category = new Category();
        Category expected = categoryServices.getById(9);

        Assert.assertEquals(category, expected);
    }


    @Test
    public void getAllCategories() throws SQLException {
        Category category1 = new Category();
        category1.setCategoryId(1);
        category1.setName("mains");

        Category category2 = new Category();
        category2.setCategoryId(2);
        category2.setName("starters");

        Category category3 = new Category();
        category3.setCategoryId(3);
        category3.setName("desserts");

        Category category4 = new Category();
        category4.setCategoryId(4);
        category4.setName("drinks");

        List<Category> allCategoriesFromDb = categoryServices.getAll();
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);


        Assert.assertEquals(allCategoriesFromDb, categoryList);
    }

    @Test
    public void createCategory() throws SQLException {
        Category category1 = new Category();
        category1.setName("testCategory");
        long expectedKey = categoryServices.create(category1);
        category1.setCategoryId(expectedKey);

        Assert.assertEquals(category1, categoryServices.getById(expectedKey));

        categoryServices.delete(expectedKey);
    }

    @Test
    public void deleteCategory() throws SQLException {
        Category category1 = new Category();
        category1.setName("testCategory");
        long expectedKey = categoryServices.create(category1);

        categoryServices.delete(expectedKey);
        Category category = categoryServices.getById(expectedKey);

        Assert.assertEquals(category.getCategoryId(), 0);
    }

    @Test
    public void updateCategory() throws SQLException {
        Category category1 = new Category();
        category1.setName("testCategory");
        long expectedKey = categoryServices.create(category1);
        Category newCategoryToUpdate = new Category();
        newCategoryToUpdate.setName("new name");
        newCategoryToUpdate.setCategoryId(expectedKey);

        categoryServices.update(newCategoryToUpdate, expectedKey);

        Assert.assertEquals(newCategoryToUpdate, categoryServices.getById(expectedKey));

        categoryServices.delete(expectedKey);
    }






}
