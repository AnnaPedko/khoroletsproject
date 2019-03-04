package com.khorolets.dao.impl;

import com.khorolets.dao.OrderDao;
import com.khorolets.domain.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


public class OrderDBDao implements OrderDao {
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/khorolets.project";
    private static final String USER = "Test";
    private static final String PASSWORD = "";

    public OrderDBDao() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement()) {

            st.execute("CREATE TABLE IF NOT EXISTS ORDERS(ID INT(10) AUTO_INCREMENT PRIMARY KEY,  CLIENT_ID INT(10) );");
            st.execute("CREATE TABLE IF NOT EXISTS ORDER_ITEM(ID INT(10) AUTO_INCREMENT PRIMARY KEY, ORDER_ID INT(10), PRODUCT_ID INT(10));");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public long orderProducts(Order order) {
        return 0;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Map<Long, Order> getOrdersByClientId(long clientId) {
        return null;
    }

    @Override
    public void deleteOrdersByClientId(long clientId, long orderId) {

    }
}
