package com.refu.share_recipes.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "recipes")
public class Recipe {
  @Id
  private String id;
  private String title;
  private String description;
  private String ownerId;
  private String[] ingredients;
  private Category category; 
}
