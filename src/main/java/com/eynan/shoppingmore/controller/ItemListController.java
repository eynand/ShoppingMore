package com.eynan.shoppingmore.controller;

import com.eynan.shoppingmore.model.data.CartItem;
import com.eynan.shoppingmore.model.data.Product;
import com.eynan.shoppingmore.service.OrderService;
import com.eynan.shoppingmore.service.ProductService;
import com.eynan.shoppingmore.service.ShoppingCartService;
import com.eynan.shoppingmore.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Scope(value = "session")
@Component(value = "itemListController")
@Data
public class ItemListController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    private Product selectedItem;
    private CartItem selectedCartItem;
    private List<Product> allProducts;
    private String category;

    public void searchCategory() {
        allProducts = productService.getAllProducts();
        if (category != null ||  ! category.isEmpty()){
            allProducts = allProducts.stream().filter(p -> p.getCategory().contains(category)).collect(Collectors.toList());
        }
    }

    public List<String> getByCategory(String query) {
        return productService.getAllProductsByCategory(query).stream().map(p -> p.getCategory()).distinct().collect(Collectors.toList());
    }

    public void removeCartItem() {
        shoppingCartService.deleteFromCart(selectedCartItem);
    }

    public List<Product> loadData() {

        if (allProducts == null) {
            allProducts = productService.getAllProducts();
        }
        return allProducts;
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


