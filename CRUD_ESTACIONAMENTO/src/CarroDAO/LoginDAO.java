/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarroDAO;


import CarroBean.LoginBean;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uirapuru
 */
public class LoginDAO {
    
   private Connection con;
    Long id;
    String usuario;
    String senha;

    
     public LoginDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
     public  boolean verifica(String usuario, String senha){
        this.con = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
    try {
        stmt =con.prepareStatement("select * from login where usuario = ? and senha = ?");
        stmt.setString(1, usuario);
        stmt.setString(2, senha);
        rs = stmt.executeQuery();
        
        if(rs.next()){
             check = true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
    
    }
        return check;
    }   
}
