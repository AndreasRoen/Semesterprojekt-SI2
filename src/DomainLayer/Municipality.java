
package DomainLayer;

import java.util.ArrayList;
import java.util.UUID;

public class Municipality {
    private String name;
    private UUID id;
    
    public Municipality(String name){
        this.name = name;
        this.id = UUID.randomUUID();
    }
    
    private ArrayList<Institution> institutions = new ArrayList<>();
    
    public String getName(){
        return name;
    }
    
    public UUID getId(){
        return id;
    }
    
    public Institution getInstitution(UUID id){
        for (Institution i : institutions){
            if(i.getId().equals(id)){
                return i;
            }
        }
        return null;
    }
}
