package Services;

import DAO.Implementations.ClientDAO;
import Models.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private ClientDAO clientDb = new ClientDAO();


    public void create(Client client) {
        clientDb.create(client);
    }

    public List<Client> getAll() throws SQLException {
        return clientDb.getAll();
    }

    public Client getByPhoneNumber(String phoneNumber) throws SQLException {
        return clientDb.getByPhoneNumber(phoneNumber);
    }

    public void delete(int id) {
        clientDb.delete(id);
    }

    public void update(Client client, int id) {
        clientDb.update(client, id);
    }

    public Client getById(int id) throws SQLException {
        return clientDb.getById(id);
    }
}
