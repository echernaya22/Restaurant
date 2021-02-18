package DAO.Implementations;

import DAO.Interfaces.DishInterface;
import Models.Category;
import Models.Dish;
import Models.Unit;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DishDAO implements DishInterface {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";

    public DishDAO() {

    }

    public List<Dish> getAll() throws SQLException {
        ResultSet resultSet = null;

        List<Dish> allDishes = new LinkedList<>();

        String sql = "select \n" +
                "\tds.[DishID], \n" +
                "\tds.[Name], \n" +
                "\tds.[CategoryID],\n" +
                "\tct.CategoryName,\n" +
                "\tds.[Price], \n" +
                "\tds.[Weight], \n" +
                "\tds.[UnitID],\n" +
                "\tut.UnitName\n" +
                "from Dish as ds\n" +
                "inner join Category as ct on ct.CategoryID = ds.CategoryID\n" +
                "inner join Unit as ut on ut.UnitID = ds.UnitID";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql);){

            resultSet = prepStatement.executeQuery();

            while (resultSet.next()) {
                Dish dish = new Dish();
                Unit unit = new Unit(resultSet.getString("UnitName"));
                Category category = new Category(resultSet.getString("CategoryName"));

                dish.setId(resultSet.getInt("DishID"));
                dish.setName(resultSet.getString("Name"));
                dish.setCategory(category);
                dish.setPrice(resultSet.getDouble("Price"));
                dish.setWeight(resultSet.getDouble("Weight"));
                dish.setUnit(unit);

                allDishes.add(dish);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
        }
        return allDishes;
    }

    public List<Dish> getByCategory(String categoryName) throws SQLException {
        ResultSet resultSet = null;

        List<Dish> allDishes = new LinkedList<>();

        String sql = "select \n" +
                "\tds.[DishID], \n" +
                "\tds.[Name], \n" +
                "\tds.[CategoryID],\n" +
                "\tct.CategoryName,\n" +
                "\tds.[Price], \n" +
                "\tds.[Weight], \n" +
                "\tds.[UnitID],\n" +
                "\tut.UnitName\n" +
                "from Dish as ds\n" +
                "inner join Category as ct on ct.CategoryID = ds.CategoryID\n" +
                "inner join Unit as ut on ut.UnitID = ds.UnitID\n" +
                "where CategoryName = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)){

            prepStatement.setString(1, categoryName);
            resultSet = prepStatement.executeQuery();

            while (resultSet.next()) {
                Dish dish = new Dish();
                Unit unit = new Unit(resultSet.getString("UnitName"));
                Category category = new Category(resultSet.getString("CategoryName"));

                dish.setId(resultSet.getInt("DishID"));
                dish.setName(resultSet.getString("Name"));
                dish.setCategory(category);
                dish.setPrice(resultSet.getDouble("Price"));
                dish.setWeight(resultSet.getDouble("Weight"));
                dish.setUnit(unit);

                allDishes.add(dish);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
        }
        return allDishes;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Dish getById(int id) throws SQLException {

        ResultSet resultSet = null;
        Dish dish = new Dish();

        String sql = "select ds.[DishID], ds.[Name], ds.[CategoryID], ct.CategoryName, ds.[Price], ds.[Weight], " +
                "ds.[UnitID], ut.UnitName from Dish as ds inner join Category as ct on " +
                "ct.CategoryID = ds.CategoryID inner join Unit as ut on ut.UnitID = ds.UnitID where DishID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)){

            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();

            resultSet.next();

            String str = resultSet.getString("UnitName");
            Unit unit = new Unit(resultSet.getString("UnitName"));
            Category category = new Category(resultSet.getString("CategoryName"));

            dish.setId(resultSet.getInt("DishID"));
            dish.setName(resultSet.getString("Name"));
            dish.setCategory(category);
            dish.setPrice(resultSet.getDouble("Price"));
            dish.setWeight(resultSet.getDouble("Weight"));
            dish.setUnit(unit);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
        }
        return dish;
    }

    public void delete(int dishId) {

        String sql = "delete from Dish where DishID = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setInt(1, dishId);
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void create(Dish dish) {

        String sql = "insert into Dish ([Name], [CategoryID], [Price], [Weight], [UnitID]) values(?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            Category category = dish.getCategory();
            Unit unit = dish.getUnit();

            prepStatement.setString(1, dish.getName());
            prepStatement.setInt(2, category.getCatergoryId());
            prepStatement.setDouble(3, dish.getPrice());
            prepStatement.setDouble(4, dish.getWeight());
            prepStatement.setInt(5, unit.getUnitId());
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Dish dish, int id) {

        String sql = "update Dish set [Name] = ?, [CategoryID] = ?, [Price] = ?, [Weight] = ?, [UnitID] = ? where [DishID] = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            Category category = dish.getCategory();
            Unit unit = dish.getUnit();

            prepStatement.setString(1, dish.getName());
            prepStatement.setInt(2, category.getCatergoryId());
            prepStatement.setDouble(3, dish.getPrice());
            prepStatement.setDouble(4, dish.getWeight());
            prepStatement.setInt(5, unit.getUnitId());
            prepStatement.setInt(6, id);
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
