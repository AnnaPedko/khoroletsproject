package com.khorolets.domain;

import java.util.List;

public class Order {
    private long id;
    private Client client;
    List<Product> products;

    public Order(Client client, List<Product> products) {
        this.client = client;
        this.products = products;
    }


    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
