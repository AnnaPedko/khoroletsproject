package com.khorolets.dao;

import com.khorolets.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {

    /**
     * Save product
     *
     * @param product product object that should be saved;
     * @return id of saved product;
     **/
    long saveProduct(Product product);

    /**
     * Modify product by id
     *
     * @param id       product id;
     * @param newName  new product name;
     * @param newPrice new price;
     **/
    void editProduct(long id, String newName, BigDecimal newPrice);

    /**
     * Get list of all products
     *
     * @return list of products;
     **/
    List<Product> getAllProducts();

    /**
     * Delete product by id
     *
     * @param id product id;
     **/
    void deleteProduct(long id);

    /**
     * Get product by id
     *
     * @param id product id;
     * @return product object;
     **/
    Product getProductById(long id);
}