package com.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.user = :user")
    List<CartItem> findCartItemsByUser(@Param("user") User user);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product.id = :productId")
    Optional<CartItem> findCartItemByCartAndProduct(@Param("cart") Cart cart, @Param("productId") Long productId);

	CartItem save(CartItem existingCartItem);

	void delete(CartItem cartItem);
}

