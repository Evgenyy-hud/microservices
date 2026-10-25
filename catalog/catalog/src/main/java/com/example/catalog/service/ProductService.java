package com.example.catalog.service;

import com.example.catalog.dto.RequestProduct;
import com.example.catalog.models.Category;
import com.example.catalog.models.Product;
import com.example.catalog.repo.CategoryRepository;
import com.example.catalog.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Optional<Product> createProduct(RequestProduct request) {
        Optional<Category> category = categoryRepository.findById(request.getCategoryId());

        if (category.isEmpty()) {
            return Optional.empty();
        }

        Product product = new Product(
                category.get(),
                request.getTitle(),
                request.getDescription(),
                request.getPrice()
        );

        return Optional.of(productRepository.save(product));
    }
}

