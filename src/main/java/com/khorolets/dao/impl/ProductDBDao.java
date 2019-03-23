package com.khorolets.dao.impl;

import com.khorolets.dao.ProductDao;
import com.khorolets.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class ProductDBDao implements ProductDao {
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/khorolets.project";
    private static final String USER = "Test";
    private static final String PASSWORD = "";

    private static volatile ProductDao productDao;

    private ProductDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement()) {

            st.execute("CREATE TABLE IF NOT EXISTS PRODUCT(ID INT(10) AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), PRICE DECIMAL(10) );");

        } catch (SQLException ex) {
            System.out.println("Something wrong");
        }
    }

    public static ProductDao getInstance() {
        if (productDao == null) {
            synchronized (ProductDaoImpl.class) {
                if (productDao == null)
                    productDao = new ProductDBDao();
            }
        }

        return productDao;
    }

    @Override
    public long saveProduct(Product product) {
        long result = -1;
        String insertSQL = "INSERT INTO PRODUCT(NAME, PRICE) VALUES(?, ?);";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, product.getName());
            st.setBigDecimal(2, product.getPrice());

            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void editProduct(long id, String newName, BigDecimal newPrice) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("UPDATE PRODUCT SET NAME = ?,  PRICE = ? WHERE ID = ?;")) {

            st.setString(1, newName);
            st.setBigDecimal(2, newPrice);
            st.setLong(3, id);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM PRODUCT;")) {

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                BigDecimal price = resultSet.getBigDecimal("PRICE");

                products.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public void deleteProduct(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement st = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID = ?")) {

            st.setLong(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(long productId) {
        Product product = new Product();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM PRODUCT WHERE ID = " + productId)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                BigDecimal price = resultSet.getBigDecimal("PRICE");

                product = new Product(id, name, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}