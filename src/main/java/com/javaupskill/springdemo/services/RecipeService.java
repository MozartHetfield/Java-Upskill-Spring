package com.javaupskill.springdemo.services;

import com.javaupskill.springdemo.dtos.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe getRecipeById(int id);
}
