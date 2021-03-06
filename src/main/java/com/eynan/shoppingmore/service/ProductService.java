package com.eynan.shoppingmore.service;

import com.eynan.shoppingmore.model.data.Order;
import com.eynan.shoppingmore.model.data.Product;
import com.eynan.shoppingmore.model.data.User;
import com.eynan.shoppingmore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    public void newProduct(Product product){
        productRepository.save(product);
    }

    public void decreaseProductAmount(Product product){
        product.setAmount(product.getAmount() - 1);
        productRepository.save(product);
    }

    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findAll().stream().filter(p->p.getCategory().contains(category)).collect(Collectors.toList());
    }

    public List<Order> getProductOrders(User user) {
        List<Order> productOrders = new ArrayList<>();

        for(Order order : orderService.getAllOrders()) {
            if (order.getProducts().size() > 0 && order.getProducts().get(0).getUser().getUserId().equals(user.getUserId())) {
                productOrders.add(order);
            }

        }
        return productOrders;
    }

    public List<Product> getProductForUser(User user) {
        return productRepository.findByUser(user);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

}
