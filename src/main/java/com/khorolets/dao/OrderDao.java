package com.khorolets.dao;

import com.khorolets.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    /**
     * Order product
     *
     * @param order order object;
     * @return order id;
     **/
    long orderProducts(Order order);

    /**
     * Get list of all orders
     *
     * @return list of orders;
     **/
    List<Order> getAllOrders();

    /**
     * Get list of orders for certain client id
     *
     * @param clientId client id;
     * @return collection with key = order id, value = order object;
     **/
    Map<Long, Order> getOrdersByClientId(long clientId);

    /**
     * Delete order for certain client id
     *
     * @param clientId client id;
     * @param orderId  order id;
     **/
    void deleteOrdersByClientId(long clientId, long orderId);
}