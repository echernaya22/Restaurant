package DAO.Implementations;

import DAO.Interfaces.CrudInterface;
import Models.Category;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAO implements CrudInterface<Category> {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";

    public CategoryDAO() {

    }

    public Category getById(int id) throws SQLException {
        ResultSet resultSet = null;

        Category category = new Category();
        String sql = "select CategoryID, CategoryName from Category where CategoryID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)){
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();

            resultSet.next();
            category.setCatergoryId(resultSet.getInt("CategoryID"));
            category.setName(resultSet.getString("CategoryName"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
       }
        return category;
    }

    public List<Category> getAll() {

        List<Category> allCategories = new LinkedList<>();

        String sql = "select CategoryID, CategoryName from Category";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             ResultSet resultSet = prepStatement.executeQuery()){

            while (resultSet.next()) {
                Category category = new Category();
                category.setCatergoryId(resultSet.getInt("CategoryID"));
                category.setName(resultSet.getString("CategoryName"));

                allCategories.add(category);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCategories;
    }

    public void create(Category category) {

        String sql = "insert into Category (CategoryName) values (?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            prepStatement.setString(1, category.getName());
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Category category, int id){

        String sql = "update Category set CategoryName = ? where CategoryID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setString(1, category.getName());
            prepStatement.setInt(2, id);
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id){

        String sql = "delete from Category where CategoryID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
