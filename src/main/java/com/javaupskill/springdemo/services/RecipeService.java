package com.javaupskill.springdemo.services;

import com.javaupskill.springdemo.entities.Recipe;
import com.javaupskill.springdemo.exceptions.ResponseException;
import com.javaupskill.springdemo.repositories.RecipeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Lazy
@Primary
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RecipeService {


    RecipeRepository recipeRepository;
    Logger logger;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.logger = LoggerFactory.getLogger(RecipeService.class);
        System.out.println("service init");
    }

    @PostConstruct
    public void setup() {
        System.out.println("service setup");
    }

    /**
     * it doesn't run for scope = prototype!
     */
    @PreDestroy
    public void cleanup() {
        System.out.println("service cleanup");
    }

    public List<Recipe> getAllRecipes() {
        // TODO: Recipe should be the model that deals with the DB interaction
        // we should convert Recipe into a RecipeDto
        // and send this RecipeDto to client
        return recipeRepository.getAllRecipes();
    }

    private void logSomething() {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }

    public Recipe verifyIfRecipeExistsAndReturnRecipe(int id) throws ResponseException {
        Recipe recipe = recipeRepository.getRecipeById(id);

        if (recipe == null) {
            throw new ResponseException(String.format("Recipe with ID %d was not found", id), HttpStatus.NOT_FOUND);
        }

        return recipe;
    }

    @Override
    public String toString() {
        return "RecipeService{}";
    }

    public List<Recipe> getFilteredRecipes(String name, boolean isVegan) {
        return recipeRepository.getRecipesByFilters(name, isVegan);
    }

    public Recipe addRecipe(Recipe recipe) throws ResponseException {
        recipeRepository.saveRecipe(recipe);
        return recipe;
    }

    public Recipe editRecipe(int id, Recipe recipe) throws ResponseException {
        verifyIfRecipeExistsAndReturnRecipe(id);

        recipe.setId(id);
        return recipeRepository.updateRecipe(recipe);
    }

    //Experimental
    public void editRecipesByFields() throws ResponseException {
        recipeRepository.updateRecipesByFields("test", true);
    }

    public void removeRecipe(int id) throws ResponseException {
        Recipe recipe = verifyIfRecipeExistsAndReturnRecipe(id);
        recipeRepository.removeRecipe(recipe);
    }
}
