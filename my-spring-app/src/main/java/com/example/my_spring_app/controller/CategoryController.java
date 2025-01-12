package com.example.my_spring_app.controller;

import com.example.my_spring_app.model.Category;
import com.example.my_spring_app.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Operations related to categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Fetches all categories in the system.")
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Fetches a specific category by its ID.")
    public ResponseEntity<Category> getCategoryById(@PathVariable @Parameter(description = "ID of the category to fetch") Long id) {
        return categoryService.findCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new category in the system.")
    public Category createCategory(@RequestBody @Parameter(description = "Category details") Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing category", description = "Updates the details of an existing category.")
    public ResponseEntity<Category> updateCategory(@PathVariable @Parameter(description = "ID of the category to update") Long id,
                                                   @RequestBody @Parameter(description = "Updated category details") Category updatedCategory) {
        return categoryService.findCategoryById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());
                    Category savedCategory = categoryService.saveCategory(existingCategory);
                    return ResponseEntity.ok(savedCategory);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Deletes a category from the system.")
    public ResponseEntity<Void> deleteCategory(@PathVariable @Parameter(description = "ID of the category to delete") Long id) {
        if (categoryService.findCategoryById(id).isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
