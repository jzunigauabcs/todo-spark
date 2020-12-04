/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.exceptions;

/**
 *
 * @author jzuniga
 */
public class NotFoundUserIdException extends Exception{
    public NotFoundUserIdException(String message) {
        super(message);
    }
}
