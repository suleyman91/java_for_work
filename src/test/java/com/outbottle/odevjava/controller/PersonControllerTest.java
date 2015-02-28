/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.controller;
import com.outbottle.odevjava.entities.User;
import com.outbottle.odevjava.service.IPersonService;
import util.AbstractTest;
import com.outbottle.odevjava.service.tanim.ReCaptchaService;
import com.outbottle.odevjava.service.tanim.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 *
 * @author süleyman
 */
public class PersonControllerTest extends AbstractTest {
    
    private static final String WRONG_CAPTCHA = "Captcha is wrong!";
    private static final String USER_SAVED = "User saved";
    private static final String USER_DELETED = "User deleted";
    private static final String USER_UPDATED = "User updated";

    private MockMvc mockMvc;
    private PersonController personController;
    private ReCaptchaService reCaptchaService;
    private PersonService personService;
    private static final ModelMap modelMap = new ModelMap();
    private HttpServletRequest request;
    private HttpServletResponse response;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    @Override
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();

        personController = spy(new PersonController());
        reCaptchaService = mock(ReCaptchaService.class);
        personService = mock(PersonService.class);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        personController.setReCaptchaService(reCaptchaService);
        personController.setUserService( personService);
    }

    @Test
    public void indexTest() throws Exception {
        List<User> userList = new LinkedList<User>();
        userList.add(new User());

        doReturn(userList).when(personService).findAll();

        personController.index(modelMap, response);

        verify(personService).findAll();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"));

    }

    @Test
    public void saveTestValidUser() throws Exception {
        User u = new User("denemeAd", "denemeSoyad", "112233445566");
        String ip = "1.1.1.2";
        String challenge = "chaptcha";
        String responseCaptcha = "response";

        doReturn(true).when(reCaptchaService).controlCaptcha(anyString(),anyString(), anyString());
        doNothing().when(personService).save(any(User.class));
        doReturn(ip).when(request).getRemoteAddr();

        String saveResponse = personController.save(u, challenge, responseCaptcha, response, request);

        verify(reCaptchaService).controlCaptcha(eq(ip), eq(challenge), eq(responseCaptcha));
        verify(response, never()).setStatus(eq(HttpStatus.NOT_ACCEPTABLE.value()));
        verify(personService).save(eq(u));
        verify(response).setStatus(eq(HttpStatus.CREATED.value()));

        assertEquals(USER_SAVED, saveResponse);
    }

    @Test
    public void saveTestValidUserWrongCaptcha() throws Exception {
        User u = new User("denemeAd", "denemeSoyad", "112233445566");
        String ip = "1.1.1.2";
        String challenge = "chaptcha";
        String responseCaptcha = "chaptcha";

        doReturn(false).when(reCaptchaService).controlCaptcha(anyString(),anyString(), anyString());
        doNothing().when(personService).save(any(User.class));
        doReturn(ip).when(request).getRemoteAddr();

        String saveResponse = personController.save(u, challenge, responseCaptcha, response, request);

        verify(reCaptchaService).controlCaptcha(eq(ip), eq(challenge), eq(responseCaptcha));
        verify(response).setStatus(eq(HttpStatus.NOT_ACCEPTABLE.value()));
        verify(personService, never()).save(eq(u));
        verify(response, never()).setStatus(eq(HttpStatus.CREATED.value()));

        assertEquals(WRONG_CAPTCHA, saveResponse);
    }

    @Test
    public void deleteTest() throws Exception {
        String id = "user_id";

        doNothing().when(personService).delete(anyString());

        personController.delete(id, response, request);

        verify(personService).delete(id);
        verify(response).setStatus(eq(HttpStatus.OK.value()));

        mockMvc.perform(delete("/user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(USER_DELETED));
    }

    @Test
    public void updateTest() {
        User u = new User("denemeAd", "denemeSoyad", "112233445566");

        doNothing().when(personService).save(any(User.class));

        String updateResponse = personController.update(u, response, request);

        verify(personService).save(eq(u));
        verify(response).setStatus(eq(HttpStatus.OK.value()));

        assertEquals(USER_UPDATED, updateResponse);
    }

}
