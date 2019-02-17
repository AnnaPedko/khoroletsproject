package com.khorolets.dao.impl;

import com.khorolets.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {
    public boolean orderProduct(long clientId, long productId) {
        return true;
    }

    @Override
    public void showOrders() {

        System.out.println("Show orders");
    }

    public void showOrdersByClientId(long clientId) {

        System.out.println("Show orders by client Id" + clientId);
    }

    @Override
    public void deleteOrdersByClientId(long clientId) {
        System.out.println("Delete order with id" + clientId);
    }

}
