package com.javaupskill.springdemo.repositories;

import com.javaupskill.springdemo.entities.Recipe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeRepository {
    private static final String UPDATE_RECIPES_BY_FIELDS = "UPDATE Recipe SET name = `%s` where isVegan = `%s`";

    private EntityManager entityManager;

    @Autowired
    public RecipeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        System.out.println("repo init");
    }

    @Transactional
    public void saveRecipe(Recipe recipe) {
        entityManager.persist(recipe);
    }

    public List<Recipe> getAllRecipes() {
        // JPQL = JPA Query Language
        TypedQuery<Recipe> results = entityManager.createQuery("FROM Recipe", Recipe.class);
        return results.getResultList();
    }

    public Recipe getRecipeById(int id) {
        return entityManager.find(Recipe.class, id);
    }

    public List<Recipe> getRecipesByFilters(String text, boolean isVegan) {
        TypedQuery<Recipe> results = entityManager.createQuery("FROM Recipe WHERE " +
                "name like :name and isVegan = :isVegan", Recipe.class);
        results.setParameter("name", "%" + text + "%");
        results.setParameter("isVegan", isVegan);
        return results.getResultList();
    }

    @Transactional
    public Recipe updateRecipe(Recipe recipe) {
        return entityManager.merge(recipe);
    }

    public void updateRecipesByFields(String name, boolean isVegan) {
        int rows = entityManager.createQuery(String.format(UPDATE_RECIPES_BY_FIELDS, name, isVegan)).executeUpdate();
        System.out.println(rows + " rows have been updated");
    }

    @Transactional
    public void removeRecipe(Recipe recipe) {
        entityManager.remove(recipe);
    }
}
