/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.service;
import com.outbottle.odevjava.entities.User;
import com.outbottle.odevjava.mongo.PersonDAO;
import com.outbottle.odevjava.service.tanim.PersonService;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import util.AbstractTest;

/**
 *
 * @author süleyman
 */
public class PersonServiceTest extends AbstractTest{
    
  
    private PersonService personService;
    private PersonDAO personDAO;
    private User userWithId;
    private User userWithoutId;

    @Before
    public void setup() {
        personService = spy(new PersonService());
        personDAO = mock(PersonDAO.class);

        userWithId = new User("1", "name", "lastname", "112233445566");
        userWithoutId = new User("name", "lastname", "112233445566");
        
        personService.setPersonDAO(personDAO);
            
        //userService.setPersonDAO(userDAO);
    }

    @Test
    public void saveTest() throws Exception {
        doNothing().when(personDAO).save(any(User.class));

        personService.save(userWithoutId);

        verify(personDAO, times(1)).save(eq(userWithoutId));
    }

    @Test
    public void deleteTest() throws Exception {
        String id = "1";
        doNothing().when(personDAO).delete(anyString());

        personDAO.delete(id);

        verify(personDAO, times(1)).delete(id);
    }

    @Test
    public void findByIdTest() throws Exception {
        String id = "1";
        doReturn(userWithId).when(personDAO).findById(anyString());

        User userbyId = personDAO.findById(id);

        verify(personDAO, times(1)).findById(eq(id));
        assertEquals(userWithId, userbyId);
    }

    @Test
    public void findAllTest() throws Exception {
        List<User> userList = new LinkedList<User>();
        doReturn(userList).when(personDAO).findAll();

        List<User> all = personDAO.findAll();

        verify(personDAO, times(1)).findAll();
        assertEquals(userList, all);
    }

}
