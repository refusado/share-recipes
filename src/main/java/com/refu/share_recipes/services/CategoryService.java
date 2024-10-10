package com.refu.share_recipes.services;

import org.springframework.stereotype.Service;

import com.refu.share_recipes.domain.Category;
import com.refu.share_recipes.domain.dto.CategoryCreateDTO;
import com.refu.share_recipes.domain.dto.CategoryUpdateDTO;
import com.refu.share_recipes.domain.exceptions.CategoryNotFoundException;
import com.refu.share_recipes.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
  private final CategoryRepository repository;

  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  public Category insert(CategoryCreateDTO data) {
    Category category = new Category();

    category.setTitle(data.title());
    category.setDescription(data.description());
    category.setOwnerId(data.ownerId());

    return repository.save(category);
  }

  public List<Category> getAll() {
    return repository.findAll();
  }

  public Optional<Category> getById(String id) {
    return repository.findById(id);
  }

  public Category update(String id, CategoryUpdateDTO data) {
    Category category = repository.findById(id)
      .orElseThrow(CategoryNotFoundException::new);

    if (!data.title().isEmpty()) {
      category.setTitle(data.title());
    }
    
    if (!data.description().isEmpty()) {
      category.setDescription(data.description());
    }

    return repository.save(category);
  }

  public void remove(String id) {
    Category category = repository.findById(id)
      .orElseThrow(CategoryNotFoundException::new);
      
    repository.delete(category);
  }
}
