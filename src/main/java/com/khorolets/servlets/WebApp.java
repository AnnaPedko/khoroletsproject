package com.khorolets.servlets;

import com.khorolets.dao.ClientDao;
import com.khorolets.dao.OrderDao;
import com.khorolets.dao.ProductDao;
import com.khorolets.dao.impl.ClientDBDao;
import com.khorolets.dao.impl.OrderDBDao;
import com.khorolets.dao.impl.ProductDBDao;
import com.khorolets.services.ClientService;
import com.khorolets.services.OrderService;
import com.khorolets.services.ProductService;
import com.khorolets.services.impl.ClientServiceImpl;
import com.khorolets.services.impl.OrderServiceImpl;
import com.khorolets.services.impl.ProductServiceImpl;
import com.khorolets.validators.ValidationService;
import com.khorolets.validators.impl.ValidationServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ClientDao clientDao = ClientDBDao.getInstance();
        ProductDao productDao = ProductDBDao.getInstance();
        OrderDao orderDao = OrderDBDao.getInstance();

        ValidationService validationService = new ValidationServiceImpl();

        ClientService clientService = new ClientServiceImpl(clientDao, validationService );
        ProductService productService = new ProductServiceImpl(productDao,validationService);
        OrderService orderService= new OrderServiceImpl(orderDao,validationService,productService,clientService);

        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("ClientServlet", new ClientServlet(clientService)).addMapping("/clients/*");
        servletContext.addServlet("ProductServlet", new ProductServlet(productService)).addMapping("/products/*");
        servletContext.addServlet("OrderServlet", new OrderServlet(orderService)).addMapping("/orders/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
