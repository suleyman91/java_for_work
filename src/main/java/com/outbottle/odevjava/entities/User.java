/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.odevjava.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.data.mongodb.core.mapping.Document;
import com.outbottle.odevjava.util.Model;



/**
 *
 * @author süleyman
 */
@Document
public class User extends Model{

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @Pattern(regexp = "0[0-9]{10}")
    @Min(11)
    private String phone;

    public User(String name, String lastname, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public User(String id, String name, String lastname, String phone) {
        super.setId(id);
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
