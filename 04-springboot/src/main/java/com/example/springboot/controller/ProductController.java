package com.example.springboot.controller;

import com.example.springboot.model.Product;
import org.springframework.web.bind.annotation.*;

/**
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping
    public Product getProduct() {
        return new Product(1, "Product 1", 19.99);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        System.out.println(product);
        return product;
    }
}
