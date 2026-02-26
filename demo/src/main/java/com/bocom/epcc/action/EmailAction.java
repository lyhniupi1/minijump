package com.bocom.epcc.action;

import com.bocom.epcc.entity.Email;
import com.bocom.epcc.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmailAction {
    
    @Autowired
    private EmailService emailService;
    
    public Map<String, Object> handleEmail(Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String operationType = (String) request.get("operatetype");
        String modTeller = (String) request.getOrDefault("modteller", "SYSTEM");
        
        try {
            switch (operationType) {
                case "query":
                    handleQuery(request, response);
                    break;
                case "create":
                    handleCreate(request, response);
                    break;
                case "update":
                    handleUpdate(request, response);
                    break;
                case "delete":
                    handleDelete(request, response);
                    break;
                case "createOrUpdate":
                    handleCreateOrUpdate(request, response, modTeller);
                    break;
                default:
                    response.put("success", false);
                    response.put("message", "不支持的操作类型: " + operationType);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "操作失败: " + e.getMessage());
        }
        
        return response;
    }
    
    private void handleQuery(Map<String, Object> request, Map<String, Object> response) {
        String email = (String) request.get("email");
        if (email != null && !email.trim().isEmpty()) {
            Email emailObj = emailService.getEmailByEmail(email);
            if (emailObj != null) {
                response.put("success", true);
                response.put("data", emailObj);
            } else {
                response.put("success", false);
                response.put("message", "邮箱不存在: " + email);
            }
        } else {
            List<Email> emails = emailService.getAllEmails();
            response.put("success", true);
            response.put("data", emails);
        }
    }
    
    private void handleCreate(Map<String, Object> request, Map<String, Object> response) {
        Email email = buildEmailFromRequest(request);
        Email createdEmail = emailService.createEmail(email);
        response.put("success", true);
        response.put("data", createdEmail);
        response.put("message", "邮箱创建成功");
    }
    
    private void handleUpdate(Map<String, Object> request, Map<String, Object> response) {
        Email email = buildEmailFromRequest(request);
        Email updatedEmail = emailService.updateEmail(email);
        response.put("success", true);
        response.put("data", updatedEmail);
        response.put("message", "邮箱更新成功");
    }
    
    private void handleDelete(Map<String, Object> request, Map<String, Object> response) {
        String email = (String) request.get("email");
        emailService.deleteEmail(email);
        response.put("success", true);
        response.put("message", "邮箱删除成功");
    }
    
    private void handleCreateOrUpdate(Map<String, Object> request, Map<String, Object> response, String modTeller) {
        Email email = buildEmailFromRequest(request);
        Email resultEmail = emailService.createOrUpdateEmail(email, modTeller);
        response.put("success", true);
        response.put("data", resultEmail);
        response.put("message", "邮箱创建或更新成功");
    }
    
    private Email buildEmailFromRequest(Map<String, Object> request) {
        Email email = new Email();
        email.setEmail((String) request.get("email"));
        email.setUsername((String) request.get("username"));
        email.setDescription((String) request.get("description"));
        return email;
    }
}