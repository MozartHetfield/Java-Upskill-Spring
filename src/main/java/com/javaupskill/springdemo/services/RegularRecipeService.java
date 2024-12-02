package com.javaupskill.springdemo.services;

import com.javaupskill.springdemo.dtos.Recipe;
import com.javaupskill.springdemo.dtos.RecipeType;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
//@Lazy
@Primary
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RegularRecipeService implements RecipeService {

    List<Recipe> recipes;
    @Value("${chef.name}")
    private String chefName;

    @Value("${chef.rating}")
    private int chefRating;

    public RegularRecipeService() {
        recipes = getAllRecipesMock();
        System.out.println("asian init");
    }

    @PostConstruct
    public void setup() {
        System.out.println("asian setup");
    }

    /**
     * it doesn't run for scope = prototype!
     */
    @PreDestroy
    public void cleanup() {
        System.out.println("asian cleanup");
    }

    public List<Recipe> getAllRecipes() {
        System.out.println(chefName + " " + chefRating);
        return recipes;
    }

    public Recipe getRecipeById(int id) {
        Optional<Recipe> foundRecipe = recipes.stream().filter(recipe -> recipe.getId() == id).findFirst();

        if (foundRecipe.isEmpty()) {
            // TODO: exception handling
            return null;
        }

        return foundRecipe.get();
    }

    /**
     * this should be in DAO
     *
     * @return
     */
    public List<Recipe> getAllRecipesMock() {
        return Arrays.asList(
                new Recipe(1,"Recipe one", true, 10.0, 10, RecipeType.AMERICAN),
                new Recipe(2, "Recipe two", false, 5.0, 13, RecipeType.ASIAN),
                new Recipe(3, "Recipe three", true, 4.0, 8, RecipeType.EUROPEAN),
                new Recipe(4,"Recipe four", false, 15.0, 3, RecipeType.EUROPEAN)
        );
    }

    @Override
    public String toString() {
        return "AsianRecipeService{}";
    }
}
