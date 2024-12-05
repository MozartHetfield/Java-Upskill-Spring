package com.javaupskill.springdemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

    // alter table recipe auto_increment = 3000;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "isVegan")
    private boolean isVegan;

    @Column(name = "price")
    private Double price;

    @Column(name = "numberOfIngredients")
    @Min(value = 2, message = "recipes should contain at least 2 ingredients because ....")
    private int numberOfIngredients;

    public Recipe(String name, boolean isVegan, Double price, int numberOfIngredients, RecipeType recipeType) {
        this.name = name;
        this.isVegan = isVegan;
        this.price = price;
        this.numberOfIngredients = numberOfIngredients;
        this.recipeType = recipeType;
        this.customText = name + " " + numberOfIngredients;
    }

    @NotNull
    private RecipeType recipeType;

    @Transient
    private String customText;

//    @Valid
//    private SecretIngredient secretIngredient;
}
