package com.example.JavaTask.service;

import com.example.JavaTask.model.Product;
import com.example.JavaTask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class productService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new RuntimeException("Category ID is required");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getAllProducts(int page) {
    	return productRepository.findAll(PageRequest.of(page, 10)); // 10 items per page
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    
}
