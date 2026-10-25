package com.example.catalog.controllers;

import com.example.catalog.dto.RequestProduct;
import com.example.catalog.models.Category;
import com.example.catalog.models.Product;
import com.example.catalog.repo.CategoryRepository;
import com.example.catalog.repo.ProductRepository;
import com.example.catalog.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductControllers {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductControllers(ProductService productService, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping
    public List<Product> getAllTovars() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getTovarById(@PathVariable Long id) {

        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createTovar(@RequestBody RequestProduct request) {
        Optional<Category> category = categoryRepository.findById(request.getCategoryId());
        if (category.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Product product = new Product(
                category.get(),
                request.getTitle(),
                request.getDescription(),
                request.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateTovar(@PathVariable Long id,
                                               @RequestBody RequestProduct request) {
        Optional<Product> tovarOpt = productRepository.findById(id);
        if (tovarOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Category> categoty = categoryRepository.findById(request.getCategoryId());
        if (categoty.isEmpty()) {
            return ResponseEntity.badRequest().build();

        }
        Product product = tovarOpt.get();
        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        product.setCategory(categoty.get());
        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteTovar(@PathVariable Long id){
        Optional<Product> tovarOpt = productRepository.findById(id);
        if(tovarOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Product product = tovarOpt.get();
        productRepository.deleteById(id);
        return ResponseEntity.ok(product);
    }
}

