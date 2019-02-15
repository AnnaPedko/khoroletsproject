package com.khorolets.services;

import com.khorolets.domain.Client;

public interface ClientService {

    /**
     * Create new client
     *
     * @param name
     *         client name;
     * @param surname
     *        client surname;
     * @param phone
     *        client phone;
     **/
    void createClient(String name, String surname, String phone);

    /**
     * Modify client by id
     *
     * @param id
     *         client id;
     * @param newName
     *        new client name;
     * @param newPhone
     *         new client phone;
     **/
    void editClient(long id, String newName, String newPhone);

    /**
     * Show list of all clients
     **/
    void showClients();

    /**
     * Delete client by id
     *
     * @param id
     *         client id;
     **/
    void deleteClient(long id);

    /**
     * Verification of client
     *
     * @param client
     *         client object;
     **/
    long verifyClient(Client client);
}
