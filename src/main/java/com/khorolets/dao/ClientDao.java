package com.khorolets.dao;

import com.khorolets.domain.Client;

import java.util.List;

public interface ClientDao {

    /**
     * Save client
     *
     * @param client Client object that should be saved ;
     * @return ClientId if client was saved;
     **/
    long saveClient(Client client);

    /**
     * Modify client by id
     *
     * @param id       client id;
     * @param newName  new client name ;
     * @param newAge   new client phone ;
     * @param newEmail new client phone ;
     **/
    void editClient(long id, String newName, int newAge, String newEmail);

    /**
     * Delete client by id
     *
     * @param id client id;
     **/
    void deleteClient(long id);

    /**
     * Get list of all clients
     *
     * @return list of clients;
     **/
    List<Client> getAllClients();

    /**
     * Get client id by phone
     *
     * @param phone client phone;
     * @return client id;
     **/
    long getClientId(String phone);

    /**
     * Return true if phone exists
     *
     * @param  phone client phone;
     * @return true if phone exists;
     **/
    boolean hasPhone(String phone);

    /**
     * Get Client object by client id
     *
     * @param clientId client phone;
     * @return Client object;
     **/
    Client getClientById(long clientId);
}