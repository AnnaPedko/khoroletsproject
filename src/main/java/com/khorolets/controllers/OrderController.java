package com.khorolets.controllers;

import com.khorolets.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    public OrderController() {
    }

    @GetMapping()
    public String showOrders(@RequestParam(required = false) String id, ModelMap modelMap) {
        if (id != null)
            modelMap.put("message", orderService.getOrdersByClientId(Long.parseLong(id)));
        else
            modelMap.put("message", orderService.getAllOrders());

        return "orders";
    }

    @GetMapping("/delete")
    public String deleteOrdersById(@RequestParam String clientid,
                                   @RequestParam String id) {
        orderService.deleteOrdersByClientId(Long.parseLong(clientid), Long.parseLong(id));
        return "orders";
    }

    @PostMapping()
    public void OrderProduct(@RequestParam String id,
                             @RequestParam String products,
                             ModelMap modelMap) {
        ArrayList<Long> productsId = new ArrayList<>();

        for (String productId : products.split(",")) {
            productsId.add(Long.parseLong(productId));
        }

        orderService.orderProducts(Long.parseLong(id), productsId);
    }
}