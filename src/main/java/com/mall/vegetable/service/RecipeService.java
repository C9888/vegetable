package com.mall.vegetable.service;

import com.mall.vegetable.dao.RecipeMapper;
import com.mall.vegetable.pojo.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeMapper recipeMapper;

    public Recipe findById(Integer id) {
        return recipeMapper.findById(id);
    }

    public List<Recipe> findByVegetableId(Integer vegetableId) {
        return recipeMapper.findByVegetableId(vegetableId);
    }

    public List<Recipe> findAll() {
        return recipeMapper.findAll();
    }

    public boolean addRecipe(Recipe recipe) {
        recipe.setStatus(1);
        return recipeMapper.insert(recipe) > 0;
    }

    public boolean updateRecipe(Recipe recipe) {
        return recipeMapper.update(recipe) > 0;
    }

    public boolean deleteRecipe(Integer id) {
        return recipeMapper.deleteById(id) > 0;
    }
    
    public List<Recipe> searchByKeyword(String keyword) {
        return recipeMapper.searchByKeyword(keyword);
    }
}
