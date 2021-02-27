package DAO.Implementations;

import DAO.Interfaces.CrudInterface;
import Models.Category;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAO implements CrudInterface<Category> {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";
    private static final Logger log = Logger.getLogger(CategoryDAO.class);
    public CategoryDAO() {

    }

    public Category getById(long id) throws SQLException {
        ResultSet resultSet = null;

        Category category = new Category();
        String sql = "select CategoryID, CategoryName from Category where CategoryID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)){
            prepStatement.setLong(1, id);
            resultSet = prepStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                category.setCategoryId(resultSet.getLong("CategoryID"));
                category.setName(resultSet.getString("CategoryName"));
            }


        } catch (SQLException e) {
            log.error("SQLException is caught in CategoryDAO.getById: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in CategoryDAO.getById: ", e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
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
                category.setCategoryId(resultSet.getLong("CategoryID"));
                category.setName(resultSet.getString("CategoryName"));

                allCategories.add(category);
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in CategoryDAO.getAll: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in CategoryDAO.getAll: ", e);
        }
        return allCategories;
    }

    public void create(Category category) {

        String sql = "insert into Category (CategoryName) values (?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            prepStatement.setString(1, category.getName());
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException is caught in CategoryDAO.create: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in CategoryDAO.create: ", e);
        }

    }

    public void update(Category category, long id){

        String sql = "update Category set CategoryName = ? where CategoryID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setString(1, category.getName());
            prepStatement.setLong(2, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException is caught in CategoryDAO.update: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in CategoryDAO.update: ", e);
        }
    }

    public void delete(long id){

        String sql = "delete from Category where CategoryID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            prepStatement.setLong(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException is caught in CategoryDAO.delete: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in CategoryDAO.delete: ", e);
        }
    }
}
