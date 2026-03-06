package com.mall.vegetable.service;

import com.mall.vegetable.dao.RecipeRatingMapper;
import com.mall.vegetable.pojo.RecipeRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecipeRatingService {

    @Autowired
    private RecipeRatingMapper recipeRatingMapper;

    public void addOrUpdateRating(RecipeRating rating) {
        RecipeRating existing = recipeRatingMapper.findByRecipeAndUser(rating.getRecipeId(), rating.getUserId());
        if (existing != null) {
            recipeRatingMapper.update(rating);
        } else {
            recipeRatingMapper.insert(rating);
        }
    }

    public RecipeRating findByRecipeAndUser(Integer recipeId, Integer userId) {
        return recipeRatingMapper.findByRecipeAndUser(recipeId, userId);
    }

    public Map<String, Object> getAverageScore(Integer recipeId) {
        return recipeRatingMapper.getAverageScore(recipeId);
    }

    public Map<String, Object> getRecipeRatingStats(Integer recipeId) {
        return recipeRatingMapper.getAverageScore(recipeId);
    }

    public List<RecipeRating> getRecipeRatings(Integer recipeId) {
        return recipeRatingMapper.findByRecipeIdWithUsername(recipeId);
    }

    public RecipeRating getUserRating(Integer recipeId, Integer userId) {
        return recipeRatingMapper.findByRecipeAndUser(recipeId, userId);
    }
}
