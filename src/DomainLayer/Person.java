/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainLayer;

import java.util.UUID;

/**
 *
 * @author andre
 */
class Person {
    
    private UUID id;
    private String name;

    public Person() {
        this.id = UUID.randomUUID();
    }
    
    public UUID getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}
