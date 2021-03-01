package Services;

import Models.Category;
import Models.Dish;
import Models.Unit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DishServiceTest {
    private Connection connection;
    private DishService dishService = new DishService();
    private CategoryServices categoryServices = new CategoryServices();
    private UnitService unitService = new UnitService();

    public DishServiceTest() {

    }

    @BeforeClass
    public void before() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";
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
    public void getDishById() throws SQLException {
        Category category = categoryServices.getById(2);
        Unit unit = unitService.getById(1);
        Dish dish = new Dish("Summer Salad", category, 380, 300, unit);
        dish.setId(1);

        Dish expected = dishService.getById(1);

        Assert.assertEquals(dish, expected);
    }

    @Test
    public void getAllDishes() throws SQLException {
        Category category = categoryServices.getById(2);
        Unit unit = unitService.getById(1);
        Dish dish = new Dish("Summer Salad", category, 380, 300, unit);
        dish.setId(1);
        Dish dish1 = new Dish("Hawaiian Mini Pizza", category, 290, 290, unit);
        dish1.setId(2);
        Dish dish4 = new Dish("Chilli Crab Mini Pizza", category, 340, 300, unit);
        dish4.setId(2);
        Category category1 = categoryServices.getById(1);
        Dish dish2 = new Dish("Beef Burger", category1, 720, 590, unit);
        dish2.setId(4);
        Dish dish3 = new Dish("Feesh & Chips", category1, 530, 450, unit);
        dish3.setId(5);

        List<Dish> dishList = new LinkedList<>();
        dishList.add(dish);
        dishList.add(dish1);
        dishList.add(dish2);
        dishList.add(dish3);
        dishList.add(dish4);

        List<Dish> dishListFromDb = dishService.getAll();

        Assert.assertEquals(dishList, dishListFromDb);
    }

    @Test
    public void createDish() throws SQLException {
        Category category = categoryServices.getById(2);
        Unit unit = unitService.getById(1);
        Dish dish = new Dish("Test Dish", category, 100, 100, unit);
        long expectedKey = dishService.create(dish);
        dish.setId(expectedKey);
        Dish expected = dishService.getById(expectedKey);

        Assert.assertEquals(dish, expected);

        dishService.delete(expectedKey);
    }

    @Test
    public void deleteDish() throws SQLException {
        Category category = categoryServices.getById(2);
        Unit unit = unitService.getById(1);
        Dish dish = new Dish("Test Dish", category, 100, 100, unit);
        long expectedKey = dishService.create(dish);

        dishService.delete(expectedKey);
        Dish deletedDish = dishService.getById(expectedKey);

        Assert.assertEquals(deletedDish.getId(), 0);
    }

    @Test
    public void updateDish() throws SQLException {
        Category category = categoryServices.getById(2);
        Unit unit = unitService.getById(1);
        Dish dish = new Dish("Test Dish", category, 100, 100, unit);
        long expectedKey = dishService.create(dish);

        Dish newDishToUpdate = new Dish("New Name", category, 400, 200, unit);
        newDishToUpdate.setId(expectedKey);

        dishService.update(newDishToUpdate, expectedKey);

        Assert.assertEquals(newDishToUpdate, dishService.getById(expectedKey));

        dishService.delete(expectedKey);
    }

    @Test
    public void getDishesByCategory() throws SQLException {
        Unit unit = unitService.getById(1);
        Category category = categoryServices.getById(1);
        Dish dish1 = new Dish("Beef Burger", category, 720, 590, unit);
        dish1.setId(4);
        Dish dish2 = new Dish("Feesh & Chips", category, 530, 450, unit);
        dish2.setId(5);

        List<Dish> dishList = new LinkedList<>();
        dishList.add(dish1);
        dishList.add(dish2);

        List<Dish> dishListFromDb = dishService.getByCategory("mains");

        Assert.assertEquals(dishList, dishListFromDb);
    }

}
