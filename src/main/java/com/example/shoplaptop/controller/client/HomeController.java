package com.example.shoplaptop.controller.client;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.shoplaptop.domain.Product;
import com.example.shoplaptop.domain.User;
import com.example.shoplaptop.domain.dto.RegisterDTO;
import com.example.shoplaptop.service.ProductService;
import com.example.shoplaptop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class HomeController {
    
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomeController(ProductService productService , UserService userService , PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/home/show";
    }

    @GetMapping("/register")
    public String about(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String postMethodName(@ModelAttribute("registerUser") @Valid RegisterDTO registerUser,
                                 BindingResult result) {

        if (result.hasErrors()) {
            return "client/auth/register";
        }

        User user = userService.registerDTOtoUser(registerUser);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(userService.getRoleByName("USER"));
        this.userService.saveUser(user);
        return "client/auth/login";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        return "client/auth/login";
    }

    @PostMapping("/login")
    public String postMethodName(@ModelAttribute("loginUser") User loginUser) {
        return "client/home/show";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "client/auth/403";
    }
    
}
