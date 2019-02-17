package com.khorolets.services.impl;

import com.khorolets.dao.OrderDao;
import com.khorolets.services.OrderService;
import com.khorolets.validators.ValidationService;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private ValidationService validationService;

    public OrderServiceImpl(OrderDao orderDao, ValidationService validationService) {
        this.orderDao = orderDao;
        this.validationService = validationService;
    }

    @Override
    public void showOrders() {
        orderDao.showOrders();
    }

    public void showOrdersByClientId(long clientId) {
        orderDao.showOrdersByClientId(clientId);
    }

    @Override
    public void orderProduct(long clientId, long productId) {
        orderDao.orderProduct(clientId, productId);
    }

    public void deleteOrdersByClientId(long clientId) {
        orderDao.deleteOrdersByClientId(clientId);

    }
}
