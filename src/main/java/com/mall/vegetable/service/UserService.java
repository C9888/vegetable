package com.mall.vegetable.service;

import com.mall.vegetable.dao.UserMapper;
import com.mall.vegetable.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 用户登录
     */
    public User login(String username, String password, Integer role) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return null;
        }
        
        // 先尝试BCrypt加密验证
        if (passwordEncoder.matches(password, user.getPassword())) {
            // 验证角色
            if (role != null && !user.getRole().equals(role)) {
                return null;
            }
            return user;
        }
        
        // 如果BCrypt验证失败，尝试明文密码验证（兼容旧数据）
        if (password.equals(user.getPassword())) {
            // 验证角色
            if (role != null && !user.getRole().equals(role)) {
                return null;
            }
            return user;
        }
        
        return null;
    }

    /**
     * 用户注册
     */
    public boolean register(User user) {
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user) > 0;
    }

    /**
     * 检查用户名是否存在
     */
    public boolean isUsernameExists(String username) {
        return userMapper.findByUsername(username) != null;
    }

    /**
     * 根据ID查询用户
     */
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 更新用户信息
     */
    public boolean updateUser(User user) {
        return userMapper.update(user) > 0;
    }

    /**
     * 修改密码
     */
    public boolean changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.findById(userId);
        if (user == null) {
            return false;
        }
        
        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }
        
        // 更新密码
        String encodedPassword = passwordEncoder.encode(newPassword);
        return userMapper.updatePassword(userId, encodedPassword) > 0;
    }

    /**
     * 获取所有用户
     */
    public java.util.List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * 切换用户状态
     */
    public boolean toggleStatus(Integer userId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            return false;
        }
        user.setStatus(user.getStatus() == 1 ? 0 : 1);
        return userMapper.update(user) > 0;
    }
    
    /**
     * 更新密码
     */
    public boolean updatePassword(Integer userId, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        return userMapper.updatePassword(userId, encodedPassword) > 0;
    }
    
    /**
     * 更新手机号
     */
    public boolean updatePhone(Integer userId, String phone) {
        return userMapper.updatePhone(userId, phone) > 0;
    }
}
