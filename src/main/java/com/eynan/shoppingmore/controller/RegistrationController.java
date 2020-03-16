package com.eynan.shoppingmore.controller;

import com.eynan.shoppingmore.model.data.User;
import com.eynan.shoppingmore.service.ShoppingCartService;
import com.eynan.shoppingmore.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "registrationController")
@Data
public class RegistrationController {

    @Autowired
    UserService userService;
    @Autowired
    ShoppingCartService shoppingCartService;

    private User user = new User();

    public String registerNewUser() {
        userService.createNewUser(user);
        return "/views/items.xhtml";
    }

    public User getUser() {
        return user;
    }
}
