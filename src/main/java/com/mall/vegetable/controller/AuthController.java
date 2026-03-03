package com.mall.vegetable.controller;

import com.mall.vegetable.pojo.User;
import com.mall.vegetable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * 注册页面
     */
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    /**
     * 用户登录
     */
    @PostMapping("/auth/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(defaultValue = "0") Integer role,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        
        User user = userService.login(username, password, role);
        
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
            return "redirect:/login";
        }
        
        if (user.getStatus() == 0) {
            redirectAttributes.addFlashAttribute("error", "账号已被禁用");
            return "redirect:/login";
        }
        
        // 保存用户到Session
        session.setAttribute("user", user);
        
        // 根据角色跳转
        if (user.getRole() == 1) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/home";
        }
    }

    /**
     * 用户注册
     */
    @PostMapping("/auth/register")
    public String register(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          @RequestParam(required = false) String email,
                          @RequestParam(required = false) String phone,
                          RedirectAttributes redirectAttributes) {
        
        // 验证密码
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "两次输入的密码不一致");
            return "redirect:/register";
        }
        
        // 验证用户名
        if (username == null || username.trim().length() < 3) {
            redirectAttributes.addFlashAttribute("error", "用户名至少3个字符");
            return "redirect:/register";
        }
        
        // 验证密码长度
        if (password.length() < 6) {
            redirectAttributes.addFlashAttribute("error", "密码至少6个字符");
            return "redirect:/register";
        }
        
        // 检查用户名是否已存在
        if (userService.isUsernameExists(username)) {
            redirectAttributes.addFlashAttribute("error", "用户名已存在");
            return "redirect:/register";
        }
        
        // 创建用户
        User user = new User(1, "admin", "123456");
        user.setUsername(username.trim());
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(0); // 普通用户
        user.setStatus(1);
        
        boolean success = userService.register(user);
        
        if (success) {
            redirectAttributes.addFlashAttribute("success", "注册成功，请登录");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "注册失败，请重试");
            return "redirect:/register";
        }
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
