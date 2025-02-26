package com.example.shoplaptop.controller.client;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoplaptop.domain.Product;
import com.example.shoplaptop.service.ProductService;


@Controller
public class ItemController {

    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/client/product/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Optional<Product> product = this.productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        return "client/product/detail";
    }
}
