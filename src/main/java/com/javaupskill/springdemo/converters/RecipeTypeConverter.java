package com.javaupskill.springdemo.converters;

import com.javaupskill.springdemo.entities.RecipeType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class RecipeTypeConverter implements AttributeConverter<RecipeType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RecipeType recipeType) {
        return recipeType.getId();
    }

    @Override
    public RecipeType convertToEntityAttribute(Integer id) {
        return RecipeType.getRecipeTypeById(id);
    }
}
