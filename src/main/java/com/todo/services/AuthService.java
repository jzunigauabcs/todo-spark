/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.services;

import com.todo.dao.UserDao;
import com.todo.exceptions.ValidLoginException;
import com.todo.models.User;

/**
 *
 * @author jzuniga
 */
public class AuthService {
    private UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }
            
    public User login(String email, String password) throws ValidLoginException {
        User u = this.userDao.findByCredentials(email, password);
        if(u == null) {
            throw new ValidLoginException("Usuario o contraseña incorrectos");
        } 
        return u;
    }
}
