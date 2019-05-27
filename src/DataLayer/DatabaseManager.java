/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DomainLayer.Resident;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * 
 */
public class DatabaseManager{
  
//    private final String url = "jdbc:postgresql://localhost:5432/postgres"; // LOcal DB
//    private final String user = "postgres"; // USername
//    private final String password = "ASDqwe123"; // Password
    private final String url = "postgres://aksnqosj:5HXSoTmcZwE-GKiCKdqHYzyxEFNtSaTz@dumbo.db.elephantsql.com:5432/aksnqosj";
    private final String user = "aksnqosj";
    private final String password = "5HXSoTmcZwE-GKiCKdqHYzyxEFNtSaTz";
    

    public Connection connect() throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        DatabaseManager main = new DatabaseManager();
        main.findUserByLogin("Malte","Bukrinski");
        SimpleStringProperty test  = main.findUserByID(1); 
        System.out.println("Hopefully this works " + test );
    }

    public int getUserCount()
    {
        String SQL = "SELECT count(*) FROM bruger";
        int count = 0;

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL))
        {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    private void displayUser(ResultSet rs) throws SQLException
    {
        while (rs.next())
        {
            System.out.println(rs.getString("name") + "\t"
                    + rs.getString("lastname"));

        }
    }

    public SimpleStringProperty findUserByID(int userID)
    {
        SimpleStringProperty output = new SimpleStringProperty(); 
        String SQL = "SELECT person_id,name,lastname "
                + "FROM Bruger "
                + "WHERE person_id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL))
        {

            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            displayUser(rs);
            while(rs.next()){
                output.set(rs.getString("name" ) + "\t" + rs.getString("lastname"));
                //output = rs.getString("name" ) + "\t" + rs.getString("lastname"); 
                 return output; 
            }
           
            
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
             
        }
        return output;
    }

    public String findUserByName(String name)
    {
        String SQL = "SELECT person_id,name,lastname "
                + "FROM Bruger "
                + "WHERE name = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL))
        {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            displayUser(rs);
            return rs.getCursorName(); 
        } catch (SQLException ex)
        {
            return ex.getMessage();
        }
    }

    public void findUserByResidence(int residence)
    {
        String SQL = "SELECT person_id,name,lastname "
                + "FROM Bruger "
                + "WHERE Residence = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL))
        {

            pstmt.setInt(1, residence);
            ResultSet rs = pstmt.executeQuery();
            displayUser(rs);
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void findUserByLogin(String name, String lastname)
    {
        String SQL = "SELECT name,lastname "
                + "FROM Bruger "
                + "WHERE name = ?"
                + "and lastname = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL))
        {

            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            ResultSet rs = pstmt.executeQuery();
            displayUser(rs);
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
      public void findUserByCommune(String commune)
    {
        String SQL = "SELECT name,lastname, username "
                + "FROM Bruger "
                + "WHERE kommune = ?"
                ;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL))
        {

            pstmt.setString(1, commune);
            ResultSet rs = pstmt.executeQuery();
            displayUser(rs);
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
      public void CreateUser(String username, String password){
          String SQL = "Insert into Login_info(username, pass) "
                + "FROM Bruger "
                + "WHERE kommune = ?"
                ;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL))
        {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            displayUser(rs);
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
          
      }
      
      public boolean validatePassword(String Username, String password){
          
          
          return true; 
      }
        
      
      
      
      
      
      
      
      public void createDatabase()
    {
        String SQL = "CREATE DATABASE \"Socialportalen\"\n" +
        "    WITH \n" +
        "    OWNER = postgres\n" +
        "    ENCODING = 'UTF8'\n" +
        "    LC_COLLATE = 'Danish_Denmark.1252'\n" +
        "    LC_CTYPE = 'Danish_Denmark.1252'\n" +
        "    TABLESPACE = pg_default\n" +
        "    CONNECTION LIMIT = -1;\n" +
        "\n" +
        "GRANT ALL ON DATABASE \"Socialportalen\" TO postgres;\n" +
        "\n" +
        "GRANT TEMPORARY, CONNECT ON DATABASE \"Socialportalen\" TO PUBLIC;"
                
      + "Create Table if not exists Users(\n" +
        "    Person_id int not null PRIMARY KEY,\n" +
        "    Name varchar(130) not null ,\n" +
        "    lastName varchar(200) not null,\n" +
        "    Role_id int not null,\n" +
        "    Socialportalen_Ts char(26)	\n" +
        "); \n" +
        "INSERT INTO Users(\n" +
"	person_id, name, lastname, role_id, socialportalen_ts)\n" +
"	VALUES \n" +
"	(1, 'Admin','', 3, '01-01-2000'),\n" +
"	(2, 'Malte', 'Bukrinski', 3, '06-01-1995'),\n" +
"	(3, 'Per', 'Frederiksen', 1, '13-03-1993'),\n" +
"	(4, 'Peter', 'Andersen', 2, '25-06-1992'),\n" +
"	(5, 'Anders', 'Jordan', 2, '13-05-1992'),\n" +
"	(6, 'Preben', 'Ravi', 3, '24-04-1992'),\n" +
"	(7, 'Mads', 'Christensen', 1, '06-03-1992'),\n" +
"	(8, 'Mikkel', 'Petersen', 2, '12-12-1992'),\n" +
"	(9, 'Admir', 'Pedersen', 1, '11-11-1992');\n" +  
                
                
        "	\n" +
                
                
        "\n" +
        "CREATE TABLE if not exists staff(\n" +
        "    Person_id int not null PRIMARY KEY,\n" +
        "    Kommune1 varchar(200) not null,\n" +
        "    Kommune2 varchar(200),\n" +
        "    Kommune3 varchar(200),\n" +
        "    Bosted1 Varchar(200) not null,\n" +
        "    Bosted2 Varchar(200),\n" +
        "    Bosted3 Varchar(200),\n" +
        "    Role_id int not null,\n" +
        "    Socialportalen_Ts char(26)\n" +
        "\n" +
        ");\n" +
                
                
                
        "\n" +
        "CREATE TABLE if not exists residents(\n" +
        "    Person_id int not null PRIMARY KEY,\n" +
        "    Kommune1 varchar(200) not null,\n" +
        "    Bosted1 Varchar(200) not null,\n" +
        "    Role_id  int not null,\n" +
        "    Socialportalen_Ts char(26)\n" +
        "\n" +
        ");" +
                
                
        "CREATE TABLE if not exists Login_info \n " +
        "    Person_id int not null PRIMARY KEY , \n" +
        "    Username varchar(200) not null,\\n\" +\n" +
        "    Pass Varchar(200) not null,\\n\" +\n" +
        " +\n" +
        ");" 
                
                
                
        ;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL))
        {
            ResultSet rs = pstmt.executeQuery();
           
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }
 
        


    

}

    
    
    
