package com.khorolets.servlets.Filter;

import com.khorolets.exception.BusinessException;
import com.khorolets.validators.ValidationService;
import com.khorolets.validators.impl.ValidationServiceImpl;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter(urlPatterns = "/clients")
public class ClientFilter implements javax.servlet.Filter {
    private ValidationService validationService;

    public ClientFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        validationService = new ValidationServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String age = servletRequest.getParameter("age");
        try {
            validationService.validateAge(Integer.parseInt(age));

        } catch (NumberFormatException | BusinessException exception) {
            PrintWriter writer = servletResponse.getWriter();
            writer.println("<h2> WRONG AGE </h2>");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}