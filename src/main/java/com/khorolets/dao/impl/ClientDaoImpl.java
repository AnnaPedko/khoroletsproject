package com.khorolets.dao.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.domain.Client;

public class ClientDaoImpl implements ClientDao {

    @Override
    public boolean saveClient(Client client) {
        return true;
    }

    @Override
    public void editClient(long id, String newName, String newPhone)
    {
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

    public boolean contain(Client client)
    {
        return true;
    }
}
