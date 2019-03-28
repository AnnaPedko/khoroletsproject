package com.khorolets.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDERS")
public class Order {
    @ManyToMany
    @JoinTable(
            name = "ORDER_ITEM",
            joinColumns = @JoinColumn(
                    name = "ORDER_ID",
                    referencedColumnName = "ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "PRODUCT_ID",
                    referencedColumnName = "ID"
            )
    )
    List<Product> products;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @OneToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    private Client client;

    public Order() {

    }

    public Order(Client client, List<Product> products) {
        this.client = client;
        this.products = products;
    }

    @Autowired
    public Order(long id, Client client, List<Product> products) {
        this.id = id;
        this.client = client;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(products, order.products) &&
                Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {

        return Objects.hash(products, id, client);
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

    @Override
    public String toString() {
        return "Order{" +
                "\nid= " + id + "," +
                "\nclient=" + client + "," +
                "\nproducts=" + products +
                '}';
    }
}