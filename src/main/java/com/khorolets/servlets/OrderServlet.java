package com.khorolets.servlets;

import com.khorolets.domain.Order;
import com.khorolets.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    public OrderServlet(OrderService orderService) {
        this.orderService = orderService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if("/delete".equals(pathInfo))
        {
            doDelete(req,resp);
            return;
        }
        String clientId = req.getParameter("clientid");

        if (clientId!=null ) {
            printOrders(resp, orderService.getOrdersByClientId(Long.parseLong(clientId)));
        }
        else
            printOrders(resp, orderService.getAllOrders());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createOrder(req);

        doGet(req, resp);
    }

    private void createOrder(HttpServletRequest req)
    {
        ArrayList<Long>productsId = new ArrayList<>();

        Long id = Long.parseLong(req.getParameter("id"));
        String products = req.getParameter("products");

        for (String productId : products.split(",")) {
            productsId.add(Long.parseLong(productId));
        }
        orderService.orderProducts(id, productsId);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientId =req.getParameter("clientid");
        String orderId =req.getParameter("orderid");

        orderService.deleteOrdersByClientId(Long.parseLong(clientId), Long.parseLong(orderId));

        //doGet(req, resp);
    }
    private void printOrders(HttpServletResponse resp, List<Order> orders) throws IOException
    {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        for (Order order : orders) {
            writer.println("<h1>" + order + "</h1>");
            writer.println("<br>");
        }
    }

}