package com.javaupskill.springdemo.configurations;

import com.javaupskill.springdemo.dtos.Recipe;
import com.javaupskill.springdemo.services.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class RecipeServiceConfiguration {

    @Bean(name = "myCustomRecipeService")
    public RecipeService getCustomRecipeService() {
        System.out.println("configuration init");
        return new RecipeService() {
            @Override
            public List<Recipe> getAllRecipes() {
                return Collections.emptyList();
            }

            @Override
            public Recipe getRecipeById(int id) {
                return null;
            }
        };
    }
}
