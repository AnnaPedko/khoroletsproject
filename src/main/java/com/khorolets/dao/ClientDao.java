package com.khorolets.dao;

import com.khorolets.domain.Client;

import java.util.List;

public interface ClientDao {

    /**
     * Save client
     *
     * @param client Client object that should be saved ;
     * @return true if client have been saved ;
     **/
    boolean saveClient(Client client);

    /**
     * Modify client by id
     *
     * @param id       client id;
     * @param newName  new client name ;
     * @param newPhone new client phone ;
     **/
    void editClient(long id, String newName, String newPhone);

    /**
     * Show list of all clients
     **/
    void showClients();

    /**
     * Delete client by id
     *
     * @param id client id;
     **/
    void deleteClient(long id);

    /**
     * Show if client exists in db
     *
     * @param client client object;
     * @return true if exists
     **/
    boolean contain(Client client);

    List<Client> getAllClients();

}