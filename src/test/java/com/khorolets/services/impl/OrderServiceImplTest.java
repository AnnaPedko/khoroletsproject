package com.khorolets.services.impl;

import com.khorolets.dao.OrderDao;
import com.khorolets.domain.Client;
import com.khorolets.domain.Order;
import com.khorolets.domain.Product;
import com.khorolets.services.ClientService;
import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;
import com.khorolets.validators.ValidationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @Mock
    private ValidationService validationService;
    @Mock
    private ClientService clientService;
    @Mock
    private ProductService productService;
    @Mock
    private OrderDao orderDao;

    private OrderService orderService;

    @Before
    public void init() {
        orderService = new OrderServiceImpl(orderDao, validationService, productService, clientService);
    }

    @Test
    public void orderProducts() {
        long id = 1;
        String name = "Test";
        String surname = "Test";
        int age = 10;
        String phone = "+380501111111";
        String email = "test@test.com";

        long productId = 1;
        long productId2 = 2;
        Long[] productIds = {productId, productId2};

        String firstName = "firstName";
        String secondName = "secondName";
        BigDecimal firstPrice = new BigDecimal(100);
        BigDecimal secondPrice = new BigDecimal(200);
        Product expectedProduct1 = new Product(productId, firstName, firstPrice);
        Product expectedProduct2 = new Product(productId2, secondName, secondPrice);
        Product[] products = {expectedProduct1, expectedProduct2};

        Client expectedClient = new Client(id, name, surname, age, phone, email);

        Mockito.when(productService.getProductById(productId)).thenReturn(expectedProduct1);
        Mockito.when(productService.getProductById(productId2)).thenReturn(expectedProduct2);
        Mockito.when(clientService.getClientById(id)).thenReturn(expectedClient);

        orderService.orderProducts(expectedClient.getId(), new ArrayList<>(Arrays.asList(productIds)));

        Order order = new Order(expectedClient, new ArrayList<>(Arrays.asList(products)));
        Mockito.verify(orderDao, Mockito.times(1)).orderProducts(order);
    }

    @Test
    public void getAllOrders() {
        orderService.getAllOrders();
        Mockito.verify(orderDao, Mockito.times(1)).getAllOrders();
    }

    @Test
    public void getOrdersByClientId() {
        long orderId = 1;

        long clientId = 1;
        String name = "Test";
        String surname = "Test";
        int age = 10;
        String phone = "+380501111111";
        String email = "test@test.com";

        long productId = 1;

        String firstName = "firstName";
        BigDecimal firstPrice = new BigDecimal(100);
        Product expectedProduct = new Product(productId, firstName, firstPrice);
        Product[] products = {expectedProduct};

        Client expectedClient = new Client(clientId, name, surname, age, phone, email);

        Order order = new Order(orderId, expectedClient, new ArrayList<>(Arrays.asList(products)));
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        List<long[]> orderTable = new ArrayList<>();
        orderTable.add(new long[]{orderId, expectedClient.getId(), expectedProduct.getId()});

        Mockito.when(orderDao.getOrdersByClientId(clientId)).thenReturn(orderTable);
        Mockito.when(clientService.getClientById(clientId)).thenReturn(expectedClient);
        Mockito.when(productService.getProductById(productId)).thenReturn(expectedProduct);

        List<Order> actOrderList = orderService.getOrdersByClientId(clientId);

        Assert.assertEquals(orderList, actOrderList);
    }

    @Test
    public void deleteOrdersByClientId() {
        long clientId = 1;
        long id = 1;

        orderService.deleteOrdersByClientId(clientId, id);
        Mockito.verify(orderDao, Mockito.times(1)).deleteOrdersByClientId(clientId, id);
    }

}