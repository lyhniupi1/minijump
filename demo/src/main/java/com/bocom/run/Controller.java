package com.bocom.run;

import com.bocom.epcc.action.UserAction;
import com.bocom.epcc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class Controller {

    @Autowired
    private UserAction userAction;

    // 创建用户
    @PostMapping
    public User createUser(@RequestParam String name, @RequestParam String email) {
        return userAction.createUser(name, email);
    }

    // 获取所有用户
    @GetMapping
    public List<User> getAllUsers() {
        return userAction.getAllUsers();
    }

    // 根据ID获取用户
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userAction.getUserById(id);
    }

    // 更新用户
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        return userAction.updateUser(id, name, email);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userAction.deleteUser(id);
    }
}
