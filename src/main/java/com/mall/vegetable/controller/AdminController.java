package com.mall.vegetable.controller;

import com.mall.vegetable.pojo.Recipe;
import com.mall.vegetable.pojo.User;
import com.mall.vegetable.pojo.Vegetable;
import com.mall.vegetable.service.RecipeService;
import com.mall.vegetable.service.UserService;
import com.mall.vegetable.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class  AdminController {

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    private boolean checkAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && user.getRole() == 1;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        int userCount = userService.findAll().size();
        int vegetableCount = vegetableService.findAll().size();
        int recipeCount = recipeService.findAll().size();
        
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("userCount", userCount);
        model.addAttribute("vegetableCount", vegetableCount);
        model.addAttribute("recipeCount", recipeCount);
        return "admin/dashboard";
    }

    @GetMapping("/vegetables")
    public String vegetables(@RequestParam(required = false) String keyword,
                             HttpSession session, Model model) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        List<Vegetable> vegetables;
        if (keyword != null && !keyword.trim().isEmpty()) {
            vegetables = vegetableService.searchByKeyword(keyword);
        } else {
            vegetables = vegetableService.findAll();
        }
        
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("vegetables", vegetables);
        model.addAttribute("keyword", keyword);
        return "admin/vegetables";
    }

    @GetMapping("/vegetable/{id}")
    @ResponseBody
    public Map<String, Object> getVegetable(@PathVariable Integer id, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (!checkAdmin(session)) {
            result.put("code", 403);
            result.put("message", "无权限");
            return result;
        }
        
        Vegetable vegetable = vegetableService.findById(id);
        if (vegetable == null) {
            result.put("code", 404);
            result.put("message", "蔬菜不存在");
            return result;
        }
        
        result.put("code", 200);
        result.put("data", vegetable);
        return result;
    }

    @PostMapping("/vegetable/save")
    public String saveVegetable(Vegetable vegetable, HttpSession session) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        System.out.println("Saving vegetable: " + vegetable);
        System.out.println("Vegetable ID: " + vegetable.getId());
        System.out.println("Vegetable Name: " + vegetable.getName());
        
        boolean result;
        if (vegetable.getId() == null) {
            result = vegetableService.addVegetable(vegetable);
            System.out.println("Add vegetable result: " + result);
        } else {
            result = vegetableService.updateVegetable(vegetable);
            System.out.println("Update vegetable result: " + result);
        }
        
        return "redirect:/admin/vegetables";
    }

    @GetMapping("/vegetable/delete/{id}")
    public String deleteVegetable(@PathVariable Integer id, HttpSession session) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        vegetableService.deleteVegetable(id);
        return "redirect:/admin/vegetables";
    }

    @GetMapping("/recipes")
    public String recipes(@RequestParam(required = false) String keyword,
                          @RequestParam(required = false) Integer vegetableId,
                          HttpSession session, Model model) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        List<Recipe> recipes;
        if (vegetableId != null) {
            recipes = recipeService.findByVegetableId(vegetableId);
        } else if (keyword != null && !keyword.trim().isEmpty()) {
            recipes = recipeService.searchByKeyword(keyword);
        } else {
            recipes = recipeService.findAll();
        }
        
        List<Vegetable> vegetables = vegetableService.findAll();
        
        for (Recipe recipe : recipes) {
            Vegetable vege = vegetableService.findById(recipe.getVegetableId());
            if (vege != null) {
                recipe.setVegetableName(vege.getName());
            }
        }
        
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("recipes", recipes);
        model.addAttribute("vegetables", vegetables);
        model.addAttribute("keyword", keyword);
        model.addAttribute("vegetableId", vegetableId);
        return "admin/recipes";
    }

    @GetMapping("/recipe/{id}")
    @ResponseBody
    public Map<String, Object> getRecipe(@PathVariable Integer id, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (!checkAdmin(session)) {
            result.put("code", 403);
            result.put("message", "无权限");
            return result;
        }
        
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
            result.put("code", 404);
            result.put("message", "菜谱不存在");
            return result;
        }
        
        result.put("code", 200);
        result.put("data", recipe);
        return result;
    }

    @PostMapping("/recipe/save")
    public String saveRecipe(Recipe recipe, HttpSession session) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
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
        
        return "redirect:/admin/recipes";
    }

    @GetMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable Integer id, HttpSession session) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        recipeService.deleteRecipe(id);
        return "redirect:/admin/recipes";
    }

    @GetMapping("/users")
    public String users(@RequestParam(required = false) String keyword,
                       HttpSession session, Model model) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        List<User> users;
        if (keyword != null && !keyword.trim().isEmpty()) {
            users = userService.searchByKeyword(keyword);
        } else {
            users = userService.findAll();
        }
        
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        return "admin/users";
    }

    @GetMapping("/user/toggle/{id}")
    public String toggleUserStatus(@PathVariable Integer id, HttpSession session) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        userService.toggleStatus(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Integer id, HttpSession session) {
        if (!checkAdmin(session)) {
            return "redirect:/login";
        }
        
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
