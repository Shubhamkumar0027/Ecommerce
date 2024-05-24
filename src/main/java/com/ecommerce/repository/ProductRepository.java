package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Product;

//ProductRepository.java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
 List<Product> findByCategory(String category);
 List<Product> findByNameContaining(String name);
}