/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.service.tanim;
import com.outbottle.odevjava.dao.IPersonDAO;
import com.outbottle.odevjava.entities.User;
import com.outbottle.odevjava.service.IPersonService;
import java.util.List;

/**
 *
 * @author süleyman
 */
public class PersonService implements IPersonService {
    
    private IPersonDAO personDAO;

    @Override
    public void save(User user) {
        personDAO.save(user);
        
    }

    @Override
    public void delete(String id) {
        personDAO.delete(id);

    }

    @Override
    public User findById(String id) {
        return personDAO.findById(id);

                
    }

    @Override
    public List<User> findAll() {
        return personDAO.findAll();

    }
    public void setPersonDAO(IPersonDAO personDAO) {
        this.personDAO = personDAO;
    
}
    
}

