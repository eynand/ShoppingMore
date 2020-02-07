package com.eynan.shoppingmore.model.data;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private long price;
    @Column
    private long amount;

}

