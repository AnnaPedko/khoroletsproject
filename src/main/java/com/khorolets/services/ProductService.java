package com.khorolets.services;

import com.khorolets.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Create product
     *
     * @param name  product name;
     * @param price product price;
     * @return id of created product;
     **/
    long createProduct(String name, BigDecimal price);

    /**
     * Modify product by id
     *
     * @param id       product id;
     * @param newName  new product name;
     * @param newPrice new price;
     **/
    void editProduct(long id, String newName, BigDecimal newPrice);

    /**
     * Show list of all products
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
     * @return product object
     **/
    Product getProductById(long id);
}