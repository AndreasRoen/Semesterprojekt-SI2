/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Malte
 */
public class ConnectToDb {
    
    public void connection(){
        
        try {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","ASDqwe123"); // Username and password for your Database :) 
        conn.setAutoCommit(true); // Set false if Auto commit to DB is not wanted (It is guys ;P ) 
        Statement statement = conn.createStatement();
        statement.execute("CREATE DATABASE IF NOT EXISTS SOCIALPORTALEN"); 
        
        statement.execute("SELECT * FROM DB");
        ResultSet result = statement.getResultSet();
        while (result.next())
        {
            System.out.println(result.getString("name"));  // A way to print out a statement            
        }
        
        
        statement.close();
        conn.close();
        } catch (SQLException e) {
        }
    }
    
}
