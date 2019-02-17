package com.khorolets.dao.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private static long generator = 0;
    private static ClientDao clientDao = new ClientDaoImpl();
    private Map<Long, Client> map = new HashMap<>();

    private ClientDaoImpl() {

    }

    public static ClientDao getInstance() {
        return clientDao;
    }

    @Override
    public boolean saveClient(Client client) {
        client.setId(generator++);
        map.put(client.getId(), client);

        return true;
    }

    @Override
    public void editClient(long id, String newName, String newPhone) {
        System.out.println("Client with id " + id + "is modified");
    }

    @Override
    public void showClients() {
        System.out.println("Show clients");
    }

    @Override
    public void deleteClient(long id) {
        System.out.println("Delete client with id " + id);
    }

    public boolean contain(Client client) {
        return true;
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(map.values());
    }
}
