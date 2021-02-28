package Services;

import DAO.Implementations.ClientDAO;
import Models.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private ClientDAO clientDb = new ClientDAO();


    public long create(Client client) {
        return clientDb.create(client);
    }

    public List<Client> getAll() throws SQLException {
        return clientDb.getAll();
    }

    public Client getByPhoneNumber(String phoneNumber) throws SQLException {
        return clientDb.getByPhoneNumber(phoneNumber);
    }

    public void delete(long id) {
        clientDb.delete(id);
    }

    public void update(Client client, long id) {
        clientDb.update(client, id);
    }

    public Client getById(long id) throws SQLException {
        return clientDb.getById(id);
    }
}
