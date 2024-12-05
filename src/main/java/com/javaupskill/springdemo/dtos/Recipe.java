package com.javaupskill.springdemo.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.Transient;

@Data
@AllArgsConstructor
public class Recipe {
    private int id;
    @NotBlank
    private String name;
    private boolean isVegan;
    private Double price;
    @Min(value = 2, message = "recipes should contain at least 2 ingredients because ....")
    private int numberOfIngredients;
    @NotNull
    private RecipeType recipeType;

//    @Transient
//    private String customText;
//    @Valid
//    private SecretIngredient secretIngredient;
    // TODO
}
