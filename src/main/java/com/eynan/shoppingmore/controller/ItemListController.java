package com.eynan.shoppingmore.controller;

import com.eynan.shoppingmore.model.data.CartItem;
import com.eynan.shoppingmore.model.data.Product;
import com.eynan.shoppingmore.model.data.ShoppingCart;
import com.eynan.shoppingmore.repository.ProductRepository;
import com.eynan.shoppingmore.service.OrderService;
import com.eynan.shoppingmore.service.ShoppingCartService;
import com.eynan.shoppingmore.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Scope(value = "session")
@Component(value = "itemList")
@Data
public class ItemListController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository itemRepository;
    private Product selectedItem;


    public List<Product>  loadData() {
        return itemRepository.findAll();
    }

    public void addToCart() {
        shoppingCartService.addToCart(selectedItem,userService.getCurrentUser());
    }

    public int getCartCount(){
        return userService.getCurrentUser().getShoppingCart().getItems().size();
    }

    public Set<CartItem> getShoppingCartItems(){
        return userService.getCurrentUser().getShoppingCart().getItems();
    }

    public void buyItems(){
        orderService.buyCart(userService.getCurrentUser().getShoppingCart(),userService.getCurrentUser());
    }
}


