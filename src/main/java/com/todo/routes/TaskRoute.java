/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.routes;

import com.google.gson.Gson;
import com.todo.controllers.TaskController;
import static spark.Spark.*;
import static com.todo.config.ResourcesName.TASKS;
import static com.todo.config.ResourcesName.API;
import static com.todo.config.StatusCode.UNAUTHORIZED;
import com.todo.exceptions.NotFoundTokenException;
import com.todo.exceptions.NotFoundUserIdException;
import com.todo.helpers.DataResponse;
import com.todo.helpers.JwtTokenProvider;
/**
 *
 * @author jzuniga
 */
public class TaskRoute {
    public TaskRoute(TaskController taskController) {
        Gson gson = new Gson();
        path(API, () ->{
            before("/*", (req, res) -> {
                res.type("application/json");
                try {
                     String jwtToken = JwtTokenProvider.extractTokenfromRequest(req);
                     if(!JwtTokenProvider.validateToken(jwtToken)) {
                         DataResponse response = new DataResponse();
                         response.setStatus(UNAUTHORIZED).write("Token incorrecto");
                         halt(UNAUTHORIZED, new Gson().toJson(response));
                     } else{
                         req.attribute("userId", JwtTokenProvider.getUserId(jwtToken));
                     }
                } catch(NotFoundTokenException e) {
                    DataResponse response = new DataResponse();
                         response.setStatus(UNAUTHORIZED).write(e.getMessage());
                    halt(UNAUTHORIZED, new Gson().toJson(response)); 
                } catch(NotFoundUserIdException e) {
                    DataResponse response = new DataResponse();
                         response.setStatus(UNAUTHORIZED).write("Datos del token incorrecto");
                    halt(UNAUTHORIZED, new Gson().toJson(response)); 
                }
            });
            
            get(TASKS, (req, res) -> taskController.index(req, res), gson::toJson);
            get(TASKS + "/:id", (req, res) -> taskController.show(req, res), gson::toJson);
            post(TASKS, (req, res) -> taskController.store(req, res), gson::toJson);
            put(TASKS + "/:id", (req, res) -> taskController.update(req, res), gson::toJson);
            delete(TASKS + "/:id",  (req, res) -> taskController.delete(req, res), gson::toJson);
        });
        
    }
    
}