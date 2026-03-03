package com.mall.vegetable.controller;

import com.mall.vegetable.pojo.ApiResponse;
import com.mall.vegetable.pojo.Recipe;
import com.mall.vegetable.pojo.User;
import com.mall.vegetable.pojo.Vegetable;
import com.mall.vegetable.service.RecipeService;
import com.mall.vegetable.service.UserService;
import com.mall.vegetable.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @GetMapping("/vegetables")
    public ApiResponse<List<Vegetable>> getAllVegetables() {
        List<Vegetable> vegetables = vegetableService.findAll();
        return ApiResponse.success(vegetables);
    }

    @GetMapping("/vegetable/{id}")
    public ApiResponse<Vegetable> getVegetable(@PathVariable Integer id) {
        Vegetable vegetable = vegetableService.findById(id);
        if (vegetable == null) {
            return ApiResponse.error("蔬菜不存在");
        }
        return ApiResponse.success(vegetable);
    }

    @PostMapping("/vegetable/save")
    public ApiResponse<String> saveVegetable(@RequestBody Vegetable vegetable) {
        try {
            if (vegetable.getId() == null) {
                vegetableService.addVegetable(vegetable);
            } else {
                vegetableService.updateVegetable(vegetable);
            }
            return ApiResponse.success("保存成功");
        } catch (Exception e) {
            return ApiResponse.error("保存失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/vegetable/delete/{id}")
    public ApiResponse<String> deleteVegetable(@PathVariable Integer id) {
        try {
            vegetableService.deleteVegetable(id);
            return ApiResponse.success("删除成功");
        } catch (Exception e) {
            return ApiResponse.error("删除失败: " + e.getMessage());
        }
    }

    @GetMapping("/recipes")
    public ApiResponse<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.findAll();
        for (Recipe recipe : recipes) {
            Vegetable vege = vegetableService.findById(recipe.getVegetableId());
            if (vege != null) {
                recipe.setVegetableName(vege.getName());
            }
        }
        return ApiResponse.success(recipes);
    }

    @GetMapping("/recipe/{id}")
    public ApiResponse<Recipe> getRecipe(@PathVariable Integer id) {
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
            return ApiResponse.error("菜谱不存在");
        }
        Vegetable vege = vegetableService.findById(recipe.getVegetableId());
        if (vege != null) {
            recipe.setVegetableName(vege.getName());
        }
        return ApiResponse.success(recipe);
    }

    @PostMapping("/recipe/save")
    public ApiResponse<String> saveRecipe(@RequestBody Recipe recipe) {
        try {
            if (recipe.getServings() == null) {
                recipe.setServings(2);
            }
            if (recipe.getDifficulty() == null) {
                recipe.setDifficulty("简单");
            }
            
            if (recipe.getId() == null) {
                recipeService.addRecipe(recipe);
            } else {
                recipeService.updateRecipe(recipe);
            }
            return ApiResponse.success("保存成功");
        } catch (Exception e) {
            return ApiResponse.error("保存失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/recipe/delete/{id}")
    public ApiResponse<String> deleteRecipe(@PathVariable Integer id) {
        try {
            recipeService.deleteRecipe(id);
            return ApiResponse.success("删除成功");
        } catch (Exception e) {
            return ApiResponse.error("删除失败: " + e.getMessage());
        }
    }

    @GetMapping("/users")
    public ApiResponse<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        for (User user : users) {
            user.setPassword(null);
        }
        return ApiResponse.success(users);
    }

    @GetMapping("/user/toggle/{id}")
    public ApiResponse<String> toggleUserStatus(@PathVariable Integer id) {
        try {
            userService.toggleStatus(id);
            return ApiResponse.success("操作成功");
        } catch (Exception e) {
            return ApiResponse.error("操作失败: " + e.getMessage());
        }
    }
}
