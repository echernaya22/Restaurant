package DAO.Interfaces;

import Models.Client;

import java.sql.SQLException;

public interface ClientInterface extends CrudInterface<Client> {
    public Client getByPhoneNumber(String str) throws SQLException;
}
