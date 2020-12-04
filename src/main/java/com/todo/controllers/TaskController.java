/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.google.gson.Gson;
import com.todo.dao.TaskDao;
import com.todo.helpers.DataResponse;
import com.todo.models.Task;
import com.todo.services.TaskService;
import java.util.ArrayList;
import java.util.List;
import spark.Request;
import spark.Response;
import static com.todo.config.StatusCode.*;
/**
 *
 * @author jzuniga
 */
public class TaskController {
    public DataResponse index(Request req, Response res) {
        res.type("application/json");
        TaskService taskService = new TaskService(new TaskDao());
        DataResponse response = new DataResponse();
        List<Task> tasks = null;
        int userId = req.attribute("userId");
        if(req.queryParams("search") != null) 
            tasks = taskService.find(req.queryParams("search"));
        else
            tasks = taskService.getAll(userId);
         res.status(OK);
        return response.setStatus(OK).write("Operación exitosa",tasks);
        
    }
    
    public DataResponse show(Request req, Response res) {
        res.type("application/json");
        TaskService taskService = new TaskService(new TaskDao());
        int id = Integer.parseInt(req.params(":id"));
        DataResponse response = new DataResponse();
        Task t = taskService.get(id);
        
        int status = OK;
        if(t == null){
            status = NO_CONTENT;
        }
        
        res.status(status);
        
        return response.setStatus(status).write(t);
    }
    
    public DataResponse store(Request req, Response res) {
       res.type("application/json");
       Task t  = new Gson().fromJson(req.body(), Task.class);
        TaskService taskService = new TaskService(new TaskDao());
       int result = taskService.save(t);
       DataResponse response = new DataResponse();
       String msg;
       int status;
       if(result == 1) {
           msg = "Datos almacenados correctamente";
           status = CREATE;
       } else { 
           msg = "Ocurrió un error";
           status = BAD_REQUEST;
        }
        res.status(status);
       return response.setStatus(status).write(msg, new ArrayList());
    }
    
    public DataResponse update (Request req, Response res) {
        res.type("application/json");
        TaskService taskService = new TaskService(new TaskDao());
        int id = Integer.parseInt(req.params(":id"));
        Task t = new Gson().fromJson(req.body(), Task.class);
        int rs = taskService.update(t, id);
        DataResponse response = new DataResponse();
       String msg;
       int status;
       switch(rs) { 
           case -1:
               msg = "El formato de los datos es incorrecto, favor de verificarlos";
               status = BAD_REQUEST;
               break;
           case 0:
               msg = "La tarea no existe";
               status = NO_CONTENT;
               break;
           case 1:
                msg = "La tarea actualizada correctamente";
               status = OK;
               break;           
           default:
               msg = "Ocurrió un error al intentar actualizar la tarea";
                status = BAD_REQUEST;
       }
       
        res.status(status);
       return response.setStatus(status).write(msg);
    }
    
    public DataResponse delete(Request req, Response res) {
        res.type("application/json");
       
        TaskService taskService = new TaskService(new TaskDao());
        DataResponse response = new DataResponse();
        
        try {
            int status;
            int id;
            id = Integer.parseInt(req.params(":id"));
            int rs = taskService.delete(id);
            String msg;
            switch(rs) { 
                case -1:
                    msg = "El formato de los datos es incorrecto, favor de verificarlos";
                    status = BAD_REQUEST;
                    break;
                case 0:
                    msg = "La tarea no existe";
                    status = NO_CONTENT;
                    break;
                case 1:
                     msg = "La tarea eliminada correctamente";
                    status = OK;
                    break;           
                default:
                    msg = "Ocurrió un error al intentar actualizar la tarea";
                     status = BAD_REQUEST;
            }
             res.status(status);
            return response.setStatus(status).write(msg);
        } catch(NumberFormatException ex) {
            res.status(BAD_REQUEST);
            return response.setStatus(BAD_REQUEST).write("El formato de los datos es incorrecto");
        }
        
    }
}
