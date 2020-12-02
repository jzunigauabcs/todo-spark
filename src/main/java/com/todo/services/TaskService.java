/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.services;

import com.todo.dao.TaskDao;
import com.todo.models.Task;
import java.util.List;

/**
 *
 * @author jzuniga
 */
public class TaskService {
    
    private TaskDao dao;

    public TaskService(TaskDao dao) {
        this.dao = dao;
    }
    
    public List<Task> getAll() {
        return this.dao.getAll();
    }
    
    public Task get(int id) {
        return this.dao.get(id);
    }
    
    public List<Task> find(String search) {
        return this.dao.find(search);
    }
    
     public int save(Task t) {
        return this.dao.save(t);
    }
     
    public int update(Task t, int id) {
        return this.dao.update(t, id);
    }
    
    public int delete(int id) {
        return this.dao.delete(id);
    }
}
