package com.khorolets.services.impl;

import com.khorolets.dao.OrderDao;
import com.khorolets.dao.impl.OrderDaoImpl;

import com.khorolets.services.OrderService;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void showOrders() {
        orderDao.showOrders();
    }
    public void showOrdersByClientId(long clientId){
        orderDao.showOrdersByClientId(clientId);
    }

    @Override
    public void orderProduct(long clientId, long productId) {
        orderDao.orderProduct(clientId,productId);

    }

    public void deleteOrdersByClientId(long clientId)
    {
        orderDao.deleteOrdersByClientId(clientId);

    }
}
