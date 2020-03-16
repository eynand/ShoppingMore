package com.eynan.shoppingmore.repository;

import com.eynan.shoppingmore.model.data.Order;
import com.eynan.shoppingmore.model.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Order, String> {

    List<Order> findByUser(User user);
}
