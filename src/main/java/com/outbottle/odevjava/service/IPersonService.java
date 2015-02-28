/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.service;
import com.outbottle.odevjava.entities.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

/**
 *
 * @author süleyman
 */
public interface IPersonService {
    
    public void save(final User user);

    public void delete(final String id);

    public User findById(final String id);

    public List<User> findAll();
    
}

