package com.khorolets.dao.impl;

import com.khorolets.dao.OrderDao;
import com.khorolets.domain.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDBDao implements OrderDao {
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/khorolets.project";
    private static final String USER = "Test";
    private static final String PASSWORD = "";

    private static volatile OrderDao orderDao;

    private OrderDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement()) {

            st.execute("CREATE TABLE IF NOT EXISTS ORDERS(ID INT(10) AUTO_INCREMENT PRIMARY KEY,  CLIENT_ID INT(10) );");
            st.execute("CREATE TABLE IF NOT EXISTS ORDER_ITEM(ID INT(10) AUTO_INCREMENT PRIMARY KEY, ORDER_ID INT(10), PRODUCT_ID INT(10));");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static OrderDao getInstance() {
        if (orderDao == null) {
            synchronized (ProductDaoImpl.class) {
                if (orderDao == null)
                    orderDao = new OrderDBDao();
            }
        }

        return orderDao;
    }

    @Override
    public long orderProducts(Order order) {
        long result = -1;
        String insertSQL = "INSERT INTO ORDERS(CLIENT_ID) VALUES(?);";
        String insertSQL2 = "INSERT INTO ORDER_ITEM(ORDER_ID, PRODUCT_ID) VALUES(?, ?);";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statement = connection.prepareStatement(insertSQL2)) {

            st.setLong(1, order.getClient().getId());

            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getLong(1);

                for (int i = 0; i < order.getProducts().size(); ++i) {
                    statement.setLong(1, result);
                    statement.setLong(2, order.getProducts().get(i).getId());
                    statement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<long[]> getAllOrders() {
        List<long[]> orders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT ORDERS.ID, ORDERS.CLIENT_ID, ORDER_ITEM.PRODUCT_ID FROM ORDERS INNER JOIN ORDER_ITEM ON ORDERS.ID = ORDER_ITEM.ORDER_ID ORDER BY ORDERS.ID;")) {

            while (resultSet.next()) {
                long orderId = resultSet.getLong("ID");
                long clientId = resultSet.getLong("CLIENT_ID");
                long productId = resultSet.getLong("PRODUCT_ID");
                orders.add(new long[]{orderId, clientId, productId});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<long[]> getOrdersByClientId(long id) {
        List<long[]> orders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT ORDERS.ID, ORDERS.CLIENT_ID, ORDER_ITEM.PRODUCT_ID FROM ORDERS INNER JOIN ORDER_ITEM ON ORDERS.ID = ORDER_ITEM.ORDER_ID WHERE ORDERS.CLIENT_ID = " + id + "ORDER BY ORDERS.ID ;")) {

            while (resultSet.next()) {
                long orderId = resultSet.getLong("ID");
                long clientId = resultSet.getLong("CLIENT_ID");
                long productId = resultSet.getLong("PRODUCT_ID");
                orders.add(new long[]{orderId, clientId, productId});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public void deleteOrdersByClientId(long clientId, long orderId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("DELETE FROM ORDERS WHERE ID = ? AND CLIENT_ID = ?");
             PreparedStatement statement = connection.prepareStatement("DELETE FROM ORDER_ITEM WHERE ORDER_ID = ?")) {

            st.setLong(1, orderId);
            st.setLong(2, clientId);
            statement.setLong(1, orderId);

            int res = st.executeUpdate();
            if (res == 1)
                statement.executeUpdate();
            else
                System.out.println("This order does not exist for this user");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}