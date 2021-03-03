package Services;

import Models.Client;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClientServiceTest {
    private Connection connection;
    private ClientService clientService = new ClientService();

    public ClientServiceTest() {

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
    public void getClientById() throws SQLException {
        Client client = new Client("First", "Client", "89011112233", 0);
        client.setId(1);
        Client expected = clientService.getById(1);

        Assert.assertEquals(client, expected);
    }

    @Test
    public void getAllClients() throws SQLException {
        Client client = new Client("First", "Client", "89011112233", 0);
        client.setId(1);
        Client client1 = new Client("Second", "Client", "89011332233", 0);
        client.setId(2);
        Client client2 = new Client("Third", "Client", "89911332233", 2);
        client.setId(3);
        Client client3 = new Client("Пупкин", "Вася", "88005553535", 3);
        client.setId(4);

        List<Client> clientList = new LinkedList<>();
        clientList.add(client);
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);

        List<Client> clientListFromDb = clientService.getAll();

        Assert.assertEquals(clientList, clientListFromDb);
    }

    @Test
    public void createClient() throws SQLException {
        Client client = new Client("TestSurname", "TestName", "1111111111", 9);
        long expectedKey = clientService.create(client);
        client.setId(expectedKey);

        Assert.assertEquals(client, clientService.getById(expectedKey));
        clientService.delete(expectedKey);
    }

    @Test
    public void deleteClient() throws SQLException {
        Client client = new Client("TestSurname", "TestName", "1111111111", 9);
        long expectedKey = clientService.create(client);
        clientService.delete(expectedKey);

        Client emptyClient = clientService.getById(expectedKey);

        Assert.assertEquals(0, emptyClient.getId());
    }

    @Test
    public void updateClient() throws SQLException {
        Client client = new Client("TestSurname", "TestName", "1111111111", 9);
        long expectedKey = clientService.create(client);

        Client newClientToUpdate = new Client("NewSurname", "NewName", "000000000", 7);
        newClientToUpdate.setId(expectedKey);

        clientService.update(newClientToUpdate, expectedKey);

        Assert.assertEquals(newClientToUpdate, clientService.getById(expectedKey));
        clientService.delete(expectedKey);
    }

    @Test
    public void getClientByPhoneNumber() throws SQLException {
        Client client = new Client("First", "Client", "89011112233", 0);
        client.setId(1);
        Client expected = clientService.getByPhoneNumber("89011112233");

        Assert.assertEquals(client, expected);
    }
}
