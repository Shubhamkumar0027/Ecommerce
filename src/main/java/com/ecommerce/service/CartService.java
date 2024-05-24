package com.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCart(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setCartItems(new HashSet<>());
            return cartRepository.save(newCart);
        });
    }

    public CartItem addCartItem(User user, Long productId, int quantity) {
        Cart cart = getCart(user);
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            Optional<CartItem> existingCartItemOptional = cartRepository.findCartItemByCartAndProduct(cart, productId);
            if (existingCartItemOptional.isPresent()) {
                CartItem existingCartItem = existingCartItemOptional.get();
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                return cartRepository.save(existingCartItem);
            } else {
                CartItem newCartItem = new CartItem();
                newCartItem.setCart(cart);
                newCartItem.setProduct(product);
                newCartItem.setQuantity(quantity);
                cart.getCartItems().add(newCartItem);
                cartRepository.save(cart);
                return newCartItem;
            }
        } else {
            throw new RuntimeException("Product not found with id " + productId);
        }
    }

    public CartItem updateCartItem(User user, Long cartItemId, int quantity) {
        Optional<CartItem> cartItemOptional = cartRepository.findById(cartItemId).map(Cart::getCartItems)
                .flatMap(items -> items.stream().filter(item -> item.getId().equals(cartItemId)).findFirst());
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            if (cartItem.getCart().getUser().equals(user)) {
                cartItem.setQuantity(quantity);
                return cartRepository.save(cartItem);
            } else {
                throw new RuntimeException("User not authorized to update this cart item");
            }
        } else {
            throw new RuntimeException("Cart item not found with id " + cartItemId);
        }
    }

    public void removeCartItem(User user, Long cartItemId) {
        Optional<CartItem> cartItemOptional = cartRepository.findById(cartItemId).map(Cart::getCartItems)
                .flatMap(items -> items.stream().filter(item -> item.getId().equals(cartItemId)).findFirst());
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            if (cartItem.getCart().getUser().equals(user)) {
                cartItem.getCart().getCartItems().remove(cartItem);
                cartRepository.delete(cartItem);
            } else {
                throw new RuntimeException("User not authorized to remove this cart item");
            }
        } else {
            throw new RuntimeException("Cart item not found with id " + cartItemId);
        }
    }

    public void clearCart(User user) {
        Cart cart = getCart(user);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

	public List<CartItem> getCartItems() {
		// TODO Auto-generated method stub
		return null;
	}
}
