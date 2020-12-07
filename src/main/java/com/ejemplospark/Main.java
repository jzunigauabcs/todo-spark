/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplospark;

import com.todo.controllers.AuthController;
import com.todo.controllers.TaskController;
import com.todo.controllers.UserController;
import com.todo.routes.AuthRoute;
import com.todo.routes.TaskRoute;
import com.todo.routes.UserRoute;
import static spark.Spark.port;

/**
 *
 * @author jzuniga
 */
public class Main {
    public static void main(String[] args) {
        port(5555); //Especifica el puerto por el cual corre la aplicación
        new TaskRoute(new TaskController());
        new UserRoute(new UserController());
        new AuthRoute(new AuthController());
    }
}
