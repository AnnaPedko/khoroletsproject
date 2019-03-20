package com.khorolets.servlets;

import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;

import javax.servlet.http.HttpServlet;

public class ProductServlet extends HttpServlet {
    private ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }
}
