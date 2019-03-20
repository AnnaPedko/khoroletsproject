package com.khorolets.servlets;

import com.khorolets.domain.Product;
import com.khorolets.services.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class ProductServlet extends HttpServlet {
    private ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if ("/delete".equals(pathInfo)) {
            doDelete(req, resp);
            return;
        } else if ("/put".equals(pathInfo)) {
            doPut(req, resp);
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        for (Product product : productService.getAllProducts()) {
            writer.println("<h1>" + product + "</h1>");
            writer.println("<br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String queryType = req.getParameter("queryType");

        if (queryType.equals("edit"))
            editProduct(req);
        else if (queryType.equals("create"))
            createProduct(req);
        else {
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("Not allowed command: " + queryType);
        }

        doGet(req, resp);
    }

    private void createProduct(HttpServletRequest req) {
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        productService.createProduct(name, new BigDecimal(price));
    }

    private void editProduct(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        productService.editProduct(Long.parseLong(id), name, new BigDecimal(price));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        productService.deleteProduct(Long.parseLong(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        editProduct(req);
    }
}