package com.khorolets;

import com.khorolets.view.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        /*ClientDao clientDao = ClientDBDao.getInstance();
        ProductDao productDao = ProductDBDao.getInstance();
        OrderDao orderDao = OrderDBDao.getInstance();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ValidationService validationService = new ValidationServiceImpl();

        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
        ProductService productService = new ProductServiceImpl(productDao, validationService);
        OrderService orderService = new OrderServiceImpl(orderDao, validationService, productService, clientService);

        AdminMenu adminMenu = new AdminMenu(br, clientService, productService, orderService);
        ClientMenu clientMenu = new ClientMenu(br, clientService, productService, orderService);
        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);

        menu.showMenu();*/

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.khorolets/view", "com.khorolets/services", "com.khorolets/dao", "com.khorolets/validators");
        MainMenu menu = context.getBean(MainMenu.class);
        menu.showMenu();
    }
}