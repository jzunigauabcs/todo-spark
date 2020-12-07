/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.routes;

import com.google.gson.Gson;
import static com.todo.config.ResourcesName.API;
import static com.todo.config.ResourcesName.AUTH_LOGIN;
import com.todo.controllers.AuthController;
import static spark.Spark.path;
import static spark.Spark.post;
/**
 *
 * @author jzuniga
 */
public class AuthRoute {

    public AuthRoute(AuthController authController ) {
          Gson gson = new Gson();
            path(API, () -> {
                post(AUTH_LOGIN, (req, res) -> authController.login(req, res), gson::toJson);
            });
    }
}
