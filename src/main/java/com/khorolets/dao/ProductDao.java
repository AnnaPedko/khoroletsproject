package com.khorolets.dao;

import com.khorolets.domain.Product;

import java.math.BigDecimal;

public interface ProductDao {

    /**
     * Save product
     *
     * @param product
     *        product object that should be saved;
     * @return true if product have been saved;
     **/
    boolean saveProduct(Product product);

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
