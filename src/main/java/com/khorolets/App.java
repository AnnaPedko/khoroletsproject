package com.khorolets;

import com.khorolets.dao.ClientDao;
import com.khorolets.dao.OrderDao;
import com.khorolets.dao.ProductDao;
import com.khorolets.dao.impl.ClientDBDao;
import com.khorolets.dao.impl.OrderDaoImpl;
import com.khorolets.dao.impl.ProductDBDao;
import com.khorolets.services.ClientService;
import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;
import com.khorolets.services.impl.ClientServiceImpl;
import com.khorolets.services.impl.OrderServiceImpl;
import com.khorolets.services.impl.ProductServiceImpl;
import com.khorolets.validators.ValidationService;
import com.khorolets.validators.impl.ValidationServiceImpl;
import com.khorolets.view.AdminMenu;
import com.khorolets.view.ClientMenu;
import com.khorolets.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {
        ClientDao clientDao = new ClientDBDao();
        ProductDao productDao = new ProductDBDao();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ValidationService validationService = new ValidationServiceImpl();

        //ClientDao clientDao = ClientDaoImpl.getInstance();
        //ProductDao productDao = ProductDaoImpl.getInstance();
        OrderDao orderDao = OrderDaoImpl.getInstance();

        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
        ProductService productService = new ProductServiceImpl(productDao, validationService);
        OrderService orderService = new OrderServiceImpl(orderDao, validationService, productService, clientService);

        AdminMenu adminMenu = new AdminMenu(br, clientService, productService, orderService);
        ClientMenu clientMenu = new ClientMenu(br, clientService, productService, orderService);
        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);

        menu.showMenu();
    }
}