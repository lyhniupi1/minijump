package com.bocom.epcc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
public class Email {
    
    @Id
    @Column(name = "EEC_EMAIL")
    private String email;
    
    @Column(name = "EEC_USERNAME")
    private String username;
    
    @Column(name = "EEC_DESC")
    private String description;
    
    @Column(name = "EEC_CREATETIME")
    private LocalDateTime createTime;
    
    @Column(name = "EEC_MODTELLER")
    private String modTeller;
    
    @Column(name = "EEC_LASTMODTIME")
    private LocalDateTime lastModTime;

    // 构造函数
    public Email() {
    }

    public Email(String email, String username, String description) {
        this.email = email;
        this.username = username;
        this.description = description;
        this.createTime = LocalDateTime.now();
        this.lastModTime = LocalDateTime.now();
    }

    // Getter和Setter方法
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getModTeller() {
        return modTeller;
    }

    public void setModTeller(String modTeller) {
        this.modTeller = modTeller;
    }

    public LocalDateTime getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(LocalDateTime lastModTime) {
        this.lastModTime = lastModTime;
    }
}