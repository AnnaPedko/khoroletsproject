package com.khorolets.view;

import com.khorolets.services.ClientService;
import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

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
        productService.getAllProducts().forEach(System.out::println);
    }

    private void orderProduct() throws IOException {
        showProducts();
        ArrayList<Long> clientProductIds = new ArrayList<>();
        boolean isOrdered = false;

        while (!isOrdered) {
            System.out.println("Please enter long type of product Id and  \"S\" to save order");
            String order = br.readLine();
            switch (order) {
                case "S":
                    saveOrder(clientProductIds);
                    isOrdered = true;
                    break;

                default: {
                    long productId = Long.parseLong(order);
                    clientProductIds.add(productId);
                }
            }
        }
    }

    private void saveOrder(ArrayList<Long> clientProductIds) {
        if (!clientProductIds.isEmpty()) {
            orderService.orderProduct(clientId, clientProductIds);
        } else {
            System.out.println("List of products is empty");
        }
    }

    private void showOrders() {
        orderService.getOrdersByClientId(clientId).forEach(System.out::println);
    }

    private void deleteOrders() {
        showOrders();
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Input id as a long type");
                long id = Long.parseLong(br.readLine());
                orderService.deleteOrdersByClientId(clientId, id);
                isValid = true;
            } catch (NumberFormatException | IOException ex) {
            }
        }
    }

}
