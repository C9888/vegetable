package com.mall.vegetable.controller;

import com.mall.vegetable.pojo.Recipe;
import com.mall.vegetable.pojo.RecognitionHistory;
import com.mall.vegetable.pojo.User;
import com.mall.vegetable.pojo.Vegetable;
import com.mall.vegetable.service.RecipeService;
import com.mall.vegetable.service.RecognitionHistoryService;
import com.mall.vegetable.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecognitionHistoryService recognitionHistoryService;

    private boolean checkUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null;
    }

    @GetMapping("/home")
    public String home(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) String category,
                       HttpSession session, Model model) {
        if (!checkUser(session)) {
            return "redirect:/login";
        }
        
        List<Vegetable> vegetables;
        if (keyword != null && !keyword.trim().isEmpty()) {
            vegetables = vegetableService.searchByKeyword(keyword);
        } else if (category != null && !category.trim().isEmpty()) {
            vegetables = vegetableService.findByCategory(category);
        } else {
            vegetables = vegetableService.findAll();
        }
        
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("vegetables", vegetables);
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);
        return "user/home";
    }

    @GetMapping("/vegetable/{id}")
    public String vegetableDetail(@PathVariable Integer id, HttpSession session, Model model) {
        if (!checkUser(session)) {
            return "redirect:/login";
        }
        
        Vegetable vegetable = vegetableService.findById(id);
        if (vegetable == null) {
            return "redirect:/user/home";
        }
        
        List<Recipe> recipes = recipeService.findByVegetableId(id);
        
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("vegetable", vegetable);
        model.addAttribute("recipes", recipes);
        return "user/vegetable_detail";
    }

    @GetMapping("/recipes")
    public String recipes(@RequestParam(required = false) String keyword,
                         HttpSession session, Model model) {
        if (!checkUser(session)) {
            return "redirect:/login";
        }
        
        List<Recipe> recipes;
        if (keyword != null && !keyword.trim().isEmpty()) {
            recipes = recipeService.searchByKeyword(keyword);
        } else {
            recipes = recipeService.findAll();
        }
        
        for (Recipe recipe : recipes) {
            Vegetable vege = vegetableService.findById(recipe.getVegetableId());
            if (vege != null) {
                recipe.setVegetableName(vege.getName());
            }
        }
        
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("recipes", recipes);
        model.addAttribute("keyword", keyword);
        return "user/recipes";
    }

    @GetMapping("/recipe/{id}")
    public String recipeDetail(@PathVariable Integer id, HttpSession session, Model model) {
        if (!checkUser(session)) {
            return "redirect:/login";
        }
        
        Recipe recipe = recipeService.findById(id);
        if (recipe == null) {
            return "redirect:/user/recipes";
        }
        
        Vegetable vegetable = vegetableService.findById(recipe.getVegetableId());
        if (vegetable != null) {
            recipe.setVegetableName(vegetable.getName());
        }
        
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("recipe", recipe);
        model.addAttribute("vegetable", vegetable);
        return "user/recipe_detail";
    }

    @GetMapping("/favorites")
    public String favorites(HttpSession session, Model model) {
        if (!checkUser(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", session.getAttribute("user"));
        return "user/favorites";
    }

    @GetMapping("/history")
    public String history(HttpSession session, Model model) {
        if (!checkUser(session)) {
            return "redirect:/login";
        }

        User user = (User) session.getAttribute("user");
        List<RecognitionHistory> histories = recognitionHistoryService.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("histories", histories);
        return "user/history";
    }
    
    @GetMapping("/camera")
    public String camera(HttpSession session, Model model) {
        if (!checkUser(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", session.getAttribute("user"));
        return "user/camera";
    }
}
