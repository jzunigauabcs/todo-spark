/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.helpers;

import static com.todo.config.StatusCode.*;
/**
 *
 * @author jzuniga
 */
public class DataResponse extends BaseResponse{
    
    private Object data;
    
    public DataResponse() {
        this.status = OK;    
    }

    @Override
    public void write(String msg) {
        this.msg = msg;
    }
    
    public void write(Object data) {
        this.data = data;
    }
    
     public void write(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
    
}
