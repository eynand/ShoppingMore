package com.eynan.shoppingmore.controller;

import com.eynan.shoppingmore.model.data.User;
import com.eynan.shoppingmore.service.ShoppingCartService;
import com.eynan.shoppingmore.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@Scope(value = "session")
@Component(value = "registrationController")
@Data
public class RegistrationController {

    @Autowired
    UserService userService;
    @Autowired
    ShoppingCartService shoppingCartService;

    private User user = new User();

    public void validateEmail(FacesContext context, UIComponent comp,
                             Object value) {
        String mno = (String) value;
        if (!isValidEmailAddress(mno) || mno.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "invalid email address", "invalid email address"));
        }
    }

    public void validatePassword(FacesContext context, UIComponent comp,
                                 Object value) {
        String mno = (String) value;
        if (mno.length() < 8 || mno.length() > 32  || mno.isEmpty()) {
            ((UIInput) comp).setValid(false);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"invalid password, should be 8-32 chars","invalid password"));
        }
    }

    public void validateUserName(FacesContext context, UIComponent comp,
                                 Object value) {
        String mno = (String) value;
        if (mno.length() < 6 || mno.length() > 32  || mno.isEmpty()) {
            ((UIInput) comp).setValid(false);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"invalid username, should be 6-32 chars","invalid username"));
        }
    }

    public String registerNewUser() {
        if (userService.findByUsername(user.getUsername()) != null ) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"username already exist","username already exist"));
            return "";
        }
        userService.createNewUser(user);
        return "/views/items.xhtml";
    }

    public User getUser() {
        return user;
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
