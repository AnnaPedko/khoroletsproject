package com.khorolets.services.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.dao.impl.ClientDaoImpl;
import com.khorolets.domain.Client;
import com.khorolets.services.ClientService;

public class ClientServiceImpl implements ClientService {


    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public void createClient(String name, String surname, String phone) {
        Client client = new Client(name,surname, phone);
        boolean result = clientDao.saveClient(client);
        if( result )
        {
            System.out.println("Client saved");
        }
    }

    @Override
    public void editClient(long id, String newName, String newPhone)
    {

        clientDao.editClient(id, newName, newPhone);
    }

    @Override
    public void showClients() {
        clientDao.showClients();

    }

    @Override
    public void deleteClient(long id) {
        clientDao.deleteClient(id);
    }

    @Override
    public long verifyClient(Client client) {
        long id = -1;
        if (clientDao.contain(client))
            id = 50;//client.getId();
        return id;
    }
}
