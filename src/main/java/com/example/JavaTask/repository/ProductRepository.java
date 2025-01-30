package com.example.JavaTask.repository;

import com.example.JavaTask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
