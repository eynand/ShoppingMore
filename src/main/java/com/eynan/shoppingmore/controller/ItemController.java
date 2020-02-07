package com.eynan.shoppingmore.controller;

import com.eynan.shoppingmore.model.data.Item;
import com.eynan.shoppingmore.repository.ItemRepository;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "itemController")
@ELBeanName(value = "itemController")
@Join(path = "/item", to = "/form/item-form.jsf")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    private Item item = new Item();

    public String save() {
        itemRepository.save(item);
        item = new Item();
        return "/items.xhtml?faces-redirect=true";
    }

    public Item getItem() {
        return item;
    }
}