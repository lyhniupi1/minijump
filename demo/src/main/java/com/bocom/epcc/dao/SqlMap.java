package com.bocom.epcc.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SqlMap {
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    public <T> List<T> selectList(String statement) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList(statement);
        }
    }
    
    public <T> List<T> selectList(String statement, Object parameter) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList(statement, parameter);
        }
    }
    
    public <T> T selectOne(String statement) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne(statement);
        }
    }
    
    public <T> T selectOne(String statement, Object parameter) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne(statement, parameter);
        }
    }
    
    public int insert(String statement) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.insert(statement);
        }
    }
    
    public int insert(String statement, Object parameter) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.insert(statement, parameter);
        }
    }
    
    public int update(String statement) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.update(statement);
        }
    }
    
    public int update(String statement, Object parameter) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.update(statement, parameter);
        }
    }
    
    public int delete(String statement) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.delete(statement);
        }
    }
    
    public int delete(String statement, Object parameter) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.delete(statement, parameter);
        }
    }
}