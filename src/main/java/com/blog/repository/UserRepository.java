package com.blog.repository;

import com.blog.entity.Role;
import com.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username,String  email);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
