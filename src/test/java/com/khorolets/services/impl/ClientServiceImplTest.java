package com.khorolets.services.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.domain.Client;
import com.khorolets.services.ClientService;
import com.khorolets.validators.ValidationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {
    @Mock
    private ValidationService validationService;
    @Mock
    private ClientDao clientDao;

    private ClientService clientService;

    @Before
    public void init() {
        clientService = new ClientServiceImpl(clientDao, validationService);
    }

    @Test
    public void createClientWithFullParametersTest() {
        //GIVEN
        long id = 1;
        String name = "Test";
        String surname = "Test";
        int age = 10;
        String phone = "+380501111111";
        String email = "test@test.com";

        Client client = new Client(name, surname, age, phone, email);
        Mockito.when(clientDao.saveClient(client)).thenReturn(id);

        //WHEN
        long res = clientService.createClient(name, surname, age, phone, email);

        //THEN

        Assert.assertEquals(id, res);
    }

    @Test
    public void editClient() {
        //GIVEN
        long id = 1;
        String name = "Test";
        int age = 10;
        String email = "test@test.com";

        //WHEN
        clientService.editClient(id, name, age, email);

        //THEN
        Mockito.verify(clientDao, Mockito.times(1)).editClient(id, name, age, email);
    }

    @Test
    public void deleteClient() {
        //GIVEN
        long id = 1;

        //WHEN
        clientService.deleteClient(id);

        //THEN
        Mockito.verify(clientDao, Mockito.times(1)).deleteClient(id);
    }

    @Test
    public void verifyClientByPhone() {
        //GIVEN
        long id = 1;
        String name = "TestName";
        String surname = "TestSurname";
        int age = 10;
        String email = "test@test.com";
        String phone = "+380501111111";

        //WHEN
        Mockito.when(clientDao.hasPhone(phone)).thenReturn(true);
        Mockito.when(clientDao.getClientId(phone)).thenReturn(id);

        //THEN
        long res = clientService.verifyClientByPhone(new Client(name, surname, age, phone, email));

        Assert.assertEquals(id, res);
    }

    @Test
    public void verifyClientByPhoneNegative() {
        //GIVEN
        long id = -1;
        String name = "TestName";
        String surname = "TestSurname";
        int age = 10;
        String email = "test@test.com";
        String phone = "+380501111111";

        //WHEN
        Mockito.when(clientDao.hasPhone(phone)).thenReturn(false);

        //THEN
        long res = clientService.verifyClientByPhone(new Client(name, surname, age, phone, email));

        Assert.assertEquals(id, res);
    }

    @Test
    public void getAllClients() {
        clientService.getAllClients();
        Mockito.verify(clientDao, Mockito.times(1)).getAllClients();
    }

    @Test
    public void getClientByIdTest() {
        //GIVEN
        String name = "Test";
        String surname = "Test";
        int age = 10;
        String phone = "+380501111111";
        String email = "test@test.com";
        long id = 1;

        Client expectedClient = new Client(id, name, surname, age, phone, email);
        Mockito.when(clientDao.getClientById(id)).thenReturn(expectedClient);

        //WHEN
        Client client = clientService.getClientById(1);

        //THEN
        Mockito.verify(clientDao).getClientById(1);
        Mockito.verifyNoMoreInteractions(clientDao);
        Assert.assertEquals(expectedClient, client);
    }
}