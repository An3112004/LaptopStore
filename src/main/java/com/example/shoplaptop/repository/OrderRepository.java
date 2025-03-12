package com.example.shoplaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoplaptop.domain.Order;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order save(Order order);


    
}
