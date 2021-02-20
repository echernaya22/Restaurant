package DAO.Implementations;

import DAO.Interfaces.ClientInterface;
import Models.Client;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClientDAO implements ClientInterface {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";

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
                client.setId(resultSet.getInt("ClientID"));
                client.setSurname(resultSet.getString("Sirname"));
                client.setName(resultSet.getString("Name"));
                client.setPhoneNumber(resultSet.getString("PhoneNumber"));
                client.setDiscount(resultSet.getDouble("Discount"));

                allClients.add(client);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

            resultSet.next();

            client.setId(resultSet.getInt("ClientID"));
            client.setSurname(resultSet.getString("Sirname"));
            client.setName(resultSet.getString("Name"));
            client.setPhoneNumber(resultSet.getString("PhoneNumber"));
            client.setDiscount(resultSet.getDouble("Discount"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
        }
        return client;
    }

    public Client getById(int id) throws SQLException {
        ResultSet resultSet = null;

        Client client = new Client();
        String sql = "select ClientID, Sirname, Name, PhoneNumber, Discount from Client where ClientID = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)){
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();

            resultSet.next();

            client.setId(resultSet.getInt("ClientID"));
            client.setSurname(resultSet.getString("Sirname"));
            client.setName(resultSet.getString("Name"));
            client.setPhoneNumber(resultSet.getString("PhoneNumber"));
            client.setDiscount(resultSet.getDouble("Discount"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
        }
        return client;
    }

    public void create(Client client) {

        String sql = "insert into Client (Sirname, [Name], PhoneNumber, Discount) values (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {

            prepStatement.setString(1, client.getSurname());
            prepStatement.setString(2, client.getName());
            prepStatement.setString(3, client.getPhoneNumber());
            prepStatement.setDouble(4, client.getDiscount());
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(int id) {

        String sql = "delete from Client where ClientID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Client client, int id) {

        String sql = "update Client set Sirname = ?, Name = ?, PhoneNumber = ?, Discount = ? where ClientID = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setString(1, client.getSurname());
            prepStatement.setString(2, client.getName());
            prepStatement.setString(3, client.getPhoneNumber());
            prepStatement.setDouble(4, client.getDiscount());
            prepStatement.setInt(5, id);
            prepStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
