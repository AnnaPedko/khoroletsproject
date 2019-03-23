package com.khorolets.servlets;

import com.khorolets.domain.Client;
import com.khorolets.services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientServlet extends HttpServlet {
    private ClientService clientService;

    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
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

        for (Client client : clientService.getAllClients()) {
            writer.println("<h1>" + client + "</h1>");
            writer.println("<br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryType = req.getParameter("queryType");

        if (queryType.equals("edit"))
            editClient(req);
        else if (queryType.equals("create"))
            createClient(req);
        else {
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("Not allowed command: " + queryType);
        }

        doGet(req, resp);
    }

    private void createClient(HttpServletRequest req) {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        String age = req.getParameter("age");
        String email = req.getParameter("email");

        clientService.createClient(name, surname, Integer.parseInt(age), phone, email);
    }

    private void editClient(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String email = req.getParameter("email");

        clientService.editClient(Long.parseLong(id), name, Integer.parseInt(age), email);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        clientService.deleteClient(Long.parseLong(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        editClient(req);
    }
}