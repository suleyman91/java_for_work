/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.util;
import com.outbottle.odevjava.entities.User;
import java.util.List;

/**
 *
 * @author süleyman
 */
public interface GenericCrud<T extends Model>{
    public void init();

    public void save(final T t);
    
    public void delete(final String id);

    public T findById(final String id);

    public List<T> findAll();

}

    
