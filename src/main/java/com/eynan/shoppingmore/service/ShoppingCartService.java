package com.eynan.shoppingmore.service;

import com.eynan.shoppingmore.model.data.CartItem;
import com.eynan.shoppingmore.model.data.Product;
import com.eynan.shoppingmore.model.data.ShoppingCart;
import com.eynan.shoppingmore.model.data.User;
import com.eynan.shoppingmore.repository.CartItemRepository;
import com.eynan.shoppingmore.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    public ShoppingCart createShoppingCartForUser(ShoppingCart shoppingCart){
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public void deleteFromCart(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public void addToCart(Product product, User user) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItemRepository.save(cartItem);
        //shoppingCartRepository.save(user.getShoppingCart());
    }

    public void emptyCart(ShoppingCart cart) {
        cart.getItems().stream().forEach(cartItem -> cartItemRepository.delete(cartItem));
        cart.getItems().clear();
    }
}
