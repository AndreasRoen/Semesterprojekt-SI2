/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.sql.Statement;


/**
 *
 * @author maltebukrinski
 */
public interface DBController
{
    boolean addResident(Statement statement,int Person_ID, String Kommune,String bosted,  String SocialPortalenTs); 
    boolean addUserInformation(Statement statement,int Person_ID, String password, String Kommune1,String bosted1, int role_ID, String SocialPortalenTs); 
    boolean addStaff(Statement statement,int Person_ID, String kommune,String bosted, int role_ID, String SocialPortalenTs);
    boolean checkPassword(Statement statement, String salt, String password, String givenPassword, String givenUsername);
    boolean CreateDatabase(Statement statement);
    boolean CreateTable(Statement statement); 
    
    String getId(Statement statement);
    String[] getDiary(Statement statement);
    String getResidents(Statement statement);
    
    String[] getUsers(Statement statement);  //From residents 
    
    
    
    
    
}
