package com.refu.share_recipes.domain.dto;

public record RecipeUpdateDTO(
  String categoryId,
  String title,
  String description,
  String[] ingredients
) {
  
}
