package com.khorolets.dao.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.domain.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {
    private static volatile ClientDao clientDao;
    private static long generator = 0;
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
        client.setId(generator++);
        map.put(client.getId(), client);

        return client.getId();
    }

    @Override
    public void editClient(long id, String newName, int age, String email) {
        if (map.keySet().contains(id)) {
            map.get(id).setName(newName);
            map.get(id).setAge(age);
            map.get(id).setEmail(email);

            System.out.println("Client with id = " + id + " is edited");
        } else
            System.out.println("Client with id = " + id + " does not exist");
    }

    @Override
    public void deleteClient(long id) {
        if (map.remove(id) != null) {
            System.out.println("Delete client with id =  " + id);
        } else {
            System.out.println("There is no client with id = " + id);
        }
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

    public boolean hasPhone(String phone) {
        boolean exist = false;

        for (Map.Entry<Long, Client> entry : map.entrySet()) {
            if (phone.equals(entry.getValue().getPhone())) {
                exist = true;
                System.out.println("The client with phone " + phone + " exists");
            }
        }

        return exist;
    }

    @Override
    public Client getClientById(long clientId) {
        return map.get(clientId);
    }
}