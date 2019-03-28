package com.khorolets.dao.impl;

import com.khorolets.dao.OrderDao;
import com.khorolets.domain.Order;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Service
public class OrderEMDao implements OrderDao {
    private EntityManager entityManager;

    public OrderEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public long orderProducts(Order order) {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.flush();
        entityManager.getTransaction().commit();

        return order.getId();
    }

    public List<Order> getAllOrdersList() {
        List<Order> resultList = entityManager.createQuery("from Order", Order.class).getResultList();

        return resultList;
    }

    @Override
    public List<long[]> getAllOrders() {
        return null;
    }

    @Override
    public List<long[]> getOrdersByClientId(long clientId) {
        return null;
    }

    @Override
    public List<Order> getOrdersByClientIdList(long clientId) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Order where CLIENT_ID=:id", Order.class);
        query.setParameter("id", clientId);
        entityManager.getTransaction().commit();

        return query.getResultList();
    }

    @Override
    public void deleteOrdersByClientId(long clientId, long orderId) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Order where CLIENT_ID=:clientId AND ID=:orderId ");
        query.setParameter("clientId", clientId);
        query.setParameter("orderId", orderId);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}