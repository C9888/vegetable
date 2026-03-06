package com.mall.vegetable.controller;

import com.mall.vegetable.pojo.ApiResponse;
import com.mall.vegetable.pojo.User;
import com.mall.vegetable.pojo.Vegetable;
import com.mall.vegetable.service.BrowseHistoryService;
import com.mall.vegetable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BrowseHistoryService browseHistoryService;

    @GetMapping("/history/{userId}")
    public ApiResponse<List<Vegetable>> getBrowseHistory(@PathVariable Integer userId) {
        List<Vegetable> history = browseHistoryService.getRecentHistory(userId);
        return ApiResponse.success(history);
    }

    @PostMapping("/history/add")
    public ApiResponse<String> addBrowseHistory(@RequestParam Integer userId, @RequestParam Integer vegetableId) {
        try {
            browseHistoryService.addHistory(userId, vegetableId);
            return ApiResponse.success("添加成功");
        } catch (Exception e) {
            return ApiResponse.error("添加失败: " + e.getMessage());
        }
    }

    @PostMapping("/changePassword")
    public ApiResponse<String> changePassword(@RequestParam Integer userId,
                                               @RequestParam String oldPassword,
                                               @RequestParam String newPassword) {
        try {
            boolean success = userService.changePassword(userId, oldPassword, newPassword);
            if (success) {
                return ApiResponse.success("密码修改成功");
            } else {
                return ApiResponse.error("原密码错误");
            }
        } catch (Exception e) {
            return ApiResponse.error("修改失败: " + e.getMessage());
        }
    }

    @PostMapping("/changePhone")
    public ApiResponse<String> changePhone(@RequestParam Integer userId, @RequestParam String phone) {
        try {
            userService.updatePhone(userId, phone);
            return ApiResponse.success("手机号修改成功");
        } catch (Exception e) {
            return ApiResponse.error("修改失败: " + e.getMessage());
        }
    }

    @PostMapping("/updateProfile")
    public ApiResponse<String> updateProfile(@RequestParam Integer userId,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) String avatar) {
        try {
            User user = userService.findById(userId);
            if (user == null) {
                return ApiResponse.error("用户不存在");
            }
            
            if (email != null) {
                user.setEmail(email);
            }
            if (avatar != null) {
                user.setAvatar(avatar);
            }
            
            userService.updateUser(user);
            return ApiResponse.success("个人信息更新成功");
        } catch (Exception e) {
            return ApiResponse.error("更新失败: " + e.getMessage());
        }
    }
}
