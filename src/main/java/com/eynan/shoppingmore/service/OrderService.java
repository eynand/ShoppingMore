package com.eynan.shoppingmore.service;

import com.eynan.shoppingmore.Constants;
import com.eynan.shoppingmore.model.data.*;
import com.eynan.shoppingmore.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductService productService;

    public void buyCart(ShoppingCart shoppingCart, User user) {
        HashMap<String,List<Product>> products = new HashMap<>();
        for (CartItem cartItem:shoppingCart.getItems()) {
            User productOwner = cartItem.getProduct().getUser();
            if (products.get(productOwner.getUserId()) == null) {
                List<Product> productList = new ArrayList<>();
                productList.add(cartItem.getProduct());
                products.put(productOwner.getUserId(),productList);
            } else {
                products.get(productOwner.getUserId()).add(cartItem.getProduct());
            }
        }

        for(Map.Entry<String,List<Product>> userProduct:products.entrySet()) {
            Order newOrder = new Order();
            newOrder.setProducts(userProduct.getValue());
            newOrder.setUser(user);
            newOrder.setStatus(Constants.ORDER_STATUS.Pending.name());
            ordersRepository.save(newOrder);
        }
        shoppingCartService.emptyCart(shoppingCart);
    }

    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    public void completeOrder(Order order){
        for (Product product:order.getProducts()){
            product.setAmount(product.getAmount() - 1);
            productService.decreaseProductAmount(product);
        }
        order.setStatus(Constants.ORDER_STATUS.Closed.name());
        ordersRepository.save(order);
    }

    public List<Order> getOrdersByUser(User user) {
        return ordersRepository.findByUser(user);
    }

    public void deleteOrder(Order order) {
        ordersRepository.delete(order);
    }
}
