package com.refu.share_recipes.domain.dto;

public record RecipeCreateDTO(
  String ownerId,
  String categoryId,
  String title,
  String description,
  String[] ingredients
) {
  
}
