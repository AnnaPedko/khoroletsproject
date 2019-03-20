package com.khorolets.services.impl;

import com.khorolets.dao.OrderDao;
import com.khorolets.domain.Order;
import com.khorolets.domain.Product;
import com.khorolets.services.ClientService;
import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;
import com.khorolets.validators.ValidationService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private ValidationService validationService;
    private ProductService productService;
    private ClientService clientService;

    public OrderServiceImpl(OrderDao orderDao, ValidationService validationService, ProductService productService, ClientService clientService) {
        this.orderDao = orderDao;
        this.validationService = validationService;
        this.productService = productService;
        this.clientService = clientService;
    }

    @Override
    public void orderProducts(long clientId, ArrayList<Long> productIds) {
        List<Product> products = new ArrayList<>();
        for (Long id : productIds) {
            if (productService.getProductById(id) != null) {
                products.add(productService.getProductById(id));
            }

        }
        long getClientId = clientService.getClientById(clientId).getId();
        if (products != null && clientId == getClientId) {
            orderDao.orderProducts(new Order(clientService.getClientById(clientId), products));
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders;
        orders = getOrders(orderDao.getAllOrders());

        return orders;
    }

    public List<Order> getOrdersByClientId(long clientId) {
        List<Order> orders;
        orders = getOrders(orderDao.getOrdersByClientId(clientId));

        return orders;
    }


    public void deleteOrdersByClientId(long clientId, long orderId) {
        orderDao.deleteOrdersByClientId(clientId, orderId);
    }

    public List<Order> getOrders(List<long[]> ordersTable) {
        List<Order> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        long previousOrderId = -1;

        for (int i = 0; i < ordersTable.size(); ++i) {
            long orderId = ordersTable.get(i)[0];
            long clientId = ordersTable.get(i)[1];
            long productId = ordersTable.get(i)[2];

            if (orderId == previousOrderId)
                products.add(productService.getProductById(productId));
            else {
                products = new ArrayList<>();
                products.add(productService.getProductById(productId));
                previousOrderId = orderId;
                orders.add(new Order(orderId, clientService.getClientById(clientId), products));
            }

        }
        return orders;
    }
}