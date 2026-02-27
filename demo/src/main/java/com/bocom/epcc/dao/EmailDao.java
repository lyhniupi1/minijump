package com.bocom.epcc.dao;

import com.bocom.epcc.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailDao {
    
    @Autowired
    private SqlMap sqlMap;
    
    public List<Email> findAll() {
        return sqlMap.selectList("emailhandle.findAll");
    }
    
    public Email findByEmail(String email) {
        return sqlMap.selectOne("emailhandle.findByEmail", email);
    }
    
    public int insert(Email email) {
        return sqlMap.insert("emailhandle.insert", email);
    }
    
    public int update(Email email) {
        return sqlMap.update("emailhandle.update", email);
    }
    
    public int deleteByEmail(String email) {
        return sqlMap.delete("emailhandle.deleteByEmail", email);
    }
    
    public int countByEmail(String email) {
        return sqlMap.selectOne("emailhandle.countByEmail", email);
    }
}