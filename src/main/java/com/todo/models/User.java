/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.models;

/**
 *
 * @author jzuniga
 */
public class User {
    private int id;
    private String nombre;
    private String apP;
    private String apM;
    private String email;
    private String password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApP() {
        return apP;
    }

    public void setApP(String apP) {
        this.apP = apP;
    }

    public String getApM() {
        return apM;
    }

    public void setApM(String apM) {
        this.apM = apM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
