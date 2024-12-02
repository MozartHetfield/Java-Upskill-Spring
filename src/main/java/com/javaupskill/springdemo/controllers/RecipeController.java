package com.javaupskill.springdemo.controllers;

import com.javaupskill.springdemo.dtos.Recipe;
import com.javaupskill.springdemo.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/recipes")
//@Lazy
public class RecipeController {
    RecipeService recipeService;
    RecipeService customRecipeService;
    RecipeService extraRecipeService;

//    @PreDestroy
//    public void cleanup() {
//        // alternative to call cleanup if asianRecipeService is prototype
//        ((AsianRecipeService) recipeService).cleanup();
//        ((AsianRecipeService) recipeService2).cleanup();
//    }

    /**
     * Qualifier should have the name of the desired class starting with lower case
     *
     * @param recipeService
     * @param extraRecipeService
     */
    @Autowired
    public RecipeController(RecipeService recipeService, @Qualifier("myCustomRecipeService") RecipeService customRecipeService,
                            @Qualifier("americanRecipeService") RecipeService extraRecipeService) {
        System.out.println("controlled init");
        this.recipeService = recipeService;
        this.customRecipeService = customRecipeService;
        this.extraRecipeService = extraRecipeService;
    }

    @GetMapping()
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

}
