
package DomainLayer;

import Modules.Module;
import java.util.ArrayList;
import java.util.UUID;

public interface PresentationInterface {

    public boolean validLogin(String username, String password);
    
    public boolean isValid(String input);
    
    public void setID(String username, String password);
    
    public UUID getID();
    
    public UserType.type getType(UUID id);
    
    public UserType.type currentUserType();
    
    public ArrayList<Module> getAvaiableModules();
}
