/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todo.dao;

import com.todo.db.ConnectionDB;
import com.todo.models.User;
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
public class UserDao {
    
    public int save(User u) {
        ConnectionDB db = new ConnectionDB();
        Connection conn = null;
        int rs = 0;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO users(nombre, ap_p, ap_m, email, password) VALUE(?, ?, ?, ?, sha1(?))";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, u.getNombre());
            pstm.setString(2, u.getApP());
            pstm.setString(3, u.getApM());
            pstm.setString(4, u.getEmail());
            pstm.setString(5, u.getPassword());
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
            String query = "DELETE FROM users WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.close();
        }
        return rs;
    } 
}
