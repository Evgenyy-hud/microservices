package com.example.catalog.service;

import com.example.catalog.models.Category;
import com.example.catalog.models.Product;
import com.example.catalog.repo.CategoryRepository;
import com.example.catalog.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<List<Product>> getProductsByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            return Optional.empty();
        }
        return Optional.of(productRepository.findByCategoryId(categoryId));
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public Optional<Category> updateCategory(Long id, Category categoryDetails) {
        return categoryRepository.findById(id).map(existingCategory -> {
            existingCategory.setName(categoryDetails.getName());
            return categoryRepository.save(existingCategory);
        });
    }
    @Transactional
    public Optional<Category> deleteCategory(Long id) {
        return categoryRepository.findById(id).map(category -> {
            categoryRepository.deleteById(id);
            return category;
        });
    }
}
