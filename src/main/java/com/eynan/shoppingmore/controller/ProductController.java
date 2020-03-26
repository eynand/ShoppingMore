package com.eynan.shoppingmore.controller;

import com.eynan.shoppingmore.model.data.Order;
import com.eynan.shoppingmore.model.data.Product;
import com.eynan.shoppingmore.service.ProductService;
import com.eynan.shoppingmore.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "productController")
@Data
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    private Product product = new Product();


    public String save() {
        product.setUser(userService.getCurrentUser());
        productService.newProduct(product);
        product = new Product();
        return "/views/home.xhtml?faces-redirect=true";
    }


    public String newProduct(){
        return "/form/product-form.xhtml";
    }

    public List<Order> getProductOrders(){
        return productService.getProductOrders(userService.getCurrentUser());
    }

    public List<Product> getProducts(){
        return productService.getProductForUser(userService.getCurrentUser());
    }

    public void deleteProduct() {
        productService.deleteProduct(product);
    }

    public Product getProduct() {
        return product;
    }
}