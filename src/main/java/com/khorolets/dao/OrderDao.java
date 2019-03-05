package com.khorolets.dao;

import com.khorolets.domain.Order;

import java.util.List;

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
     * @return list of arrays [orderId, clientId, productId];
     **/
    List<long[]> getAllOrders();

    /**
     * Get list of orders for certain client id
     *
     * @param clientId client id;
     * @return list of arrays [orderId, clientId, productId]
     **/
    List<long[]> getOrdersByClientId(long clientId);

    /**
     * Delete order for certain client id
     *
     * @param clientId client id;
     * @param orderId  order id;
     **/
    void deleteOrdersByClientId(long clientId, long orderId);
}