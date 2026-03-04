package com.bocom.run;

import com.bocom.epcc.action.UserAction;
import com.bocom.epcc.action.EmailAction;
import com.bocom.epcc.batch.BtPbocFeeSettStep;
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

    @Autowired
    private BtPbocFeeSettStep btPbocFeeSettStep;

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

    // 费用结算批处理接口
    @PostMapping("/batch/fee-settlement")
    public Map<String, Object> executeFeeSettlement(
            @RequestParam(required = false) String startBtId,
            @RequestParam(required = false) String endBtId) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 如果提供了参数，可以传递给BtPbocFeeSettStep（当前实现中使用硬编码值）
            // 这里可以扩展BtPbocFeeSettStep以接受参数
            boolean result = btPbocFeeSettStep.execute();
            
            response.put("success", result);
            response.put("message", result ? "费用结算批处理执行成功" : "费用结算批处理执行失败");
            response.put("timestamp", System.currentTimeMillis());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "费用结算批处理执行异常: " + e.getMessage());
            response.put("timestamp", System.currentTimeMillis());
        }
        
        return response;
    }

    @GetMapping("/batch/fee-settlement")
    public Map<String, Object> getFeeSettlementInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("endpoint", "/api/batch/fee-settlement");
        response.put("method", "POST");
        response.put("description", "执行费用结算批处理，根据epcc_batch_stat表统计费用");
        response.put("parameters", "startBtId (可选), endBtId (可选) - BTID范围参数");
        response.put("curl_example", "curl -X POST \"http://localhost:8080/api/batch/fee-settlement?startBtId=20240101000000&endBtId=20241231235959\"");
        return response;
    }
}
