package com.eynan.shoppingmore.service;

import com.eynan.shoppingmore.model.data.ShoppingCart;
import com.eynan.shoppingmore.model.data.User;
import com.eynan.shoppingmore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired ShoppingCartService shoppingCartService;

    public void createNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        user.setShoppingCart(shoppingCart);
        shoppingCartService.createShoppingCartForUser(shoppingCart);
        userRepository.save(user);



    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getCurrentUser() {
        return userRepository.findByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
    }
}
