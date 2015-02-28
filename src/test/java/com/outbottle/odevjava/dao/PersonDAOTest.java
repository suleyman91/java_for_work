/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.dao;
import com.outbottle.odevjava.mongo.PersonDAO;
import com.outbottle.odevjava.entities.User;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import util.AbstractTest;

/**
 *
 * @author süleyman
 */
public class PersonDAOTest extends AbstractTest{

    private PersonDAO userDAO;
    private MongoTemplate mockMongoTemplate;
    private User userWithId;
    private User userWithoutId;

    @Before
    public void setup() {
        userDAO = spy(new PersonDAO());
        mockMongoTemplate = mock(MongoTemplate.class);
        userWithId = new User("1", "name", "lastname", "112233445566");
        userDAO.setMongoTemplate(mockMongoTemplate);
    }

    @Test
    public void saveTest() throws Exception {
        doNothing().when(mockMongoTemplate).save(any(User.class));

        userDAO.save(userWithoutId);

        verify(userDAO, times(1)).save(eq(userWithoutId));
    }

    @Test
    public void deleteTest() throws Exception {
        String id = "1";
        doNothing().when(mockMongoTemplate).remove(any(Query.class), any(Class.class));

        userDAO.delete(id);

        verify(mockMongoTemplate, times(1)).remove(any(Query.class), eq(User.class));
    }

    @Test
    public void findByIdTest() throws Exception {
        String id = "1";
        doReturn(userWithId).when(mockMongoTemplate).findById(any(String.class), any(Class.class));

        User userbyId = userDAO.findById(id);

        verify(mockMongoTemplate, times(1)).findById(eq(id), eq(User.class));
        assertEquals(userWithId, userbyId);
    }

    @Test
    public void findAllTest() throws Exception {
        List<User> userList = new LinkedList<User>();
        doReturn(userList).when(mockMongoTemplate).findAll(any(Class.class));

        List<User> all = userDAO.findAll();

        verify(mockMongoTemplate, times(1)).findAll(eq(User.class));
        assertEquals(userList, all);
    }
}
