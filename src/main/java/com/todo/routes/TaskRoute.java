/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.routes;

import com.google.gson.Gson;
import com.todo.controllers.TaskController;
import spark.Spark;
import static spark.Spark.*;
import static com.todo.config.ResourcesName.TASKS;
/**
 *
 * @author jzuniga
 */
public class TaskRoute {
    private TaskController taskController;
    
    public TaskRoute(TaskController taskController) {
        Gson gson = new Gson();
        String resource = "";
        get(TASKS, (req, res) -> taskController.index(req, res), gson::toJson);
        get(TASKS + "/:id", (req, res) -> taskController.show(req, res), gson::toJson);
        post(TASKS, (req, res) -> taskController.store(req, res), gson::toJson);
        put(TASKS + "/:id", (req, res) -> taskController.update(req, res), gson::toJson);
        delete(TASKS + "/:id",  (req, res) -> taskController.delete(req, res), gson::toJson);
    }
    
}
