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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jzuniga
 */
public class UserDao {
    
    public User findByCredentials(String email, String password) {
        ConnectionDB db = new ConnectionDB();
        User u = null;
        try(Connection conn = db.getConnection()){
            String query = "SELECT * FROM users WHERE email=? AND password=SHA1(?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, email);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApPaterno(rs.getString("ap_p"));
                u.setApMaterno(rs.getString("ap_m"));
                u.setEmail(rs.getString("email"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public int save(User u) {
        ConnectionDB db = new ConnectionDB();
        int rs = 0;
        try(Connection conn = db.getConnection()) {
            String query = "INSERT INTO users(nombre, ap_p, ap_m, email, password) VALUES(?,?,?,?,SHA1(?))";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, u.getNombre());
            pstm.setString(2, u.getApPaterno());
            pstm.setString(3, u.getApMaterno());
            pstm.setString(4, u.getEmail());
            pstm.setString(5, u.getPassword());
            rs = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
