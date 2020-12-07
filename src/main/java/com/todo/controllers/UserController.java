/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.google.gson.Gson;
import com.todo.config.StatusCode;
import static com.todo.config.StatusCode.BAD_REQUEST;
import static com.todo.config.StatusCode.CREATE;
import com.todo.dao.UserDao;
import com.todo.helpers.DataResponse;
import com.todo.models.User;
import com.todo.services.UserService;
import spark.Request;
import spark.Response;

/**
 *
 * @author jzuniga
 */
public class UserController {
    public DataResponse store(Request req, Response res)  {
        res.type("application/json");
        User u = new Gson().fromJson(req.body(), User.class);
        UserService userService = new UserService(new UserDao());
        int rs = userService.save(u);
        DataResponse response = new DataResponse();
        String msg;
        int status;
        if(rs == 1){
            msg = "Datos del usuario almacenados correctamente";
            status = CREATE;
        } else {
            msg = "Ocurrió un error al intentart almacenar los datos del usuario";
            status = BAD_REQUEST;
        }
        res.status(status);
        return response.setStatus(status).write(msg);
    }
}
