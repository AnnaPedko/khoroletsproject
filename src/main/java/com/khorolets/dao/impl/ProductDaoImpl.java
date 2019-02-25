package com.khorolets.dao.impl;

import com.khorolets.dao.ProductDao;
import com.khorolets.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    private static long generator = 0;
    private Map<Long, Product> map = new HashMap<>();


    @Override
    public long saveProduct(Product product) {
        product.setId(generator++);
        map.put(product.getId(), product);
        return product.getId();
    }

    @Override
    public void editProduct(long id, String newName, BigDecimal newPrice) {

        for (long key : map.keySet()) {
            if (id == key) {
                map.get(id).setName(newName);
                map.get(id).setPrice(newPrice);
                System.out.println("Product modified with id " + id);
            }
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteProduct(long id) {
        if (map.keySet().contains(id)) {
            map.remove(id);
            System.out.println("Delete client with id " + id);
        }
    }

    public Product getProductById(long id) {
        return map.get(id);
    }
}
