package com.refu.share_recipes.services;

import org.springframework.stereotype.Service;

import com.refu.share_recipes.domain.Category;
import com.refu.share_recipes.domain.Recipe;
import com.refu.share_recipes.domain.dto.RecipeCreateDTO;
import com.refu.share_recipes.domain.dto.RecipeUpdateDTO;
import com.refu.share_recipes.domain.exceptions.CategoryNotFoundException;
import com.refu.share_recipes.domain.exceptions.RecipeNotFoundException;
import com.refu.share_recipes.repositories.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {
  private final RecipeRepository repository;
  private final CategoryService categoryService;

  public RecipeService(
    RecipeRepository repository,
    CategoryService categoryService
  ) {
    this.repository = repository;
    this.categoryService = categoryService;
  }

  public Recipe insert(RecipeCreateDTO data) {
    Category category = categoryService
      .getById(data.categoryId())
      .orElseThrow(CategoryNotFoundException::new);

    Recipe recipe = new Recipe();

    recipe.setTitle(data.title());
    recipe.setDescription(data.description());
    recipe.setOwnerId(data.ownerId());
    recipe.setIngredients(data.ingredients());
    recipe.setCategory(category);

    return repository.save(recipe);
  }

  public List<Recipe> getAll() {
    return repository.findAll();
  }

  public Recipe update(String id, RecipeUpdateDTO data) {
    Recipe recipe = repository.findById(id)
      .orElseThrow(RecipeNotFoundException::new);

    if (!data.categoryId().isEmpty()) {
      Category newCategory = categoryService
        .getById(data.categoryId())
        .orElseThrow(CategoryNotFoundException::new);
      
      recipe.setCategory(newCategory);
    }

    if (!data.title().isEmpty()) {
      recipe.setTitle(data.title());
    }
    
    if (!data.description().isEmpty()) {
      recipe.setDescription(data.description());
    }

    if (data.ingredients().length > 0) {
      recipe.setIngredients(data.ingredients());
    }

    return repository.save(recipe);
  }

  public void remove(String id) {
    Recipe recipe = repository.findById(id)
      .orElseThrow(RecipeNotFoundException::new);
      
    repository.delete(recipe);
  }
}
