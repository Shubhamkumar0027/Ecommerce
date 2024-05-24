package com.ecommerce.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Order;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderService;

import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String createOrder(Order orderDto, Model model) {
        Order order = orderService.saveOrder(orderDto);
        if (order != null) {
            model.addAttribute("order", order);
            return "orderConfirmation";
        } else {
            model.addAttribute("error", "There was an error processing your order.");
            return "checkout";
        }
    }
}
