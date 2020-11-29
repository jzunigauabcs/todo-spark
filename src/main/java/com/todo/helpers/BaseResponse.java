/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.helpers;

/**
 *
 * @author jzuniga
 */
public abstract class BaseResponse {
    protected int status;
    protected String msg;
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public abstract void write(String msg);
}
