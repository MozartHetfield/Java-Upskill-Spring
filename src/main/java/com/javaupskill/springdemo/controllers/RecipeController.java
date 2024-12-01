package com.javaupskill.springdemo.controllers;

import com.javaupskill.springdemo.dtos.Recipe;
import com.javaupskill.springdemo.services.AsianRecipeService;
import com.javaupskill.springdemo.services.RecipeService;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/recipes")
//@Lazy
public class RecipeController {
    RecipeService recipeService;
    RecipeService recipeService2;
    RecipeService americanRecipeService;

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
     * @param americanRecipeService
     */
    @Autowired
    public RecipeController(RecipeService recipeService, RecipeService recipeService2,
                            @Qualifier("americanRecipeService") RecipeService americanRecipeService) {
        System.out.println("controlled init");
        this.recipeService = recipeService;
        this.recipeService2 = recipeService2;
        this.americanRecipeService = americanRecipeService;
    }

    @GetMapping()
    public List<Recipe> getAllRecipes() {
        System.out.println(recipeService);
        System.out.println(americanRecipeService);
        return recipeService.getAllRecipes();
    }

}
