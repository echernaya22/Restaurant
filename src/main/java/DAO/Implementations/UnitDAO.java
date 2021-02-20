package DAO.Implementations;

import DAO.Interfaces.CrudInterface;
import Models.Unit;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UnitDAO implements CrudInterface<Unit> {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";

    public UnitDAO() {

    }

    public Unit getById(int id) throws SQLException {

        ResultSet resultSet = null;

        Unit unit = new Unit();
        String sql = "select UnitID, UnitName from Unit where UnitID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql);){

            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();

            resultSet.next();
            unit.setUnitId(resultSet.getInt("UnitID"));
            unit.setName(resultSet.getString("UnitName"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
        }
        return unit;
    }

    public List<Unit> getAll() {

        List<Unit> allUnits = new LinkedList<>();

        String sql = "select UnitID, UnitName from Unit";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             ResultSet resultSet = prepStatement.executeQuery()){


            while (resultSet.next()) {
                Unit unit = new Unit();
                unit.setUnitId(resultSet.getInt("UnitID"));
                unit.setName(resultSet.getString("UnitName"));

                allUnits.add(unit);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allUnits;
    }

    public void create(Unit unit) {

        String sql = "insert into Unit (UnitName) values (?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            prepStatement.setString(1, unit.getName());
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Unit unit, int id) {

        String sql = "update Unit set UnitName = ? where UnitID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setString(1, unit.getName());
            prepStatement.setInt(2, id);
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {

        String sql = "delete from Unit where UnitID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
