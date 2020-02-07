package com.eynan.shoppingmore.model.data;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "item")
@Data
public class Item {
    @Id
    @Column(name = "item_id")
    private String itemId;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    private double amount;

    public Item(){
        itemId = UUID.randomUUID().toString();
    }

}

