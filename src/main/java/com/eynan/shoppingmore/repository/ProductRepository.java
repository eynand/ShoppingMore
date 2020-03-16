package com.eynan.shoppingmore.repository;

import com.eynan.shoppingmore.model.data.Product;
import com.eynan.shoppingmore.model.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByUser(User user);
}
