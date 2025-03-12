package com.example.shoplaptop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shoplaptop.domain.Cart;
import com.example.shoplaptop.domain.CartDetail;
import com.example.shoplaptop.domain.Product;
import com.example.shoplaptop.domain.User;
import com.example.shoplaptop.repository.CartDetailRepository;
import com.example.shoplaptop.repository.CartRepository;
import com.example.shoplaptop.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository
            , CartRepository cartRepository
            , CartDetailRepository cartDetailRepository
            , UserService userService) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Cart getCartByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void addToCart(Long productId , String email ,HttpSession session) {
        // kiểm tra user đã có cart chưa
        User user = this.userService.getUserByEmail(email);
        if(user != null) {
            // check user đã có cart chưa , nếu chưa -> tạo cart
            Cart cart = this.cartRepository.findByUser(user);

            if(cart == null) {
                // tạo mới cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum((long) 0);
                cart = this.cartRepository.save(newCart);
            }

            // tạo mới cartDetail
            // Tìm product
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if(productOptional.isPresent()) {
                Product product = productOptional.get();

                // kiểm tra product đã có trong cart chưa
                CartDetail oldCartDetail = this.cartDetailRepository.findByProductAndCart(product, cart);

                if(oldCartDetail == null){
                    CartDetail newCartDetail = new CartDetail();
                    newCartDetail.setCart(cart);
                    newCartDetail.setProduct(product);
                    newCartDetail.setPrice(product.getPrice());
                    newCartDetail.setQuantity((long) 1);

                    this.cartDetailRepository.save(newCartDetail);
                    
                    // cập nhật tổng số lượng sản phẩm trong cart
                    long sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", sum);
                }
                else {
                    oldCartDetail.setQuantity(oldCartDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldCartDetail);
                }
            }
        }
    }
}
