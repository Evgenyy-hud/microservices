package com.example.catalog.controllers;

import com.example.catalog.models.Category;
import com.example.catalog.models.Product;
import com.example.catalog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryControllers {
    private final CategoryService categoryService;

    public CategoryControllers(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping // показать категории
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}/tovars") // показать товары внутри конкретной категории
    public ResponseEntity<List<Product>> getTovarByCategory(@PathVariable Long categoryId) {
        return categoryService.getProductsByCategoryId(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}") // параметры раздела
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // создать новый раздел
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/{id}") // обновить раздел
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                   @RequestBody Category category) {
        return categoryService.updateCategory(id, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // удалить раздел
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
