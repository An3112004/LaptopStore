package com.example.shoplaptop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoplaptop.domain.Cart;
import com.example.shoplaptop.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
    Cart findByUser(User user);

    Cart save(Cart cart);

}
