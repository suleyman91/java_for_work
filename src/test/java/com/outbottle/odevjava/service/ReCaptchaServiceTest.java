/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.service;
import com.outbottle.odevjava.service.ReCaptchaServiceTest;
import com.outbottle.odevjava.service.tanim.ReCaptchaService;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import util.AbstractTest;

/**
 *
 * @author süleyman
 */
public class ReCaptchaServiceTest extends AbstractTest{

    private ReCaptchaService reCaptchaService;
    private ReCaptchaImpl reCaptcha;
    private ReCaptchaResponse reCaptchaResponse;

    @Before
    public void setup() {
        reCaptchaService = spy(new ReCaptchaService());
        reCaptcha = mock(ReCaptchaImpl.class);
        reCaptchaResponse = mock(ReCaptchaResponse.class);

        reCaptchaService.setReCaptcha(reCaptcha);
    }

    @Test
    public void controlCaptchaTestOk() {
        doReturn(reCaptchaResponse).when(reCaptcha).checkAnswer(anyString(), anyString(), anyString());
        doReturn(true).when(reCaptchaResponse).isValid();

        boolean isValid = reCaptchaService.controlCaptcha("a", "b", "c");

        verify(reCaptcha, times(1)).checkAnswer(eq("a"), eq("b"), eq("c"));
        verify(reCaptchaResponse, times(1)).isValid();
        assertEquals(true, isValid);
    }

    @Test
    public void controlCaptchaTestFail() {
        doReturn(reCaptchaResponse).when(reCaptcha).checkAnswer(anyString(), anyString(), anyString());
        doReturn(false).when(reCaptchaResponse).isValid();

        boolean isValid = reCaptchaService.controlCaptcha("a", "b", "c");

        verify(reCaptcha, times(1)).checkAnswer(eq("a"), eq("b"), eq("c"));
        verify(reCaptchaResponse, times(1)).isValid();
        assertEquals(false, isValid);
    }
}
