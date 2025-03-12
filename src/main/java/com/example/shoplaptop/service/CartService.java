package com.example.shoplaptop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoplaptop.domain.Cart;
import com.example.shoplaptop.domain.CartDetail;
import com.example.shoplaptop.domain.OrderDetail;
import com.example.shoplaptop.domain.Order;
import com.example.shoplaptop.domain.User;
import com.example.shoplaptop.repository.CartDetailRepository;
import com.example.shoplaptop.repository.CartRepository;
import com.example.shoplaptop.repository.OrderDetailRepository;
import com.example.shoplaptop.repository.OrderRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService {

    @Autowired
    private CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public CartService(CartDetailRepository cartDetailRepository , CartRepository cartRepository , OrderRepository orderRepository , OrderDetailRepository orderDetailRepository){
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public void updateCartDetail(Long cartDetailId, Long quantity) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new RuntimeException("CartDetail not found"));

        if (quantity > 0) {
            cartDetail.setQuantity(quantity);
            cartDetailRepository.save(cartDetail);
        } else {
            cartDetailRepository.delete(cartDetail);
        }
    }

    public void handleRemoveCartDetail(Long cartDetailId , HttpSession session){
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();

            Cart currentCart = cartDetail.getCart();
            // delete cart-detail
            this.cartDetailRepository.deleteById(cartDetailId);

            // update cart
            if (currentCart.getSum() > 1) {
                // update current cart
                long s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                // delete cart (sum = 1)
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    public Cart fetchByUser(User user){
        return this.cartRepository.findByUser(user);
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(
            User user, HttpSession session,
            String receiverName, String receiverAddress, String receiverPhone) {

        // step 1: get cart by user
        Cart cart = this.cartRepository.findByUser(user);
        if (cart == null) {
            throw new RuntimeException("Cart not found for user: " + user.getId());
        }
        
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails == null || cartDetails.isEmpty()) {
                throw new RuntimeException("Cart is empty");
            }

            if (cartDetails != null) {

                // create order
                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");

                double sum = 0;
                for (CartDetail cd : cartDetails) {
                    sum += cd.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                // create orderDetail

                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());

                    this.orderDetailRepository.save(orderDetail);
                }

                // step 2: delete cart_detail and cart
                // for (CartDetail cd : cartDetails) {
                //     this.cartDetailRepository.deleteById(cd.getId());
                // }

                this.cartRepository.delete(cart);

                // step 3 : update session
                session.setAttribute("sum", 0);
            }
        }
    }
}

