package com.khorolets.dao.impl;

import com.khorolets.dao.ClientDao;
import com.khorolets.domain.Client;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Service
public class ClientEMDao implements ClientDao {
    private EntityManager entityManager;

    public ClientEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public long saveClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.flush();
        entityManager.getTransaction().commit();

        return client.getId();
    }

    @Override
    public void editClient(long id, String newName, int newAge, String newEmail) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("update Client set name = :name, age =:age, email = :email " +
                " where id = :id");
        query.setParameter("id", id);
        query.setParameter("name", newName);
        query.setParameter("age", newAge);
        query.setParameter("email", newEmail);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteClient(long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Client where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> resultlist = entityManager.createQuery("from Client", Client.class).getResultList();

        return resultlist;
    }

    @Override
    public long getClientId(String phone) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Client where phone =:phone");
        query.setParameter("phone", phone);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        List<Client> client = query.getResultList();

        return client.get(0).getId();

    }

    @Override
    public boolean hasPhone(String phone) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Client where phone =:phone");
        query.setParameter("phone", phone);
        query.executeUpdate();
        entityManager.getTransaction().commit();

        return query.getResultList().size() != 0;
    }

    @Override
    public Client getClientById(long clientId) {
        entityManager.getTransaction().begin();
        Client client = entityManager.find(Client.class, clientId);
        entityManager.getTransaction().commit();

        return client;
    }
}