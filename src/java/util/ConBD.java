/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jivago
 */
public class ConBD {
    
    
    public static Connection getConnection() {
        
        try {
            
          Class.forName("com.mysql.jdbc.Driver");
          Connection con = DriverManager
                  .getConnection("jdbc:mysql://127.0.0.1:3306/pokemongo","root","root");
            
          return con;
          
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não "
                    + "encontrada: "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
        return null;
        
    }
    
    
}
