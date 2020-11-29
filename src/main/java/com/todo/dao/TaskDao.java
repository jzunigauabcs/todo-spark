/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.dao;

import com.todo.db.ConnectionDB;
import com.todo.models.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jzuniga
 */
public class TaskDao {
    
    public List<Task> getAll() {
        ArrayList <Task> tasks = new ArrayList<Task>();
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM tasks";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTask(rs.getString("task"));
                t.setStatus(rs.getString("status"));
                t.setUserId(rs.getInt("user_id"));
                tasks.add(t);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return tasks;
    }
    
    public Task get(int id) {
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        Task t = null;
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM tasks WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                t = new Task();
                t.setId(rs.getInt("id"));
                t.setTask(rs.getString("task"));
                t.setStatus(rs.getString("status"));
                t.setUserId(rs.getInt("user_id"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return t;
    }
    
    public List<Task> find(String search) {
        ArrayList <Task> tasks = new ArrayList<Task>();
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM tasks WHERE task LIKE ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, "%"+search+"%");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTask(rs.getString("task"));
                t.setStatus(rs.getString("status"));
                t.setUserId(rs.getInt("user_id"));
                tasks.add(t);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return tasks;
    } 
    
    public int save(Task t) {
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        int rs = 0;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO tasks(task) VALUE(?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, t.getTask());
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return rs;
    }
    
    public int update(Task t, int id) {
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        int rs = 0;
        try {
            conn = db.getConnection();
            String query = "UPDATE tasks SET status=? WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, t.getStatus());
            pstm.setInt(2, id);
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return rs;
    } 
    
    public int delete(int id) {
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        int rs = 0;
        try {
            conn = db.getConnection();
            String query = "DELETE FROM tasks WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return rs;
    } 
}
