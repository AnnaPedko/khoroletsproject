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

    @Override
    public void createClient(String name, String surname, String phone) {
        this.createClient(name, surname, 0, phone, null);
    }

    public void createClient(String name, String surname, Integer age, String phone, String email) {
        try {
            //validationService.validateAge(age);
            //validationService.validatePhone(phone);
            validationService.validateEmail(email);

            Client client = new Client(name, surname, age, phone, email);
            boolean result = clientDao.saveClient(client);

            if (result) {
                System.out.println("Client saved");
            }
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void editClient(long id, String newName, String newPhone) {

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

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }
}
