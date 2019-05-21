/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;


/**
 *
 * @author maltebukrinski
 */
public interface DBController
{
    boolean addResident(); 
    boolean addUserInformation(); 
    boolean addStaff();
    boolean checkPassword();
    boolean CreateDatabase();
    
    String getId();
    String[] getDiary();
    String getResidents();
    
    String[] getUsers();  //From residents 
    
    
    
    
    
}
