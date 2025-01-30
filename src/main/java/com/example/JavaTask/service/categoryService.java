package com.example.JavaTask.service;

import com.example.JavaTask.model.Category;
import com.example.JavaTask.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
public class categoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories with pagination
    
    @GetMapping("/api/categories")	
    public Page<Category> getAllCategories(int page) {
        return categoryRepository.findAll(PageRequest.of(page, 10)); // 10 items per page
    }

    // Get category by ID
    @GetMapping("/api/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
    	Category c = categoryRepository.findById(id).get();
        
        return c;
    }

    // Create a new category
    @PostMapping("api/categories")
    @ResponseStatus(code =HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // Update category by ID
    @PutMapping("api/categories/{id}")
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    // Delete category by ID
    @DeleteMapping("api/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}