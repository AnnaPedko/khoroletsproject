package com.khorolets.dao.impl;

import com.khorolets.dao.ProductDao;
import com.khorolets.domain.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductEMDao implements ProductDao {
    private EntityManager entityManager;

    public ProductEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public long saveProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.flush();
        entityManager.getTransaction().commit();

        return product.getId();
    }

    @Override
    public void editProduct(long id, String newName, BigDecimal newPrice) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("update Product set name = :name, price =:price where id = :id");
        query.setParameter("id", id);
        query.setParameter("name", newName);
        query.setParameter("price", newPrice);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Product> getAllProducts() {
        entityManager.getTransaction().begin();
        List<Product> resultList = entityManager.createQuery("from Product", Product.class).getResultList();
        entityManager.getTransaction().commit();

        return resultList;
    }

    @Override
    public void deleteProduct(long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Product where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public Product getProductById(long id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.getTransaction().commit();

        return product;
    }
}