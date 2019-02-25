package com.khorolets.dao;

import com.khorolets.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    /**
     * Order product
     *
     * @param clientId  id of client who orders product;
     * @param productId product id;
     * @return true if order have been done ;
     **/
    long orderProduct(Order order);

    /**
     * Show list of all orders
     **/
    List<Order> getAllOrders();

    /**
     * Show list of orders for certain client id
     *
     * @param clientId client id;
     **/
    Map<Long, Order> getOrdersByClientId(long clientId);

    /**
     * Delete all orders for certain client id
     *
     * @param clientId client id;
     **/
    void deleteOrdersByClientId(long clientId, long orderId);
}
