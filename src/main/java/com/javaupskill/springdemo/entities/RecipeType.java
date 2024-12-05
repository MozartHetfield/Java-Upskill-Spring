package com.javaupskill.springdemo.entities;

public enum RecipeType {
    INVALID(-1),
    UNKNOWN(0),
    ASIAN(1),
    AMERICAN(2),
    EUROPEAN(3)
    ;

    private int id;

    RecipeType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RecipeType getRecipeTypeById(int id) {
        for (RecipeType recipeType : RecipeType.values()) {
            if (recipeType.getId() == id) return recipeType;
        }
        return RecipeType.INVALID;
    }
}
