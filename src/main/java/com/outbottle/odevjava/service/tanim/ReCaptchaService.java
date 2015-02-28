/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.service.tanim;
import com.outbottle.odevjava.service.IReCaptchaService;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;


/**
 *
 * @author süleyman
 */
public class ReCaptchaService implements IReCaptchaService{
    private ReCaptchaImpl reCaptcha;
       
    @Override
    public boolean controlCaptcha(String remoteAddr, String challenge, String capthcaResponse) {
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, capthcaResponse);
        return reCaptchaResponse.isValid();
    }
    public void setReCaptcha(ReCaptchaImpl reCaptcha) {
        this.reCaptcha = reCaptcha;
    }
}



