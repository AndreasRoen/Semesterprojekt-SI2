package DomainLayer;

import java.util.UUID;

//TODO (maybe) change name to user and make it the only class that is generated as users
abstract class Person {

    private UUID id;
    private String name;
    private UserType.type type;

    public Person(String name, UserType.type type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType.type getType() {
        return type;
    }
}
