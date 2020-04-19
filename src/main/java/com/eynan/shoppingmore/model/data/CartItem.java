package com.eynan.shoppingmore.model.data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @Column(name = "cart_item_id")
    private String cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

    public CartItem() {
        cartItemId = UUID.randomUUID().toString();
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                '}';
    }
}
