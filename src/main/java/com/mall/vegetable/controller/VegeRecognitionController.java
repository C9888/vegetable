package com.mall.vegetable.controller;

import com.mall.vegetable.pojo.Recipe;
import com.mall.vegetable.pojo.RecognitionHistory;
import com.mall.vegetable.pojo.Vegetable;
import com.mall.vegetable.service.RecipeService;
import com.mall.vegetable.service.RecognitionHistoryService;
import com.mall.vegetable.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/recognition")
public class VegeRecognitionController {

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecognitionHistoryService recognitionHistoryService;

    /**
     * 蔬菜识别接口
     */
    @PostMapping("/identify")
    public Map<String, Object> identifyVegetable(@RequestParam("image") MultipartFile image, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // TODO: 这里应该调用AI识别服务，目前返回模拟数据
            // 实际项目中需要接入图像识别API
            
            String vegetableName = "西红柿"; // 模拟识别结果
            Vegetable vegetable = vegetableService.findByName(vegetableName);
            
            if (vegetable == null) {
                result.put("code", 404);
                result.put("message", "未识别到该蔬菜");
                return result;
            }
            
            // 保存识别记录
            Integer userId = getUserId(session);
            if (userId != null) {
                RecognitionHistory history = new RecognitionHistory();
                history.setUserId(userId);
                history.setVegetableName(vegetable.getName());
                history.setConfidence(new BigDecimal("0.95"));
                recognitionHistoryService.addHistory(history);
            }
            
            result.put("code", 200);
            result.put("message", "识别成功");
            
            Map<String, Object> data = new HashMap<>();
            data.put("vegetableName", vegetable.getName());
            data.put("confidence", 0.95);
            data.put("vegetableId", vegetable.getId());
            data.put("image", vegetable.getImage());
            
            result.put("data", data);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "识别失败：" + e.getMessage());
        }
        
        return result;
    }

    /**
     * 获取蔬菜详细信息
     */
    @GetMapping("/detail/{vegetableName}")
    public Map<String, Object> getVegetableDetail(@PathVariable String vegetableName) {
        Map<String, Object> result = new HashMap<>();
        
        Vegetable vegetable = vegetableService.findByName(vegetableName);
        
        if (vegetable == null) {
            result.put("code", 404);
            result.put("message", "蔬菜不存在");
            return result;
        }
        
        result.put("code", 200);
        result.put("message", "查询成功");
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", vegetable.getId());
        data.put("name", vegetable.getName());
        data.put("nameEn", vegetable.getNameEn());
        data.put("category", vegetable.getCategory());
        data.put("image", vegetable.getImage());
        data.put("nutrition", vegetable.getNutrition());
        data.put("benefits", vegetable.getBenefits());
        data.put("selectionTips", vegetable.getSelectionTips());
        data.put("storageMethod", vegetable.getStorageMethod());
        data.put("description", vegetable.getDescription());
        
        // 获取推荐菜谱
        List<Recipe> recipes = recipeService.findByVegetableId(vegetable.getId());
        List<Map<String, Object>> recommendedRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            Map<String, Object> recipeMap = new HashMap<>();
            recipeMap.put("id", recipe.getId());
            recipeMap.put("name", recipe.getName());
            recipeMap.put("difficulty", recipe.getDifficulty());
            recipeMap.put("time", recipe.getTime());
            recipeMap.put("image", recipe.getImage());
            recommendedRecipes.add(recipeMap);
        }
        data.put("recommendedRecipes", recommendedRecipes);
        
        result.put("data", data);
        return result;
    }

    /**
     * 获取蔬菜营养信息
     */
    @GetMapping("/nutrition/{vegetableName}")
    public Map<String, Object> getNutritionInfo(@PathVariable String vegetableName) {
        Map<String, Object> result = new HashMap<>();
        
        Vegetable vegetable = vegetableService.findByName(vegetableName);
        
        if (vegetable == null) {
            result.put("code", 404);
            result.put("message", "蔬菜不存在");
            return result;
        }
        
        result.put("code", 200);
        result.put("message", "查询成功");
        
        Map<String, Object> data = new HashMap<>();
        data.put("name", vegetable.getName());
        data.put("nutrition", vegetable.getNutrition());
        
        result.put("data", data);
        return result;
    }

    /**
     * 获取推荐菜谱
     */
    @GetMapping("/recipes/{vegetableName}")
    public Map<String, Object> getRecipes(@PathVariable String vegetableName) {
        Map<String, Object> result = new HashMap<>();
        
        Vegetable vegetable = vegetableService.findByName(vegetableName);
        
        if (vegetable == null) {
            result.put("code", 404);
            result.put("message", "蔬菜不存在");
            return result;
        }
        
        List<Recipe> recipes = recipeService.findByVegetableId(vegetable.getId());
        
        List<Map<String, Object>> recipeList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            Map<String, Object> recipeMap = new HashMap<>();
            recipeMap.put("id", recipe.getId());
            recipeMap.put("name", recipe.getName());
            recipeMap.put("difficulty", recipe.getDifficulty());
            recipeMap.put("time", recipe.getTime());
            recipeMap.put("image", recipe.getImage());
            recipeList.add(recipeMap);
        }
        
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", recipeList);
        return result;
    }

    /**
     * 获取菜谱详情
     */
    @GetMapping("/recipe/detail/{recipeId}")
    public Map<String, Object> getRecipeDetail(@PathVariable Integer recipeId) {
        Map<String, Object> result = new HashMap<>();
        
        Recipe recipe = recipeService.findById(recipeId);
        
        if (recipe == null) {
            result.put("code", 404);
            result.put("message", "菜谱不存在");
            return result;
        }
        
        result.put("code", 200);
        result.put("message", "查询成功");
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", recipe.getId());
        data.put("name", recipe.getName());
        data.put("image", recipe.getImage());
        data.put("difficulty", recipe.getDifficulty());
        data.put("time", recipe.getTime());
        data.put("servings", recipe.getServings());
        data.put("ingredients", recipe.getIngredients());
        data.put("steps", recipe.getSteps());
        data.put("tips", recipe.getTips());
        
        result.put("data", data);
        return result;
    }

    /**
     * 获取热门蔬菜列表
     */
    @GetMapping("/popular")
    public Map<String, Object> getPopularVegetables() {
        Map<String, Object> result = new HashMap<>();
        
        List<Vegetable> vegetables = vegetableService.findPopular(10);
        
        List<Map<String, Object>> vegetableList = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            Map<String, Object> vegeMap = new HashMap<>();
            vegeMap.put("id", vegetable.getId());
            vegeMap.put("name", vegetable.getName());
            vegeMap.put("image", vegetable.getImage());
            vegeMap.put("category", vegetable.getCategory());
            vegetableList.add(vegeMap);
        }
        
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", vegetableList);
        return result;
    }

    /**
     * 搜索蔬菜
     */
    @GetMapping("/search")
    public Map<String, Object> searchVegetables(@RequestParam String keyword) {
        Map<String, Object> result = new HashMap<>();
        
        List<Vegetable> vegetables = vegetableService.searchByKeyword(keyword);
        
        List<Map<String, Object>> vegetableList = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            Map<String, Object> vegeMap = new HashMap<>();
            vegeMap.put("id", vegetable.getId());
            vegeMap.put("name", vegetable.getName());
            vegeMap.put("image", vegetable.getImage());
            vegeMap.put("category", vegetable.getCategory());
            vegetableList.add(vegeMap);
        }
        
        result.put("code", 200);
        result.put("message", "搜索成功");
        result.put("data", vegetableList);
        return result;
    }

    /**
     * 获取识别历史记录
     */
    @GetMapping("/history/{userId}")
    public Map<String, Object> getRecognitionHistory(@PathVariable Integer userId) {
        Map<String, Object> result = new HashMap<>();
        
        List<RecognitionHistory> historyList = recognitionHistoryService.findByUserId(userId);
        
        List<Map<String, Object>> historyData = new ArrayList<>();
        for (RecognitionHistory history : historyList) {
            Map<String, Object> historyMap = new HashMap<>();
            historyMap.put("id", history.getId());
            historyMap.put("vegetableName", history.getVegetableName());
            historyMap.put("imageUrl", history.getImageUrl());
            historyMap.put("recognizeTime", history.getCreateTime());
            historyMap.put("confidence", history.getConfidence());
            historyData.add(historyMap);
        }
        
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", historyData);
        return result;
    }

    /**
     * 批量识别接口
     */
    @PostMapping("/batch-identify")
    public Map<String, Object> batchIdentify(@RequestParam("images") List<MultipartFile> images, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        Integer userId = getUserId(session);
        List<Map<String, Object>> results = new ArrayList<>();
        
        for (int i = 0; i < images.size(); i++) {
            // TODO: 调用AI识别服务
            String vegetableName = "蔬菜" + (i + 1);
            Vegetable vegetable = vegetableService.findByName(vegetableName);
            
            Map<String, Object> item = new HashMap<>();
            item.put("index", i);
            item.put("vegetableName", vegetable != null ? vegetable.getName() : vegetableName);
            item.put("confidence", 0.90 + Math.random() * 0.09);
            
            if (vegetable != null) {
                item.put("vegetableId", vegetable.getId());
            }
            
            results.add(item);
        }
        
        result.put("code", 200);
        result.put("message", "批量识别成功");
        result.put("data", results);
        return result;
    }

    /**
     * 获取所有蔬菜列表
     */
    @GetMapping("/vegetables")
    public Map<String, Object> getAllVegetables() {
        Map<String, Object> result = new HashMap<>();
        
        List<Vegetable> vegetables = vegetableService.findAll();
        
        List<Map<String, Object>> vegetableList = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            Map<String, Object> vegeMap = new HashMap<>();
            vegeMap.put("id", vegetable.getId());
            vegeMap.put("name", vegetable.getName());
            vegeMap.put("nameEn", vegetable.getNameEn());
            vegeMap.put("category", vegetable.getCategory());
            vegeMap.put("image", vegetable.getImage());
            vegeMap.put("nutrition", vegetable.getNutrition());
            vegetableList.add(vegeMap);
        }
        
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", vegetableList);
        return result;
    }

    private Integer getUserId(HttpSession session) {
        try {
            com.mall.vegetable.pojo.User user = (com.mall.vegetable.pojo.User) session.getAttribute("user");
            return user != null ? user.getId() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
