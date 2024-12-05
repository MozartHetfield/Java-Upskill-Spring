package com.javaupskill.springdemo.controllers;

import com.javaupskill.springdemo.dtos.Recipe;
import com.javaupskill.springdemo.exceptions.ResponseException;
import com.javaupskill.springdemo.services.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/recipes")
public class AdminRecipeController {

    @Value("${admin.key}")
    private String KEY;

    RecipeService recipeService;

    @Autowired
    public AdminRecipeController(RecipeService recipeService) {
        System.out.println("admin controller init");
        this.recipeService = recipeService;
    }

    @PostMapping()
    public ResponseEntity<Recipe> addRecipe(@RequestBody @Valid Recipe recipe,
                                            @RequestHeader(name = "ADMIN_TOKEN", required = false) String adminToken) throws Exception {

        validateAdminToken(adminToken);
        Recipe createdRecipe = recipeService.addRecipe(recipe);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe,
                                             @RequestHeader(name = "ADMIN_TOKEN", required = false) String adminToken) throws Exception {
        validateAdminToken(adminToken);
        return new ResponseEntity<>(recipeService.editRecipe(id, recipe), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id,
                                             @RequestHeader(name = "ADMIN_TOKEN", required = false) String adminToken) throws Exception {
        validateAdminToken(adminToken);
        recipeService.removeRecipe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateAdminToken(String adminToken) throws Exception {
        if (!KEY.equals(adminToken)) {
            throw new ResponseException("No rights to do this action", HttpStatus.UNAUTHORIZED);
        }
    }
}
