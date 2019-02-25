package com.khorolets.services;

import com.khorolets.domain.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    /**
     * Order product
     *
     * @param clientId  id of client who orders product;
     * @param productId product id;
     **/
    void orderProduct(long clientId, ArrayList<Long> productIds);

    /**
     * Show list of all orders
     **/
    List<Order> getAllOrders();

    /**
     * Show list of orders for certain client id
     *
     * @param clientId client id;
     **/
    List<Order> getOrdersByClientId(long clientId);

    /**
     * Delete all orders for certain client id
     *
     * @param clientId client id;
     **/
    void deleteOrdersByClientId(long clientId, long id);
}
