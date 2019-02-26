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
     * @param age     client age;
     * @param phone   client phone;
     * @param email   client email;
     * @return id of created client;
     **/
    long createClient(String name, String surname, Integer age, String phone, String email);

    /**
     * Modify client by id
     *
     * @param id       client id;
     * @param newName  new client name;
     * @param newAge   new client age;
     * @param newEmail new client phone;
     **/
    void editClient(long id, String newName, int newAge, String newEmail);

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
     * @return client id;
     **/
    long verifyClientByPhone(Client client);

    /**
     * Get all list of client;
     *
     * @return list of clients;
     **/
    List<Client> getAllClients();

    /**
     * Get client by id;
     *
     * @param clientId client id;
     * @return client object;
     **/
    Client getClientById(long clientId);
}