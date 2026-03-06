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

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private com.mall.vegetable.service.RecipeRatingService recipeRatingService;
    
    private boolean checkAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println("检查管理员权限 - Session ID: " + session.getId());
        System.out.println("Session中的用户: " + user);
        if (user != null) {
            System.out.println("用户ID: " + user.getId() + ", 用户名: " + user.getUsername() + ", 角色: " + user.getRole());
        }
        return user != null && user.getRole() == 1;
    }

    @GetMapping("/vegetables")
    public ApiResponse<List<Vegetable>> getAllVegetables(HttpSession session) {
        System.out.println("收到获取蔬菜列表请求");
        if (!checkAdmin(session)) {
            System.out.println("管理员权限检查失败");
            return ApiResponse.error("无权限访问");
        }
        System.out.println("管理员权限检查通过，开始查询蔬菜列表");
        List<Vegetable> vegetables = vegetableService.findAll();
        System.out.println("查询到蔬菜数量: " + vegetables.size());
        return ApiResponse.success(vegetables);
    }

    @GetMapping("/vegetable/{id}")
    public ApiResponse<Vegetable> getVegetable(@PathVariable Integer id, HttpSession session) {
        if (!checkAdmin(session)) {
            return ApiResponse.error("无权限访问");
        }
        Vegetable vegetable = vegetableService.findById(id);
        if (vegetable == null) {
            return ApiResponse.error("蔬菜不存在");
        }
        return ApiResponse.success(vegetable);
    }

    @PostMapping("/vegetable/save")
    public ApiResponse<String> saveVegetable(@RequestBody Vegetable vegetable, HttpSession session) {
        if (!checkAdmin(session)) {
            return ApiResponse.error("无权限访问");
        }
        try {
            System.out.println("Received vegetable: " + vegetable);
            System.out.println("Vegetable ID: " + vegetable.getId());
            System.out.println("Vegetable Name: " + vegetable.getName());
            
            if (vegetable.getId() == null) {
                System.out.println("Adding new vegetable");
                boolean result = vegetableService.addVegetable(vegetable);
                System.out.println("Add result: " + result);
            } else {
                System.out.println("Updating vegetable with ID: " + vegetable.getId());
                boolean result = vegetableService.updateVegetable(vegetable);
                System.out.println("Update result: " + result);
            }
            return ApiResponse.success("保存成功");
        } catch (Exception e) {
            System.out.println("Error saving vegetable: " + e.getMessage());
            e.printStackTrace();
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
    public ApiResponse<List<Recipe>> getAllRecipes(HttpSession session) {
        if (!checkAdmin(session)) {
            return ApiResponse.error("无权限访问");
        }
        List<Recipe> recipes = recipeService.findAll();
        for (Recipe recipe : recipes) {
            Vegetable vege = vegetableService.findById(recipe.getVegetableId());
            if (vege != null) {
                recipe.setVegetableName(vege.getName());
            }
            Map<String, Object> stats = recipeRatingService.getRecipeRatingStats(recipe.getId());
            if (stats != null) {
                Object avgScoreObj = stats.get("avgScore");
                if (avgScoreObj != null) {
                    recipe.setAvgScore(((Number) avgScoreObj).doubleValue());
                }
                Object countObj = stats.get("count");
                if (countObj != null) {
                    recipe.setRatingCount(((Number) countObj).intValue());
                }
            }
        }
        return ApiResponse.success(recipes);
    }

    @GetMapping("/recipe/{id}")
    public ApiResponse<Recipe> getRecipe(@PathVariable Integer id, HttpSession session) {
        if (!checkAdmin(session)) {
            return ApiResponse.error("无权限访问");
        }
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
            return ApiResponse.error("菜谱不存在");
        }
        Vegetable vege = vegetableService.findById(recipe.getVegetableId());
        if (vege != null) {
            recipe.setVegetableName(vege.getName());
        }
        Map<String, Object> stats = recipeRatingService.getRecipeRatingStats(recipe.getId());
        if (stats != null) {
            Object avgScoreObj = stats.get("avgScore");
            if (avgScoreObj != null) {
                recipe.setAvgScore(((Number) avgScoreObj).doubleValue());
            }
            Object countObj = stats.get("count");
            if (countObj != null) {
                recipe.setRatingCount(((Number) countObj).intValue());
            }
        }
        return ApiResponse.success(recipe);
    }

    @PostMapping("/recipe/save")
    public ApiResponse<String> saveRecipe(@RequestBody Recipe recipe, HttpSession session) {
        if (!checkAdmin(session)) {
            return ApiResponse.error("无权限访问");
        }
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
