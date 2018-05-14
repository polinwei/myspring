package com.polinwei.myspring.authentication.jwt.security.dao;

import com.polinwei.myspring.authentication.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
}