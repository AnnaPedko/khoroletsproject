package com.khorolets.services;

import java.math.BigDecimal;

public interface ProductService {

    /**
     * Create product
     *
     * @param name
     *        product name;
     * @param price
     *        product price;
     **/
    void createProduct(String name, BigDecimal price);

    /**
     * Modify product by id
     *
     * @param id
     *         product id;
     * @param newName
     *        new product name;
     * @param newPrice
     *         new price;
     **/
    void editProduct(long id, String newName, BigDecimal newPrice);

    /**
     * Show list of all products
     **/
    void showProducts();

    /**
     * Delete product by id
     *
     * @param id
     *         product id;
     **/
    void deleteProduct(long id);
}
