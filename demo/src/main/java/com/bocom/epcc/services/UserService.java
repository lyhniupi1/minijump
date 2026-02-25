package com.bocom.epcc.services;

import com.bocom.epcc.dao.UserRepository;
import com.bocom.epcc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 创建用户
    public User createUser(String name, String email) {
        User user = new User(name, email);
        return null;
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        return null;
    }

    // 根据ID获取用户
    public Optional<User> getUserById(Long id) {
        return null;
    }

    // 更新用户
    public User updateUser(Long id, String name, String email) {
        return null;
    }

    // 删除用户
    public void deleteUser(Long id) {
    }
}
