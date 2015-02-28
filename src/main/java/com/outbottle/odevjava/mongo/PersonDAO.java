/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.mongo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.outbottle.odevjava.dao.IPersonDAO;
import com.outbottle.odevjava.entities.User;
import com.outbottle.odevjava.util.MongoDoa;
import java.util.List;



/**
 *
 * @author süleyman
 */
public class PersonDAO extends MongoDoa implements IPersonDAO{
    
    @Override
    public void init() {
        if (!getMongoTemplate().collectionExists(User.class)) {
            getMongoTemplate().createCollection(User.class);
        }
    }

    @Override
    public void save(final User user) {
        getMongoTemplate().save(user);
    }

    @Override
    public void delete(final String id) {
        getMongoTemplate().remove(new Query(Criteria.where("id").is(id)), User.class);
    }

    @Override
    public User findById(final String id) {
        return getMongoTemplate().findById(id, User.class);
    }

    @Override
    public List<User> findAll() {
        return getMongoTemplate().findAll(User.class);
    }
}
