package com.khorolets.services.impl;

import com.khorolets.dao.ProductDao;
import com.khorolets.domain.Product;
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

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Mock
    private ValidationService validationService;
    @Mock
    private ProductDao productDao;

    private ProductService productService;

    @Before
    public void init() {
        productService = new ProductServiceImpl(productDao, validationService);
    }

    @Test
    public void createProduct() {
        //GIVEN
        long id = 1;
        String name = "Test";
        BigDecimal price = new BigDecimal(100);

        Product product = new Product(name, price);
        Mockito.when(productDao.saveProduct(product)).thenReturn(id);

        //WHEN
        long res = productService.createProduct(name, price);

        //THEN
        Assert.assertEquals(id, res);
    }

    @Test
    public void editProduct() {
        //GIVEN
        long id = 1;
        String name = "Test";
        BigDecimal price = new BigDecimal(100);

        //WHEN
        productService.editProduct(id, name, price);

        //THEN
        Mockito.verify(productDao, Mockito.times(1)).editProduct(id, name, price);
    }

    @Test
    public void getAllProducts() {
        productService.getAllProducts();
        Mockito.verify(productDao, Mockito.times(1)).getAllProducts();
    }

    @Test
    public void deleteProduct() {
        long id = 1;
        productService.deleteProduct(id);
        Mockito.verify(productDao, Mockito.times(1)).deleteProduct(id);
    }

    @Test
    public void getProductById() {
        //GIVEN
        long id = 1;
        String name = "Test";
        BigDecimal price = new BigDecimal(100);

        Product expectedProduct = new Product(id, name, price);
        Mockito.when(productDao.getProductById(id)).thenReturn(expectedProduct);

        //WHEN
        Product product = productService.getProductById(1);

        //THEN
        Mockito.verify(productDao).getProductById(1);
        Mockito.verifyNoMoreInteractions(productDao);
        Assert.assertEquals(expectedProduct, product);
    }
}