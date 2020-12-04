/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.google.gson.Gson;
import static com.todo.config.StatusCode.*;
import com.todo.dao.UserDao;
import com.todo.exceptions.ValidLoginException;
import com.todo.helpers.DataResponse;
import com.todo.helpers.JwtTokenProvider;
import com.todo.models.JwtToken;
import com.todo.models.User;
import com.todo.services.AuthService;
import spark.Request;
import spark.Response;

/**
 *
 * @author jzuniga
 */
public class AuthController {
    
    public DataResponse login(Request req, Response res) {
       res.type("application/json");
       
       AuthService taskService = new AuthService(new UserDao());
       DataResponse response = new DataResponse();
        String msg = "";
       int status;
       Object data = null;
       try {
            String email = req.queryParams("email"); 
            String password = req.queryParams("password");
            User u = taskService.login(email, password);
            JwtToken token = new JwtToken();
            token.setToken(JwtTokenProvider.generateToken(u));
            status = OK;
            data = token;
            
       }catch(ValidLoginException e) {
           status = UNAUTHORIZED;
           msg = "Usuario o contraseña incorrectos";
       }
       
        res.status(status);
       return response.setStatus(status).write(msg, data);
    }
}
