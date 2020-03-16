package com.eynan.shoppingmore.repository;

import com.eynan.shoppingmore.model.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
