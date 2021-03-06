package DAO.Implementations;

import DAO.Interfaces.ClientInterface;
import Models.Client;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClientDAO implements ClientInterface{
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";
    private static final Logger log = Logger.getLogger(ClientDAO.class);
    public ClientDAO() {

    }

    public List<Client> getAll() {

        List<Client> allClients = new LinkedList<>();

        String sql = "select ClientID, Sirname, Name, PhoneNumber, Discount from Client";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             ResultSet resultSet = prepStatement.executeQuery()){


            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("ClientID"));
                client.setSurname(resultSet.getString("Sirname"));
                client.setName(resultSet.getString("Name"));
                client.setPhoneNumber(resultSet.getString("PhoneNumber"));
                client.setDiscount(resultSet.getDouble("Discount"));

                allClients.add(client);
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in ClientDAO.getAll: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in ClientDAO.getAll: ", e);
        }
        return allClients;
    }

    public Client getByPhoneNumber(String phoneNumber) throws SQLException {
        ResultSet resultSet = null;

        Client client = new Client();
        String sql = "select ClientID, Sirname, Name, PhoneNumber, Discount from Client where PhoneNumber = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)){
            prepStatement.setString(1, phoneNumber);
            resultSet = prepStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                client.setId(resultSet.getLong("ClientID"));
                client.setSurname(resultSet.getString("Sirname"));
                client.setName(resultSet.getString("Name"));
                client.setPhoneNumber(resultSet.getString("PhoneNumber"));
                client.setDiscount(resultSet.getDouble("Discount"));
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in ClientDAO.getByPhoneNumber: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in ClientDAO.getByPhoneNumber: ", e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return client;
    }

    public Client getById(long id) throws SQLException {
        ResultSet resultSet = null;

        Client client = new Client();
        String sql = "select ClientID, Sirname, Name, PhoneNumber, Discount from Client where ClientID = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)){
            prepStatement.setLong(1, id);
            resultSet = prepStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                client.setId(resultSet.getLong("ClientID"));
                client.setSurname(resultSet.getString("Sirname"));
                client.setName(resultSet.getString("Name"));
                client.setPhoneNumber(resultSet.getString("PhoneNumber"));
                client.setDiscount(resultSet.getDouble("Discount"));
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in ClientDAO.getById: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in ClientDAO.getById: ", e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return client;
    }

    public long create(Client client) {
        ResultSet resultSet;
        long primaryKey = 0;
        String sql = "insert into Client (Sirname, [Name], PhoneNumber, Discount) values (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prepStatement.setString(1, client.getSurname());
            prepStatement.setString(2, client.getName());
            prepStatement.setString(3, client.getPhoneNumber());
            prepStatement.setDouble(4, client.getDiscount());
            prepStatement.executeUpdate();

            resultSet = prepStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                primaryKey = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in ClientDAO.create: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in ClientDAO.create: ", e);
        }
        return primaryKey;
    }

    public void delete(long id) {

        String sql = "delete from Client where ClientID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setLong(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException is caught in ClientDAO.delete: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in ClientDAO.delete: ", e);
        }
    }

    public void update(Client client, long id) {

        String sql = "update Client set Sirname = ?, Name = ?, PhoneNumber = ?, Discount = ? where ClientID = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setString(1, client.getSurname());
            prepStatement.setString(2, client.getName());
            prepStatement.setString(3, client.getPhoneNumber());
            prepStatement.setDouble(4, client.getDiscount());
            prepStatement.setLong(5, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException is caught in ClientDAO.update: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in ClientDAO.update: ", e);
        }
    }
}
