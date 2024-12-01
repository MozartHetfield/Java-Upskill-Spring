package com.javaupskill.springdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipe {
    private String name;
    private boolean isVegan;
    private Double price;
    private int numberOfIngredients;
}
