
package DomainLayer;

public class Admin extends Person{
    
    public Admin(String name) {
        super(name, UserType.type.ADMIN);
    }
}
