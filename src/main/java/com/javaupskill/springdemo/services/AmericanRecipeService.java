package com.javaupskill.springdemo.services;

import com.javaupskill.springdemo.dtos.Recipe;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
//@Lazy
public class AmericanRecipeService implements RecipeService {

    public AmericanRecipeService() {
        System.out.println("american init");
    }

    public List<Recipe> getAllRecipes() {
        return getAllRecipesMock();
    }

    /**
     * this should be in DAO
     *
     * @return
     */
    public List<Recipe> getAllRecipesMock() {
        return Arrays.asList(
                new Recipe("Recipe one", true, 10.0, 10),
                new Recipe("Recipe two", false, 5.0, 13),
                new Recipe("Recipe three", true, 4.0, 8),
                new Recipe("Recipe four", false, 15.0, 3)
        );
    }

    @Override
    public String toString() {
        return "AmericanRecipeService{}";
    }
}
