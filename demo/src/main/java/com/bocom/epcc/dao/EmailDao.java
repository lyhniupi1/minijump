package com.bocom.epcc.dao;

import com.bocom.epcc.entity.Email;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailDao {
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    public List<Email> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.bocom.epcc.dao.EmailRepository.findAll");
        }
    }
    
    public Email findByEmail(String email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.bocom.epcc.dao.EmailRepository.findByEmail", email);
        }
    }
    
    public int insert(Email email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.insert("com.bocom.epcc.dao.EmailRepository.insert", email);
        }
    }
    
    public int update(Email email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.update("com.bocom.epcc.dao.EmailRepository.update", email);
        }
    }
    
    public int deleteByEmail(String email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.delete("com.bocom.epcc.dao.EmailRepository.deleteByEmail", email);
        }
    }
    
    public int countByEmail(String email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.bocom.epcc.dao.EmailRepository.countByEmail", email);
        }
    }
}