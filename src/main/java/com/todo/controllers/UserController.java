/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.google.gson.Gson;
import com.todo.config.StatusCode;
import com.todo.dao.TaskDao;
import com.todo.helpers.BaseResponse;
import com.todo.helpers.DataResponse;
import com.todo.models.Task;
import com.todo.services.TaskService;
import spark.Request;
import spark.Response;

/**
 *
 * @author jzuniga
 */
public class UserController {
    
    public DataResponse store(Request req, Response res) {
       res.type("application/json");
       Task t  = new Gson().fromJson(req.body(), Task.class);
       TaskService taskService = new TaskService(new TaskDao());
       int result = taskService.save(t);
       
       DataResponse response = new DataResponse();
       
       if(result == 1) {
           response.write("Registrado");
           response.setStatus(StatusCode.CREATE);
       } else {
            response.write("Ocurrió un error");
            response.setStatus(StatusCode.BAD_REQUEST);
       }
       return response;
    }
    
    public DataResponse update(Request req, Response res) {
        res.type("application/json");
       Task t  = new Gson().fromJson(req.body(), Task.class);
       TaskService taskService = new TaskService(new TaskDao());
       int id = Integer.parseInt(req.params(":id"));
       int result = taskService.update(t, id);
       DataResponse response = new DataResponse();
        
       if(result == 1) {
           response.write("Datos actualizados correctamente");
           response.setStatus(StatusCode.OK);
       } else {
            response.write("Ocurrió un error");
            response.setStatus(StatusCode.BAD_REQUEST);
       }
       return response;
    }
    
    public DataResponse delete(Request req, Response res) {
        res.type("application/json");
       TaskService taskService = new TaskService(new TaskDao());
       int id = Integer.parseInt(req.params(":id"));
       int result = taskService.delete(id);
       DataResponse response = new DataResponse();
       
       if(result == 1) {
           response.write("Datos eliminados correctamente");
           response.setStatus(StatusCode.OK);
       } else {
            response.write("Ocurrió un error");
            response.setStatus(StatusCode.BAD_REQUEST);
       }
       return response;
    }
}
