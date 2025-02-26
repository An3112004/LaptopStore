package com.example.shoplaptop.repository;

import org.springframework.stereotype.Repository;

import com.example.shoplaptop.domain.Role;
import com.example.shoplaptop.domain.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findById(long id);
    User findByEmail(String email);
    List<User> findAll();
    void delete(User user);
    void deleteById(Long id);
    void deleteAll();
    boolean existsByEmail(String email);
}
