/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.services;

import com.todo.dao.UserDao;
import com.todo.models.User;
import com.todo.exceptions.ValidateLoginException;

/**
 *
 * @author jzuniga
 */
public class AuthService {
    private UserDao userDato;
    
    public AuthService(UserDao userDato) {
        this.userDato = userDato;
    }
    
    public User login(String email, String password) throws ValidateLoginException {
        User u = this.userDato.findByCredentials(email, password); 
        if(u == null) {
            throw new ValidateLoginException("Usuario o contraseña incorrectos");
        }
        return u;
    }
}
