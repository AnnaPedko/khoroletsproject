package com.khorolets.dao.impl;

import com.khorolets.dao.OrderDao;
import com.khorolets.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDaoImpl implements OrderDao {
    private static volatile OrderDao orderDao;
    private static long generator = 0;
    private Map<Long, Order> map = new HashMap<>();

    private OrderDaoImpl() {
    }

    public static OrderDao getInstance() {
        if (orderDao == null) {
            synchronized (ClientDaoImpl.class) {
                if (orderDao == null)
                    orderDao = new OrderDaoImpl();
            }
        }

        return orderDao;
    }

    public long orderProducts(Order order) {
        order.setId(generator++);
        map.put(order.getId(), order);

        return order.getId();
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(map.values());
    }

    public Map<Long, Order> getOrdersByClientId(long clientId) {
        Map<Long, Order> result = map.entrySet()
                .stream()
                .filter(x -> x.getValue().getClient().getId() == clientId)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        return result;
    }

    @Override
    public void deleteOrdersByClientId(long clientId, long orderId) {

        if (getOrdersByClientId(clientId).remove(orderId) != null) {
            map.remove(orderId);
            System.out.println(" Delete order with id = " + orderId);
        } else {
            System.out.println(" There is no order with id = " + orderId);
        }
    }
}