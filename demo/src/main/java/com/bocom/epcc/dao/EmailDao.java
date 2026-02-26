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
            return session.selectList("emailhandle.findAll");
        }
    }
    
    public Email findByEmail(String email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("emailhandle.findByEmail", email);
        }
    }
    
    public int insert(Email email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.insert("emailhandle.insert", email);
        }
    }
    
    public int update(Email email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.update("emailhandle.update", email);
        }
    }
    
    public int deleteByEmail(String email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.delete("emailhandle.deleteByEmail", email);
        }
    }
    
    public int countByEmail(String email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("emailhandle.countByEmail", email);
        }
    }
}