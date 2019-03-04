package com.khorolets.view;

import com.khorolets.services.ClientService;
import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class AdminMenu extends ClientManager {

    private final BufferedReader br;
    private final ProductService productService;
    private final OrderService orderService;

    public AdminMenu(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService) {
        super(clientService, br);

        this.br = br;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void show() throws IOException {

        boolean isRunning = true;
        while (isRunning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    editClient();
                    break;
                case "3":
                    deleteClient();
                    break;
                case "4":
                    showClients();
                    break;
                case "5":
                    showProducts();
                    break;
                case "6":
                    createProduct();
                    break;
                case "7":
                    editProduct();
                    break;
                case "8":
                    deleteProduct();
                    break;
                case "9":
                    showOrders();
                    break;
                case "R":
                    isRunning = false;
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong value");
                    break;
            }
        }
    }

    public void createProduct() {
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println("Input name: ");
                String name = br.readLine();
                System.out.println("Input price: ");
                BigDecimal price = new BigDecimal(br.readLine());
                productService.createProduct(name, price);
                isValid = true;
            } catch (NumberFormatException | IOException ex) {
                System.out.println("You input not valid price");
            }
        }
    }

    public void editProduct() {
        boolean isValid = false;
        showProducts();
        while (!isValid) {
            try {
                System.out.println("Input Id as a long type");
                long id = Long.parseLong(br.readLine());
                System.out.println("Input new name: ");
                String name = br.readLine();
                System.out.println("Input new price: ");
                BigDecimal price = new BigDecimal(br.readLine());
                productService.editProduct(id, name, price);
                isValid = true;

            } catch (NumberFormatException | IOException ex) {
            }
        }
    }

    private void deleteProduct() {
        showProducts();
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Input id as a long type");
                long id = Long.parseLong(br.readLine());
                productService.deleteProduct(id);
                isValid = true;
            } catch (NumberFormatException | IOException ex) {
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List of client");
        System.out.println("5. List of products");
        System.out.println("6. Add product");
        System.out.println("7. Edit product");
        System.out.println("8. Delete product");
        System.out.println("9. List of orders");
        System.out.println("R. Return ");//exit from this menu
        System.out.println("0. Exit");
    }

    private void showProducts() {
        productService.getAllProducts().forEach(System.out::println);
    }

    private void showOrders() {
        orderService.getAllOrders().forEach(System.out::println);
    }
}