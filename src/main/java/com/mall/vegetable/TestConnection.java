package com.mall.vegetable;

import com.mall.vegetable.service.UserService;
import com.mall.vegetable.service.VegetableService;
import com.mall.vegetable.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestConnection implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private RecipeService recipeService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== 测试数据库连接 ===");
        
        try {
            int userCount = userService.findAll().size();
            System.out.println("用户数量: " + userCount);
        } catch (Exception e) {
            System.out.println("用户查询失败: " + e.getMessage());
        }
        
        try {
            int vegetableCount = vegetableService.findAll().size();
            System.out.println("蔬菜数量: " + vegetableCount);
        } catch (Exception e) {
            System.out.println("蔬菜查询失败: " + e.getMessage());
        }
        
        try {
            int recipeCount = recipeService.findAll().size();
            System.out.println("菜谱数量: " + recipeCount);
        } catch (Exception e) {
            System.out.println("菜谱查询失败: " + e.getMessage());
        }
        
        System.out.println("=== 测试完成 ===");
    }
}
