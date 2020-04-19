package com.eynan.shoppingmore.model.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @Column(name = "shopping_cart_id")
    private String shoppingCartId;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<CartItem> items = new HashSet<>();

    @OneToOne(mappedBy = "shoppingCart")
    private User user;

    public ShoppingCart(){
        shoppingCartId = UUID.randomUUID().toString();
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingCartId=" + shoppingCartId +
                '}';
    }

}
