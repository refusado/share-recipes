package com.refu.share_recipes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.refu.share_recipes.domain.Recipe;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
  
}
