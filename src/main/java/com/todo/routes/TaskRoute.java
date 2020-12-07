/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.routes;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.google.gson.Gson;
import com.todo.controllers.TaskController;
import spark.Spark;
import static spark.Spark.*;
import static com.todo.config.ResourcesName.TASKS;
import static com.todo.config.ResourcesName.API;
import com.todo.config.StatusCode;
import com.todo.exceptions.NotFoundTokenException;
import com.todo.helpers.DataResponse;
import com.todo.helpers.JwtTokenProvider;
/**
 *
 * @author jzuniga
 */
public class TaskRoute {
    private TaskController taskController;
    
    public TaskRoute(TaskController taskController) {
        Gson gson = new Gson();
        path(API + TASKS, () -> {
            before("/*", (req, res) -> {
                res.type("application/json");
                 DataResponse response = new DataResponse();
                try {
                    String jwtToken = JwtTokenProvider.extractTokenFromRequest(req);
                    JwtTokenProvider.validateToken(jwtToken);
                    req.attribute("userId", JwtTokenProvider.getUserId(jwtToken));
                } catch(NotFoundTokenException e) {
                    response.setStatus(StatusCode.UNAUTHORIZED).write(e.getMessage());
                    halt(StatusCode.UNAUTHORIZED, new Gson().toJson(response));
                } catch(JWTVerificationException e) {
                    response.setStatus(StatusCode.UNAUTHORIZED).write(e.getMessage());
                    halt(StatusCode.UNAUTHORIZED, new Gson().toJson(response));
                }
            });
            get("/", (req, res) -> taskController.index(req, res), gson::toJson);
            get("/:id", (req, res) -> taskController.show(req, res), gson::toJson);
            post("/", (req, res) -> taskController.store(req, res), gson::toJson);
            put("/" + "/:id", (req, res) -> taskController.update(req, res), gson::toJson);
            delete("/:id",  (req, res) -> taskController.delete(req, res), gson::toJson);
        });
        
    }
    
}
