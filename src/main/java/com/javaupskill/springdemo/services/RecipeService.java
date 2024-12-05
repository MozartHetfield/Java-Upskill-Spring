package com.javaupskill.springdemo.services;

import com.javaupskill.springdemo.dtos.Recipe;
import com.javaupskill.springdemo.dtos.RecipeType;
import com.javaupskill.springdemo.exceptions.ResponseException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Lazy
@Primary
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RecipeService {

    List<Recipe> recipes;
    @Value("${chef.name}")
    private String chefName;

    @Value("${chef.rating}")
    private int chefRating;

    public RecipeService() {
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

    public Recipe getRecipeById(int id) throws ResponseException {
        Optional<Recipe> foundRecipe = recipes.stream().filter(recipe -> recipe.getId() == id).findFirst();

        if (foundRecipe.isEmpty()) {
            throw new ResponseException(String.format("Recipe with ID %d was not found", id), HttpStatus.NOT_FOUND);
        }

        return foundRecipe.get();
    }

    /**
     * this should be in DAO
     *
     * @return
     */
    public List<Recipe> getAllRecipesMock() {
        return new ArrayList<>(Arrays.asList(
                new Recipe(1,"Recipe one", true, 10.0, 10, RecipeType.AMERICAN),
                new Recipe(2, "Recipe two", false, 5.0, 13, RecipeType.ASIAN),
                new Recipe(3, "Recipe three", true, 4.0, 8, RecipeType.EUROPEAN),
                new Recipe(4,"Recipe four", false, 15.0, 3, RecipeType.EUROPEAN)
        ));
    }

    @Override
    public String toString() {
        return "RecipeService{}";
    }

    public List<Recipe> getFilteredRecipes(String name, boolean isVegan) {
        return recipes.stream()
                .filter(recipe -> {
                    boolean isNameMatch = true;
                    if (name != null) {
                        isNameMatch = recipe.getName().toLowerCase().contains(name.toLowerCase());
                    }
                    boolean isVeganMatch = recipe.isVegan() == isVegan;
                    return isVeganMatch && isNameMatch;
                })
                .collect(Collectors.toList());
    }

    public Recipe addRecipe(Recipe recipe) throws ResponseException {
        boolean isRecipeAdded = recipes.add(recipe);

        if (!isRecipeAdded) {
            throw new ResponseException(String.format("Recipe with ID %d was not added", recipe.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return recipe;
    }

    public Recipe editRecipe(int id, Recipe recipe) throws ResponseException {
        Recipe existingRecipe = getRecipeById(id);

        // TODO: set all the fields to the new values
        existingRecipe.setName(recipe.getName());

        return recipe;
    }

    public void removeRecipe(int id) throws ResponseException {
        Recipe recipe = getRecipeById(id);
        recipes.remove(recipe);
    }
}
