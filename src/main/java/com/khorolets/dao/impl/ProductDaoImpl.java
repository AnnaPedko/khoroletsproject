package com.khorolets.dao.impl;

import com.khorolets.dao.ProductDao;
import com.khorolets.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    private static volatile ProductDao productDao;
    private static long generator = 0;
    private Map<Long, Product> map = new HashMap<>();

    private ProductDaoImpl() {
    }

    public static ProductDao getInstance() {
        if (productDao == null) {
            synchronized (ProductDaoImpl.class) {
                if (productDao == null)
                    productDao = new ProductDaoImpl();
            }
        }

        return productDao;
    }

    @Override
    public long saveProduct(Product product) {
        product.setId(generator++);
        map.put(product.getId(), product);

        return product.getId();
    }

    @Override
    public void editProduct(long id, String newName, BigDecimal newPrice) {

        if (map.keySet().contains(id)) {
                map.get(id).setName(newName);
                map.get(id).setPrice(newPrice);
                System.out.println("Product modified with id = " + id);
            }
         else
             System.out.println("Product with id = " + id + "does not exist");
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteProduct(long id) {
        if (map.keySet().contains(id)) {
            map.remove(id);
            System.out.println("Delete product with id = " + id);
        } else
            System.out.println("Product with id = " + id + "does not exist");
    }

    public Product getProductById(long id) {
        return map.get(id);
    }
}