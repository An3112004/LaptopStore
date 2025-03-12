package com.example.shoplaptop.controller.cart;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shoplaptop.domain.Cart;
import com.example.shoplaptop.domain.CartDetail;
import com.example.shoplaptop.domain.User;
import com.example.shoplaptop.repository.UserRepository;
import com.example.shoplaptop.service.CartService;
import com.example.shoplaptop.service.ProductService;
import com.example.shoplaptop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    public CartController(CartService cartService , ProductService productService , UserService userService){
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") Long id , HttpServletRequest request) {
        long productId = id;
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        this.productService.addToCart(productId , email , session);
        
        return "redirect:/";
    }

    @GetMapping("/client/cart")
    public String showCart(Model model , HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = new User();
        Long id = (Long) session.getAttribute("id");
        user.setId(id);

        Cart cart = this.productService.getCartByUser(user);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        long totalPrice = 0;
        for (CartDetail cartDetail : cartDetails) {
            totalPrice += cartDetail.getQuantity() * cartDetail.getProduct().getPrice();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }

    @PostMapping("/cart/update")
    public String updateCart(@RequestParam("cartDetailId") Long cartDetailId,
                             @RequestParam("quantity") Long quantity,
                             RedirectAttributes redirectAttributes) {
        cartService.updateCartDetail(cartDetailId, quantity);
        return "redirect:/client/cart";  // Sau khi cập nhật, load lại trang giỏ hàng
    }

    @PostMapping("/cart/delete-cart-product/{id}")
    public String deleteCartItem(@PathVariable("id") Long id , HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long cartDetailId = id;
        this.cartService.handleRemoveCartDetail(id , session);
        
        return "redirect:/client/cart";
    }

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        Optional<User> currentUser = this.userService.getUserById(id);
        if(currentUser!=null){
            Cart cart = this.cartService.fetchByUser(currentUser.get());
            List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        }
        return "client/cart/checkout";
    }

    @PostMapping("/confirm-checkout")
    public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.cartService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
            HttpServletRequest request,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        Optional<User> currentUser = this.userService.getUserById(id);
        if (currentUser!=null){
            this.cartService.handlePlaceOrder(currentUser.get(), session, receiverName, receiverAddress, receiverPhone);
        }

        return "redirect:/thanks";
    }

    @GetMapping("/thanks")
    public String getThankYouPage(Model model) {

        return "client/cart/thanks";
    }
}
