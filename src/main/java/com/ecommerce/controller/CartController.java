package com.ecommerce.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    private User getAuthenticatedUser(Principal principal) {
        return userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(Principal principal) {
        User user = getAuthenticatedUser(principal);
        Cart cart = cartService.getCart(user);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(Principal principal, @RequestParam Long productId, @RequestParam int quantity) {
        User user = getAuthenticatedUser(principal);
        CartItem cartItem = cartService.addCartItem(user, productId, quantity);
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(Principal principal, @PathVariable Long cartItemId, @RequestParam int quantity) {
        User user = getAuthenticatedUser(principal);
        try {
            CartItem updatedCartItem = cartService.updateCartItem(user, cartItemId, quantity);
            return ResponseEntity.ok(updatedCartItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(Principal principal, @PathVariable Long cartItemId) {
        User user = getAuthenticatedUser(principal);
        try {
            cartService.removeCartItem(user, cartItemId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(Principal principal) {
        User user = getAuthenticatedUser(principal);
        cartService.clearCart(user);
        return ResponseEntity.ok().build();
    }
}
