package com.khorolets.services;

import com.khorolets.domain.Client;

import java.util.List;

public interface ClientService {

    /**
     * Create new client
     *
     * @param name    client name;
     * @param surname client surname;
     * @param phone   client phone;
     * @param
     **/

    void createClient(String name, String surname, Integer age, String phone, String email);

    /**
     * Modify client by id
     *
     * @param id       client id;
     * @param newName  new client name;
     * @param newPhone new client phone;
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
     * Verification of client
     *
     * @param client client object;
     **/
    long verifyClient(Client client);

    List<Client> getAllClients();

}
