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
        User user = new User();
        user.setUsername(username.trim());
        user.setPassword(password);
        user.setEmail("".equals(email) ? null : email);
        user.setPhone("".equals(phone) ? null : phone);
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
     * API用户登录（供Android端使用）
     */
    @PostMapping("/api/auth/login")
    @ResponseBody
    public java.util.Map<String, Object> apiLogin(@RequestParam String username,
                                                   @RequestParam String password,
                                                   @RequestParam(defaultValue = "0") Integer role) {
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        
        User user = userService.login(username, password, role);
        
        if (user == null) {
            result.put("code", 401);
            result.put("message", "用户名或密码错误");
            return result;
        }
        
        if (user.getStatus() == 0) {
            result.put("code", 403);
            result.put("message", "账号已被禁用");
            return result;
        }
        
        result.put("code", 200);
        result.put("message", "登录成功");
        
        java.util.Map<String, Object> userInfo = new java.util.HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUsername());
        userInfo.put("role", user.getRole());
        userInfo.put("email", user.getEmail());
        userInfo.put("phone", user.getPhone());
        
        result.put("data", userInfo);
        return result;
    }

    /**
     * API用户注册（供Android端使用）
     */
    @PostMapping("/api/auth/register")
    @ResponseBody
    public java.util.Map<String, Object> apiRegister(@RequestParam String username,
                                                      @RequestParam String password,
                                                      @RequestParam String confirmPassword,
                                                      @RequestParam(required = false) String email,
                                                      @RequestParam(required = false) String phone) {
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        
        // 验证密码
        if (!password.equals(confirmPassword)) {
            result.put("code", 400);
            result.put("message", "两次输入的密码不一致");
            return result;
        }
        
        // 验证用户名
        if (username == null || username.trim().length() < 3) {
            result.put("code", 400);
            result.put("message", "用户名至少3个字符");
            return result;
        }
        
        // 验证密码长度
        if (password.length() < 6) {
            result.put("code", 400);
            result.put("message", "密码至少6个字符");
            return result;
        }
        
        // 检查用户名是否已存在
        if (userService.isUsernameExists(username)) {
            result.put("code", 400);
            result.put("message", "用户名已存在");
            return result;
        }
        
        // 创建用户
        User user = new User();
        user.setUsername(username.trim());
        user.setPassword(password);
        user.setEmail("".equals(email) ? null : email);
        user.setPhone("".equals(phone) ? null : phone);
        user.setRole(0); // 普通用户
        user.setStatus(1);
        
        boolean success = userService.register(user);
        
        if (success) {
            result.put("code", 200);
            result.put("message", "注册成功");
        } else {
            result.put("code", 500);
            result.put("message", "注册失败，请重试");
        }
        
        return result;
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
