/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

import com.google.gson.Gson;
import com.todo.dao.TaskDao;
import com.todo.models.Task;
import com.todo.services.TaskService;
import java.util.List;
import spark.Request;
import spark.Response;

/**
 *
 * @author jzuniga
 */
public class TaskController {
    public List<Task> index(Request req, Response res) {
        res.type("application/json");
        TaskService taskService = new TaskService(new TaskDao());
        //int page = req.queryParams("page") != null ?  Integer.parseInt(req.queryParams("page")) : 0;
        if(req.queryParams("search") != null) 
            return taskService.find(req.queryParams("search"));
        
        return taskService.getAll();
    }
    
    public Task show(Request req, Response res) {
        res.type("application/json");
        TaskService taskService = new TaskService(new TaskDao());
        int id = Integer.parseInt(req.params(":id"));
        return taskService.get(id);
    }
    
    public String store(Request req, Response res) {
       res.type("application/json");
       Task t  = new Gson().fromJson(req.body(), Task.class);
        TaskService taskService = new TaskService(new TaskDao());
       int result = taskService.save(t);
       return result == 1 ? "Registrado"  : "Ocurrió un error";
    }
}
