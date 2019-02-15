package com.khorolets.dao.impl;

import com.khorolets.dao.ProductDao;
import com.khorolets.domain.Product;

import java.math.BigDecimal;

public class ProductDaoImpl implements ProductDao {

    public boolean saveProduct( Product product)
    {
        return true;
    }

    @Override
    public void editProduct(long id, String neName, BigDecimal newPrice)
    {
        System.out.println("Product modified with id " + id);
    }

    @Override
    public void showProducts() {
        System.out.println("Show products");
    }

    @Override
    public void deleteProduct(long id) {
        System.out.println("Delete product with id "+ id);
    }
}
