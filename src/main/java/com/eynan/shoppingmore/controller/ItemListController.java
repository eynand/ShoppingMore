package com.eynan.shoppingmore.controller;

import com.eynan.shoppingmore.model.data.Item;
import com.eynan.shoppingmore.repository.ItemRepository;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "itemList")
@ELBeanName(value = "itemList")
@Join(path = "/", to = "/views/items.jsf")
public class ItemListController {
    @Autowired
    private ItemRepository itemRepository;

    private List<Item> items;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        items = itemRepository.findAll();
    }

    public List<Item> getItem() {
        return items;
    }
}


