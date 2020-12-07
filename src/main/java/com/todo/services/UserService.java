/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.services;

import com.todo.dao.UserDao;
import com.todo.models.User;

/**
 *
 * @author jzuniga
 */
public class UserService {
    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }
    
    public int save(User u) {
        return this.dao.save(u);
    }
}
