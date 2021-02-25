package DAO.Implementations;

import DAO.Interfaces.DishInterface;
import Models.Category;
import Models.Dish;
import Models.Unit;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DishDAO implements DishInterface {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";
    private static final Logger log = Logger.getLogger(DishDAO.class);
    public DishDAO() {

    }

    public List<Dish> getAll() {

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
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             ResultSet resultSet = prepStatement.executeQuery()){

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

        } catch (SQLException e) {
            log.error("SQLException is caught in DishDAO.getAll: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in DishDAO.getAll: ", e);
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

        } catch (SQLException e) {
            log.error("SQLException is caught in DishDAO.getByCategory: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in DishDAO.getByCategory: ", e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return allDishes;
    }


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

            if (resultSet != null && resultSet.next()) {

                Unit unit = new Unit(resultSet.getString("UnitName"));
                Category category = new Category(resultSet.getString("CategoryName"));

                dish.setId(resultSet.getInt("DishID"));
                dish.setName(resultSet.getString("Name"));
                dish.setCategory(category);
                dish.setPrice(resultSet.getDouble("Price"));
                dish.setWeight(resultSet.getDouble("Weight"));
                dish.setUnit(unit);
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in DishDAO.getById: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in DishDAO.getById: ", e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return dish;
    }

    public void delete(int dishId) {

        String sql = "delete from Dish where DishID = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setInt(1, dishId);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException is caught in DishDAO.delete: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in DishDAO.delete: ", e);
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

        } catch (SQLException e) {
            log.error("SQLException is caught in DishDAO.create: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in DishDAO.create: ", e);
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

        } catch (SQLException e) {
            log.error("SQLException is caught in DishDAO.update: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in DishDAO.update: ", e);
        }
    }


}
