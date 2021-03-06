package DAO.Implementations;

import DAO.Interfaces.CrudInterface;
import Models.Unit;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UnitDAO implements CrudInterface<Unit> {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";
    private static final Logger log = Logger.getLogger(UnitDAO.class);

    public UnitDAO() {

    }

    public Unit getById(long id) throws SQLException {

        ResultSet resultSet = null;

        Unit unit = new Unit();
        String sql = "select UnitID, UnitName from Unit where UnitID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql);){

            prepStatement.setLong(1, id);
            resultSet = prepStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                unit.setUnitId(resultSet.getLong("UnitID"));
                unit.setName(resultSet.getString("UnitName"));
            }


        } catch (SQLException e) {
            log.error("SQLException is caught in UnitDAO.getById: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in UnitDAO.getById: ", e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
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
                unit.setUnitId(resultSet.getLong("UnitID"));
                unit.setName(resultSet.getString("UnitName"));

                allUnits.add(unit);
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in UnitDAO.getAll: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in UnitDAO.getAll: ", e);
        }
        return allUnits;
    }

    public long create(Unit unit) {
        ResultSet resultSet;
        long primaryKey = 0;
        String sql = "insert into Unit (UnitName) values (?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prepStatement.setString(1, unit.getName());
            prepStatement.executeUpdate();
            resultSet = prepStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                primaryKey = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in UnitDAO.create: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in UnitDAO.create: ", e);
        }

        return primaryKey;
    }

    public void update(Unit unit, long id) {

        String sql = "update Unit set UnitName = ? where UnitID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setString(1, unit.getName());
            prepStatement.setLong(2, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException is caught in UnitDAO.update: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in UnitDAO.update: ", e);
        }
    }

    public void delete(long id) {

        String sql = "delete from Unit where UnitID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            prepStatement.setLong(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException is caught in UnitDAO.delete: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in UnitDAO.delete: ", e);
        }
    }
}
