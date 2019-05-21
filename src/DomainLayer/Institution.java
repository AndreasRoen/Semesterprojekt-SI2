/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainLayer;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author andre
 */
public class Institution {
    private String name;
    private String adresse;
    private UUID id;
    
    private ArrayList<Resident> residents = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    
    public Institution(String name, String adresse){
        this.name = name;
        this.adresse = adresse;
        this.id = UUID.randomUUID();
    }
    
    public String getName(){
        return name;
    }
    
    public String getAdresse(){
        return adresse;
    }
    
    public UUID getId(){
        return id;
    }
    
    public Resident getResident(UUID id){
        for (Resident r : residents){
            if(r.getId().equals(id)){
                return r;
            }
        }
        return null;
    }
    
    public Employee getEmployee(UUID id){
        for (Employee e : employees){
            if(e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }
}
