
package DomainLayer;

import java.util.UUID;

public interface PresentationInterface {
    
    public boolean validLogin(String username, String password);
    
    public boolean isValid(String input);
    
    public UUID getID(String username, String password);
    
    public UserType.type getType(UUID id);
}
