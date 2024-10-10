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

import com.refu.share_recipes.domain.Recipe;
import com.refu.share_recipes.domain.dto.RecipeCreateDTO;
import com.refu.share_recipes.domain.dto.RecipeUpdateDTO;
import com.refu.share_recipes.services.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
  private final RecipeService service;
  
  public RecipeController(RecipeService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Recipe> createRecipe(
    @RequestBody RecipeCreateDTO recipeData
  ) {
    Recipe createdRecipe = service.insert(recipeData);

    return ResponseEntity.ok(createdRecipe);
  }

  @GetMapping
  public ResponseEntity<List<Recipe>> readCategories() {
    List<Recipe> foundCategories = service.getAll();

    return ResponseEntity.ok(foundCategories);

  }
  @PutMapping("/{id}")
  public ResponseEntity<Recipe> updateRecipe(
    @PathVariable String id,
    @RequestBody RecipeUpdateDTO newRecipeData
  ) {
    Recipe updatedRecipe = service.update(id, newRecipeData);

    return ResponseEntity.ok(updatedRecipe);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecipe(@PathVariable String id) {
    service.remove(id);

    return ResponseEntity.noContent().build();
  }
}
