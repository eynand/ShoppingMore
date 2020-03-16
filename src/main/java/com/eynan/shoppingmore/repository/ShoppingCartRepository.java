package com.eynan.shoppingmore.repository;

import com.eynan.shoppingmore.model.data.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,String> {

}
