/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.controllers;

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
        return taskService.getAll();
    }
    
    public Task show(Request req, Response res) {
        res.type("application/json");
        TaskService taskService = new TaskService(new TaskDao());
        int id = Integer.parseInt(req.params(":id"));
        return taskService.get(id);
    }
}
