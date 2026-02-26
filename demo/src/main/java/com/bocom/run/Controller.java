package com.bocom.run;

import com.bocom.epcc.action.UserAction;
import com.bocom.epcc.action.EmailAction;
import com.bocom.epcc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private UserAction userAction;

    @Autowired
    private EmailAction emailAction;

    // 用户相关接口
    @PostMapping("/users")
    public User createUser(@RequestParam String name, @RequestParam String email) {
        return userAction.createUser(name, email);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userAction.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userAction.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        return userAction.updateUser(id, name, email);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userAction.deleteUser(id);
    }

    // 邮箱相关接口
    @PostMapping("/handleEmail")
    public Map<String, Object> handleEmail(@RequestBody Map<String, Object> request) {
        return emailAction.handleEmail(request);
    }

    @GetMapping("/emails")
    public Map<String, Object> getAllEmails(@RequestParam(required = false) String email) {
        Map<String, Object> request = new HashMap<>();
        request.put("operatetype", "query");
        if (email != null && !email.trim().isEmpty()) {
            request.put("email", email);
        }
        return emailAction.handleEmail(request);
    }

    @PostMapping("/emails")
    public Map<String, Object> createEmail(@RequestBody Map<String, Object> request) {
        request.put("operatetype", "create");
        return emailAction.handleEmail(request);
    }

    @PutMapping("/emails")
    public Map<String, Object> updateEmail(@RequestBody Map<String, Object> request) {
        request.put("operatetype", "update");
        return emailAction.handleEmail(request);
    }

    @DeleteMapping("/emails")
    public Map<String, Object> deleteEmail(@RequestParam String email) {
        Map<String, Object> request = new HashMap<>();
        request.put("operatetype", "delete");
        request.put("email", email);
        return emailAction.handleEmail(request);
    }
}
