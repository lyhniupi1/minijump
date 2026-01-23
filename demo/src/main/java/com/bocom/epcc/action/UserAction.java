package com.bocom.epcc.action;

import com.bocom.epcc.entity.User;
import com.bocom.epcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserAction {

    @Autowired
    private UserService userService;

    // 创建用户
    public User createUser(String name, String email) {
        return userService.createUser(name, email);
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 根据ID获取用户
    public Optional<User> getUserById(Long id) {
        return userService.getUserById(id);
    }

    // 更新用户
    public User updateUser(Long id, String name, String email) {
        return userService.updateUser(id, name, email);
    }

    // 删除用户
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
