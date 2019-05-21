/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DomainLayer.Resident;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * 
 */
public class DatabaseManager implements DBController{
    
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

    try{
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","ASDqwe123"); // Username and password for your Database :) 
        connection.setAutoCommit(true); // Set false if Auto commit to DB is not wanted (It is guys ;P ) 

        CreateDatabase();
        
        
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
//    private void addResident(Statement statement,int Person_ID, String Kommune,String bosted,  String SocialPortalenTs)throws SQLException{
//            statement.execute("INSERT INTO " + TABLE_NAME +
//                " ("+COLUMN_PERSON_ID +", "
//                 + "" + COLUMN_KOMMUNE + ", "
//                 + "" + COLUMN_BOSTED + " , "
//                 + "" + COLUMN_ROLEID + " , "        
//                 + "" + COLUMN_TS +
//                 ") " +
//                "VALUES(" + Person_ID + ", '" + Kommune + "', '" + bosted + "',"  + SocialPortalenTs + "')");      
//        
//    }

    
    
    // MUlig løsning på en streng der kan overfører SQL til en string. 
    private void checkPassword(Statement statement, String salt, String password, String givenPassword, String givenUsername) throws SQLException{
        

        String SQL = "SELECT * FROM USERS" +
                              "Inner Join Bruger_informationer WHERE  Bruger "
                             + "Password = GivenPassword AND Username = givenUsername;";
        ResultSet rs = statement.executeQuery(SQL);
        String test =  rs.toString();
        EncryptPassword.verifyUserPassword(givenPassword,test, salt); 

        
    }
    
    

    @Override
    public boolean addResident(Statement statement,int Person_ID, String Kommune,String bosted,  String SocialPortalenTs)
    {
        try
        {
            
        return statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_KOMMUNE + ", "
                 + "" + COLUMN_BOSTED + " , "
                 + "" + COLUMN_ROLEID + " , "        
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + Kommune + "', '" + bosted + "',"  + SocialPortalenTs + "')");  
    
        } catch (SQLException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            return false; 
        }
        
    }

    @Override
    public boolean addUserInformation(Statement statement,int Person_ID, String password, String Kommune1,String bosted1, int role_ID, String SocialPortalenTs)
    {
  try
        {
            
        return statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_PASSWORD + ", "
                 + "" + COLUMN_KOMMUNE + ", "
                 + "" + COLUMN_BOSTED + " , "
                 + "" + COLUMN_ROLEID + " , "        
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + password + "', '" + Kommune1 + "',, '" + bosted1 + "'" + role_ID + ",'" + SocialPortalenTs + "')");        
         
    
        } catch (SQLException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            return false; 
        }    }

    @Override
    public boolean addStaff()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkPassword()
    {
  try
        {
            
        return statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_KOMMUNE + ", "
                 + "" + COLUMN_BOSTED + " , "
                 + "" + COLUMN_ROLEID + " , "        
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + Kommune + "', '" + bosted + "',"  + SocialPortalenTs + "')");  
    
        } catch (SQLException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            return false; 
        }    }

    @Override
    public String[] getDiary()
    {
  try
        {
            
        return statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_KOMMUNE + ", "
                 + "" + COLUMN_BOSTED + " , "
                 + "" + COLUMN_ROLEID + " , "        
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + Kommune + "', '" + bosted + "',"  + SocialPortalenTs + "')");  
    
        } catch (SQLException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            return false; 
        }    }

    @Override
    public String getResidents()
    {
  try
        {
            
        return statement.execute("INSERT INTO " + TABLE_NAME +
                " ("+COLUMN_PERSON_ID +", "
                 + "" + COLUMN_KOMMUNE + ", "
                 + "" + COLUMN_BOSTED + " , "
                 + "" + COLUMN_ROLEID + " , "        
                 + "" + COLUMN_TS +
                 ") " +
                "VALUES(" + Person_ID + ", '" + Kommune + "', '" + bosted + "',"  + SocialPortalenTs + "')");  
    
        } catch (SQLException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            return false; 
        }    }

    @Override
    public String[] getUsers(Statement statement) //TODO FIX
    {
  try
        {
            
        return statement.execute("");  
    
        } catch (SQLException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            return false; 
        }    }

    @Override
    public boolean CreateDatabase(Statement statement)
    {
        try
            {
                return statement.execute("CREATE DATABASE \"Socialportalen\" "+
                "    WITH " +
                "    OWNER = postgres" +
                "    ENCODING = 'UTF8'" +
                "    LC_COLLATE = 'Danish_Denmark.1252'" +
                "    LC_CTYPE = 'Danish_Denmark.1252'" +
                "    TABLESPACE = pg_default" +
                "    CONNECTION LIMIT = -1;" +
                "" +"GRANT ALL ON DATABASE \"Socialportalen\" TO postgres;" +
                "" +"GRANT TEMPORARY, CONNECT ON DATABASE \"Socialportalen\" TO PUBLIC;");

        } catch (SQLException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            return false; 
        }    
    }

    @Override
    public String getId()
    {
  try
        {
            
        return "kage";  
    
        } catch (SQLException e)
        {
            return "Something went wrong: " + e.getMessage();
           
        }    }
}
    
    
    
    
