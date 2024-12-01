package com.javaupskill.springdemo.services;

import com.javaupskill.springdemo.dtos.Recipe;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
//@Lazy
@Primary
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AsianRecipeService implements RecipeService {
    @Value("${chef.name}")
    private String chefName;

    @Value("${chef.rating}")
    private int chefRating;

    public AsianRecipeService() {
        System.out.println("asian init");
    }

    @PostConstruct
    public void setup() {
        System.out.println("asian setup");
    }

    /**
     * it doesn't run for scope = prototype!
     */
    @PreDestroy
    public void cleanup() {
        System.out.println("asian cleanup");
    }

    public List<Recipe> getAllRecipes() {
        System.out.println(chefName + " " + chefRating);
        return getAllRecipesMock();
    }

    /**
     * this should be in DAO
     *
     * @return
     */
    public List<Recipe> getAllRecipesMock() {
        return Arrays.asList(
                new Recipe("Recipe one", true, 10.0, 10),
                new Recipe("Recipe two", false, 5.0, 13),
                new Recipe("Recipe three", true, 4.0, 8),
                new Recipe("Recipe four", false, 15.0, 3)
        );
    }

    @Override
    public String toString() {
        return "AsianRecipeService{}";
    }
}
