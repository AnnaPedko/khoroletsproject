package com.khorolets.services.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.domain.Client;
import com.khorolets.exception.BusinessException;
import com.khorolets.services.ClientService;
import com.khorolets.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    public long createClient(String name, String surname, Integer age, String phone, String email) {
        long clientId = -1;
        try {
            validationService.validateAge(age);
            validationService.validatePhone(phone);
            validationService.validateEmail(email);
            validationService.validateString(name);
            validationService.validateString(surname);

            Client client = new Client(name, surname, age, phone, email);
            if (!clientDao.hasPhone(client.getPhone()))
                clientId = clientDao.saveClient(client);
            else
                System.out.println("The client with this phone exists. Client was not saved");

        } catch (BusinessException ex) {
            ex.printStackTrace();
        }

        return clientId;
    }

    @Override
    public void editClient(long id, String newName, int age, String email) {
        try {
            validationService.validateAge(age);
            validationService.validateEmail(email);
            validationService.validateString(newName);

            clientDao.editClient(id, newName, age, email);
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteClient(long id) {
        clientDao.deleteClient(id);
    }

    @Override
    public long verifyClientByPhone(Client client) {
        long id = -1;

        if (clientDao.hasPhone(client.getPhone()))
            id = clientDao.getClientId(client.getPhone());

        return id;
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    public Client getClientById(long clientId) {
        return clientDao.getClientById(clientId);
    }
}