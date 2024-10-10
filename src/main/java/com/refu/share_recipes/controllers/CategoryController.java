package com.refu.share_recipes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refu.share_recipes.domain.Category;
import com.refu.share_recipes.domain.dto.CategoryCreateDTO;
import com.refu.share_recipes.domain.dto.CategoryUpdateDTO;
import com.refu.share_recipes.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService service;
  
  public CategoryController(CategoryService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Category> createCategory(
    @RequestBody CategoryCreateDTO categoryData
  ) {
    Category createdCategory = service.insert(categoryData);

    return ResponseEntity.ok(createdCategory);
  }

  @GetMapping
  public ResponseEntity<List<Category>> readCategories() {
    List<Category> foundCategories = service.getAll();

    return ResponseEntity.ok(foundCategories);

  }
  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(
    @PathVariable String id,
    @RequestBody CategoryUpdateDTO newCategoryData
  ) {
    Category updatedCategory = service.update(id, newCategoryData);

    return ResponseEntity.ok(updatedCategory);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
    service.remove(id);

    return ResponseEntity.noContent().build();
  }
}
