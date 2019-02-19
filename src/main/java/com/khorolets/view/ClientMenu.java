package com.khorolets.view;

import com.khorolets.services.ClientService;
import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientMenu extends ClientManager {
    protected static long clientId = -1;
    private final BufferedReader br;
    private final ProductService productService;
    private final OrderService orderService;

    public ClientMenu(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService) {
        super(clientService, br);
        this.br = br;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void show() throws IOException {
        boolean isRunning = true;

        showAuthMenu();
        if (clientId >= 0) {
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
                        editClient(ClientMenu.class);
                        break;
                    case "R":
                        return;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Wrong value");
                        break;
                }
            }
        }
    }

    private void showAuthMenu() throws IOException {
        boolean isAuthorized = false;

        while (!isAuthorized) {
            showAuthorization();

            switch (br.readLine()) {
                case "1":
                    clientId = createClient();
                    break;
                case "2":
                    clientId = verifyClient();
                    break;
                case "R":
                    isAuthorized = true;
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong value");
            }
            if (clientId >= 0)
                isAuthorized = true;
        }
    }

    private void showMenu() {
        System.out.println("1. List of products");
        System.out.println("2. Order product");
        System.out.println("3. List of orders");
        System.out.println("4. Delete order");
        System.out.println("5. Modify account");
        System.out.println("R. Return");
        System.out.println("0. Exit");
    }

    private void showAuthorization() {
        System.out.println("1. Sign up");
        System.out.println("2. Sign in");
        System.out.println("R. Return");
        System.out.println("0. Exit");
    }

    private void showProducts() {
        productService.showProducts();
    }

    private void orderProduct() {
        showProducts();
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Please enter long type of product Id ");
                long productId = Long.parseLong(br.readLine());
                orderService.orderProduct(clientId, productId);
                isValid = true;

            } catch (IOException ex) {

            }
        }
    }

    private void showOrders() {
        orderService.showOrdersByClientId(clientId);
    }

    private void deleteOrders() {
        orderService.deleteOrdersByClientId(clientId);
    }
}
