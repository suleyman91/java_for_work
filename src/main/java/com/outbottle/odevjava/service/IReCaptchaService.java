/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.service;
import net.tanesha.recaptcha.ReCaptchaImpl;


/**
 *
 * @author süleyman
 */
public interface IReCaptchaService {
    public boolean controlCaptcha(final String remoteAddr, final String challenge, final String capthcaResponse);
    
}
