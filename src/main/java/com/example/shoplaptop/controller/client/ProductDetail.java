package com.example.shoplaptop.controller.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shoplaptop.domain.Cart;
import com.example.shoplaptop.domain.CartDetail;
import com.example.shoplaptop.domain.Product;
import com.example.shoplaptop.domain.User;
import com.example.shoplaptop.service.CartService;
import com.example.shoplaptop.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ProductDetail {

    private final ProductService productService;
    private final CartService cartService;

    public ProductDetail(ProductService productService , CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
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
