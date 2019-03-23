package com.khorolets.view;

import com.khorolets.domain.Client;
import com.khorolets.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class ClientManager {
    @Autowired
    private ClientService clientService;
    @Autowired
    private BufferedReader br;


    public ClientManager(ClientService clientService, BufferedReader br) {
        this.clientService = clientService;
        this.br = br;
    }

    public void editClient(Class classType) {
        boolean isValid = false;
        long id;
        while (!isValid) {
            try {
                if (classType.equals(AdminMenu.class)) {
                    System.out.println("Input Id ");
                    id = Long.parseLong(br.readLine());
                } else id = ClientMenu.clientId;

                System.out.println("Input new name: ");
                String modifyName = br.readLine();

                System.out.println("Input new age: ");
                Integer modifyAge = readInteger();

                System.out.println("Input new email");
                String modifyEmail = br.readLine();

                clientService.editClient(id, modifyName, modifyAge, modifyEmail);
                isValid = true;

            } catch (NumberFormatException | IOException ex) {
            }
        }
    }

    public void editClient() {
        showClients();
        boolean isValid = false;

        while (!isValid) {
            try {

                System.out.println("Input Id ");
                long id = Long.parseLong(br.readLine());

                System.out.println("Input new name: ");
                String modifyName = br.readLine();

                System.out.println("Input new age: ");
                Integer modifyAge = readInteger();

                System.out.println("Input new email");
                String modifyEmail = br.readLine();

                clientService.editClient(id, modifyName, modifyAge, modifyEmail);
                isValid = true;

            } catch (NumberFormatException | IOException ex) {
            }
        }
    }

    public void showClients() {
        clientService.getAllClients().forEach(System.out::println);
    }

    public long createClient() throws IOException {
        System.out.println("Input name: ");
        String name = br.readLine();
        System.out.println("Input surname: ");
        String surname = br.readLine();
        System.out.println("Input phone number: ");
        String phoneNumber = br.readLine();
        System.out.println("Input age");
        Integer age = readInteger();
        System.out.println("Input email");
        String email = br.readLine();

        return clientService.createClient(name, surname, age, phoneNumber, email);
    }

    public long verifyClient() throws IOException {
        System.out.println("Input phone number in format: +380XXXXXXXXXX ");
        String phoneNumber = br.readLine();
        Client client = new Client("", "", phoneNumber);

        return clientService.verifyClientByPhone(client);
    }

    public void deleteClient() {
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Input client id as a long type");
                long id = Long.parseLong(br.readLine());
                clientService.deleteClient(id);
                isValid = true;
            } catch (NumberFormatException | IOException ex) {
            }
        }
    }

    public int readInteger() {
        int res = -1;

        try {
            boolean isValid = false;

            while (!isValid) {
                res = Integer.parseInt(br.readLine());
                isValid = true;
            }
        } catch (NumberFormatException | IOException e) {
        }

        return res;
    }
}