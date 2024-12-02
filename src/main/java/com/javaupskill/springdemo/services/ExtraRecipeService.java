package com.javaupskill.springdemo.services;

import com.javaupskill.springdemo.dtos.Recipe;
import com.javaupskill.springdemo.dtos.RecipeType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
//@Lazy
public class ExtraRecipeService implements RecipeService {

    public ExtraRecipeService() {
        System.out.println("american init");
    }

    public List<Recipe> getAllRecipes() {
        return Collections.emptyList();
    }

    @Override
    public Recipe getRecipeById(int id) {
        return null;
    }


    @Override
    public String toString() {
        return "AmericanRecipeService{}";
    }
}
