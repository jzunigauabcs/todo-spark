/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.routes;

import com.google.gson.Gson;
import static com.todo.config.ResourcesName.USERS;
import static com.todo.config.ResourcesName.API;
import com.todo.controllers.UserController;
import static spark.Spark.*;

/**
 *
 * @author jzuniga
 */
public class UserRoute {

    public UserRoute(UserController userController) {
        Gson gson = new Gson();
        path(API, () -> {
            post(USERS, (req, res) -> userController.store(req, res), gson::toJson);
        });
    }
}
