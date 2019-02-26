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
        if (products != null) {
            orderDao.orderProducts(new Order(clientService.getClientById(clientId), products));
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public List<Order> getOrdersByClientId(long clientId) {
        return new ArrayList<>(orderDao.getOrdersByClientId(clientId).values());
    }


    public void deleteOrdersByClientId(long clientId, long orderId) {
        orderDao.deleteOrdersByClientId(clientId, orderId);
    }
}