package com.khorolets.view;

import com.khorolets.domain.Client;
import com.khorolets.services.ClientService;
import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientMenu {
    private final BufferedReader br;

    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    private long clientId;

    public ClientMenu(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService) {
        this.br = br;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void show() throws IOException {


        boolean isRunning = true;

        verifyClient();
        if (clientId > 0) {
            while (isRunning) {
                showMenu();
                switch (br.readLine()) {
                    case "1":
                        showProducts();
                        break;
                    case "2":
                        orderProduct();
                        break;
                    case "3":
                        showOrders();
                        break;
                    case "4":
                        deleteOrders();
                        break;
                    case "5":
                        return;
                    case "6":
                        createClient();
                        break;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Wrong value");
                        break;
                }
            }
        }
    }

    private void verifyClient() throws IOException {
        System.out.println("Input name: ");
        String name = br.readLine();
        System.out.println("Input surname: ");
        String surname = br.readLine();
        System.out.println("Input phone number: ");
        String phoneNumber = br.readLine();
        Client client = new Client(name, surname, phoneNumber);
        if (clientService.verifyClient(client) > 0)
            clientId = clientService.verifyClient(client);
    }

    private void createClient() throws IOException {
        System.out.println("Input name: ");
        String name = br.readLine();
        System.out.println("Input surname: ");
        String surname = br.readLine();
        System.out.println("Input phone number: ");
        String phoneNumber = br.readLine();
        clientService.createClient(name, surname, phoneNumber);
    }

    private void showMenu() {
        System.out.println("1. List of products");
        System.out.println("2. Order product");
        System.out.println("3. List of orders");
        System.out.println("4. Delete order");
        System.out.println("5. Return ");//exit from this menu
        System.out.println("6. CreateClient ");//exit from this menu
        System.out.println("0. Exit");

    }

    private void showProducts() {
        productService.showProducts();
    }

    private void orderProduct() throws IOException {
        showProducts();
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Please enter long type of product Id ");
                long productId = Long.parseLong(br.readLine());
                orderService.orderProduct(clientId, productId);
                isValid = true;

            } catch (Exception ex) {

            }
        }
    }

    private void showOrders() throws IOException {
        orderService.showOrdersByClientId(clientId);
    }

    private void deleteOrders() throws IOException {
        orderService.deleteOrdersByClientId(clientId);
    }
}
