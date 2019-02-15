package com.khorolets.services.impl;

import com.khorolets.dao.ProductDao;
import com.khorolets.dao.impl.ProductDaoImpl;
import com.khorolets.domain.Product;
import com.khorolets.services.ProductService;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        boolean result = productDao.saveProduct(product);
        if (result) {
            System.out.println("Product created");
        }
    }

    @Override
    public void editProduct(long id, String newName, BigDecimal newPrice) {
        productDao.editProduct(id, newName, newPrice);
    }

    @Override
    public void showProducts() {
        productDao.showProducts();

    }

    @Override
    public void deleteProduct(long id) {
        productDao.deleteProduct(id);
    }
}
