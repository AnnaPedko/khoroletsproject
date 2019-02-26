package com.khorolets.services.impl;

import com.khorolets.dao.ProductDao;
import com.khorolets.domain.Product;
import com.khorolets.services.ProductService;
import com.khorolets.validators.ValidationService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private ValidationService validationService;

    public ProductServiceImpl(ProductDao productDao, ValidationService validationService) {
        this.productDao = productDao;
        this.validationService = validationService;
    }

    @Override
    public long createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        return productDao.saveProduct(product);
    }

    @Override
    public void editProduct(long id, String newName, BigDecimal newPrice) {
        productDao.editProduct(id, newName, newPrice);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void deleteProduct(long id) {
        productDao.deleteProduct(id);
    }

    public Product getProductById(long id) {
        return productDao.getProductById(id);
    }
}