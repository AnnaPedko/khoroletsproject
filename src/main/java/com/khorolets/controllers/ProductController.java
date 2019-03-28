package com.khorolets.controllers;

import com.khorolets.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public String showProducts(ModelMap modelMap) {
        modelMap.put("message", productService.getAllProducts());
        return "products";
    }

    @PostMapping()
    public void createProduct(@RequestParam String name,
                              @RequestParam String price) {
        productService.createProduct(name, new BigDecimal(price));
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam String id, ModelMap modelMap) {
        productService.deleteProduct(Long.parseLong(id));
        modelMap.put("message", "Deleted Client with id = " + id);

        return "products";
    }

    @PutMapping("/edit")
    public String editProduct(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String price,

                              ModelMap modelMap) {
        productService.editProduct(Long.parseLong(id), name, new BigDecimal(price));
        modelMap.put("message", productService.getProductById(Long.parseLong(id)));

        return "products";
    }
}