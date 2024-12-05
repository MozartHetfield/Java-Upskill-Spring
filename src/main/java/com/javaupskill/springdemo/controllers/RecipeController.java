package com.javaupskill.springdemo.controllers;

import com.javaupskill.springdemo.entities.Recipe;
import com.javaupskill.springdemo.exceptions.ResponseException;
import com.javaupskill.springdemo.services.RecipeService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/recipes")
//@Lazy
public class RecipeController {
    RecipeService recipeService;

    /**
     * Qualifier should have the name of the desired class starting with lower case
     *
     * @param recipeService
     */
    @Autowired
    public RecipeController(RecipeService recipeService) {
        System.out.println("controlled init");
        this.recipeService = recipeService;
    }

    @GetMapping()
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipeList = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipeList, createHttpStatusFromRecipeList(recipeList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable int id) throws ResponseException {
        Recipe recipe = recipeService.verifyIfRecipeExistsAndReturnRecipe(id);
        return new ResponseEntity<>(recipe, createHttpStatusFromRecipe(recipe));
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<Recipe>> getRecipesByFilters(@RequestParam(required = false, name = "nameToken") String name,
                                            @RequestParam(defaultValue = "false") boolean isVegan,
                                            @RequestParam @Min(0) double minPrice) {
        List<Recipe> recipeList = recipeService.getFilteredRecipes(name, isVegan);
        return new ResponseEntity<>(recipeList, createHttpStatusFromRecipeList(recipeList));
    }

    private HttpStatus createHttpStatusFromRecipe(Recipe recipe) {
        HttpStatus statusCode = HttpStatus.OK;
        if (recipe == null) {
            statusCode = HttpStatus.NO_CONTENT;
        }
        return statusCode;
    }

    private HttpStatus createHttpStatusFromRecipeList(List<Recipe> recipes) {
        HttpStatus statusCode = HttpStatus.OK;
        if (recipes == null || recipes.isEmpty()) {
            statusCode = HttpStatus.NO_CONTENT;
        }
        return statusCode;
    }
}
