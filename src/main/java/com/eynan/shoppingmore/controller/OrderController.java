package com.eynan.shoppingmore.controller;

import com.eynan.shoppingmore.Constants;
import com.eynan.shoppingmore.model.data.Order;
import com.eynan.shoppingmore.service.OrderService;
import com.eynan.shoppingmore.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Scope(value = "session")
@Component(value = "ordersController")
@Data
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    private Order selectedOrder;

    public List<Order> getOrders() {
        return orderService.getOrdersByUser(userService.getCurrentUser());
    }

    public long getPendingOrders(){
        return orderService.getOrdersByUser(userService.getCurrentUser()).stream().filter(order -> order.getStatus().equals(Constants.ORDER_STATUS.Pending.name())).count();
    }

    public void completeOrder(){
        try {
            orderService.completeOrder(selectedOrder);
        }catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("Critical",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error To Complete Order - " + ex.getMessage(),ex.getMessage()));
        }
    }

    public void deleteOrder(){
        orderService.deleteOrder(selectedOrder);
    }
}
