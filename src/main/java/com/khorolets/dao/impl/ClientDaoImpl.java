package com.khorolets.dao.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private static long generator = 0;
    private static volatile ClientDao clientDao;
    private Map<Long, Client> map = new HashMap<>();

    private ClientDaoImpl() {
    }

    public static ClientDao getInstance() {
        if (clientDao == null) {
            synchronized (ClientDaoImpl.class) {
                if (clientDao == null)
                    clientDao = new ClientDaoImpl();
            }
        }

        return clientDao;
    }

    @Override
    public long saveClient(Client client) {
        long clientId = -1;

        if (!phoneExists(client.getPhone())) {
            client.setId(generator++);
            map.put(client.getId(), client);
            clientId = client.getId();
        }

        return clientId;
    }

    @Override
    public void editClient(long id, String newName, int age, String email) {

        for (long key : map.keySet()) {
            if (id == key) {
                map.get(id).setName(newName);
                map.get(id).setAge(age);
                map.get(id).setEmail(email);
            }
        }
    }

    @Override
    public void deleteClient(long id) {
        if (map.keySet().contains(id))
            map.remove(id);
        System.out.println("Delete client with id " + id);
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(map.values());
    }

    public long getClientId(String phone) {
        long id = -1;
        for (Map.Entry<Long, Client> entry : map.entrySet()) {
            if (phone.equals(entry.getValue().getPhone())) {
                id = entry.getValue().getId();
            }
        }

        return id;
    }

    public boolean phoneExists(String phone) {
        boolean exist = false;

        for (Map.Entry<Long, Client> entry : map.entrySet()) {
            if (phone.equals(entry.getValue().getPhone())) {
                exist = true;
                System.out.println("The client with this phone exists");
            }
        }

        return exist;
    }

    @Override
    public Client getClientById(long clientId) {
        return map.get(clientId);
    }

}
