package com.khorolets.domain;

import java.util.List;

public class Order {
    List<Product> products;
    private long id;
    private Client client;

    public Order(Client client, List<Product> products) {
        this.client = client;
        this.products = products;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}