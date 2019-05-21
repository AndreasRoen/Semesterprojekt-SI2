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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public class ConnectToDb {
    
    // ALL PARAMTERES HAS TO BE SET IN THE INTERFACE FOR THE USE OF ADMINS ONLY
    // TODO SET all tablenames to the correct naming from tables. 
    public static final String DB_NAME = "postgres";
    public static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/" + DB_NAME; 
    public static final String CONNECTION_PASSWORD = "ASDqwe123"; 
    public static final String CONNECTION_USERNAME = "postgres"; 
    public static final String TABLE_NAME = ""; 
    public static final String COLUMN_NAME = ""; 
    public static final int COLUMN_PERSON_ID = 0; 
    public static final String COLUMN_LASTNAME = ""; 
    public static final String COLUMN_ROLEID = ""; 
    public static final String COLUMN_KOMMUNE = ""; 
    public static final String COLUMN_TS = ""; 
    public static final String COLUMN_BOSTED = ""; 
    public static final String COLUMN_PASSWORD = ""; 
 
    private Connection connection; 
    
    
    public void connection(){

        
        try {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING,CONNECTION_USERNAME,CONNECTION_PASSWORD); // Username and password for your Database :) 
        conn.setAutoCommit(true); // Set false if Auto commit to DB is not wanted (It is guys ;P ) 
        Statement statement = conn.createStatement();

//    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","ASDqwe123"); // Username and password for your Database :) 
//        Statement statement = connection.createStatement();){        
//        statement.execute("CREATE DATABASE SOCIALPORTALEN"); 
    try{
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","ASDqwe123"); // Username and password for your Database :) 
        connection.setAutoCommit(true); // Set false if Auto commit to DB is not wanted (It is guys ;P ) 
        //Statement statement = connection.createStatement();

        statement.execute("CREATE DATABASE IF NOT EXISTS SOCIALPORTALEN"); 
        
        ResultSet result = statement.executeQuery("SELECT * from "+ TABLE_NAME); 
        
        
        while (result.next())
        {
            System.out.println(result.getString("name"));  // A way to print out a statement            
        }
        
        
        statement.close();

        conn.close();
        } catch (SQLException e) {
        }

        connection.close();
    }catch(SQLException e) {
        System.out.println("Somehing went wrong "+ e.getMessage());

    }
    
}

    
    
    private static void addUser(Statement statement,int Person_ID, String name, String lastName, int role_ID, String SocialPortalenTs) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_NAME + ", "
                 + "" + COLUMN_LASTNAME + ", "
                 + "" + COLUMN_ROLEID + " , "
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + name + "', '" + lastName + "'," + role_ID + ",'" + SocialPortalenTs + "')");
        
    }
    
    private void addUserInformation(Statement statement,int Person_ID, String password, String Kommune1,String bosted1, int role_ID, String SocialPortalenTs) throws SQLException{
            statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_PASSWORD + ", "
                 + "" + COLUMN_KOMMUNE + ", "
                 + "" + COLUMN_BOSTED + " , "
                 + "" + COLUMN_ROLEID + " , "        
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + password + "', '" + Kommune1 + "',, '" + bosted1 + "'" + role_ID + ",'" + SocialPortalenTs + "')");        
        
    }
    
    private void addStaff(Statement statement,int Person_ID, String kommune,String bosted, int role_ID, String SocialPortalenTs)throws SQLException{
            statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_KOMMUNE + ", "
                 + "" + COLUMN_BOSTED + " , "
                 + "" + COLUMN_ROLEID + " , "        
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + kommune + "', '" + bosted + "'," + role_ID + ",'" + SocialPortalenTs + "')");    
        
    }
    private void addResident(Statement statement,int Person_ID, String Kommune,String bosted,  String SocialPortalenTs)throws SQLException{
            statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_KOMMUNE + ", "
                 + "" + COLUMN_BOSTED + " , "
                 + "" + COLUMN_ROLEID + " , "        
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + Kommune + "', '" + bosted + "',"  + SocialPortalenTs + "')");      
        
    }

    private void checkPassword(Statement statement, String salt, String password, String givenPassword, String givenUsername) throws SQLException{
        

        String SQL = "SELECT * FROM USERS" +
                              "Inner Join Bruger_informationer WHERE  Bruger "
                             + "Password = GivenPassword AND Username = givenUsername;";
        ResultSet rs = statement.executeQuery(SQL);
        String test =  rs.toString();
        EncryptPassword.verifyUserPassword(givenPassword,test, salt); 

        
    }
}
    
    
    
    
