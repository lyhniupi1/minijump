package com.bocom.epcc.services;

import com.bocom.epcc.dao.EmailDao;
import com.bocom.epcc.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {
    
    @Autowired
    private EmailDao emailDao;
    
    public List<Email> getAllEmails() {
        return emailDao.findAll();
    }
    
    public Email getEmailByEmail(String email) {
        return emailDao.findByEmail(email);
    }
    
    @Transactional
    public Email createEmail(Email email) {
        if (emailDao.countByEmail(email.getEmail()) > 0) {
            throw new RuntimeException("邮箱已存在: " + email.getEmail());
        }
        email.setCreateTime(LocalDateTime.now());
        email.setLastModTime(LocalDateTime.now());
        emailDao.insert(email);
        return email;
    }
    
    @Transactional
    public Email updateEmail(Email email) {
        Email existingEmail = emailDao.findByEmail(email.getEmail());
        if (existingEmail == null) {
            throw new RuntimeException("邮箱不存在: " + email.getEmail());
        }
        email.setLastModTime(LocalDateTime.now());
        emailDao.update(email);
        return email;
    }
    
    @Transactional
    public void deleteEmail(String email) {
        if (emailDao.countByEmail(email) == 0) {
            throw new RuntimeException("邮箱不存在: " + email);
        }
        emailDao.deleteByEmail(email);
    }
    
    @Transactional
    public Email createOrUpdateEmail(Email email, String modTeller) {
        email.setModTeller(modTeller);
        Email existingEmail = emailDao.findByEmail(email.getEmail());
        
        if (existingEmail == null) {
            return createEmail(email);
        } else {
            return updateEmail(email);
        }
    }
}