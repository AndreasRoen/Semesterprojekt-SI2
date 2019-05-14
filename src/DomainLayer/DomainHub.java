package DomainLayer;

import java.util.UUID;

public class DomainHub implements PresentationInterface {

    @Override
    public boolean validLogin(String username, String password) {
        if (isValid(username) && isValid(password)) {
            //TODO check with existing accounts in DATA layer
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid(String input) {
        char[] stringToCharArray = input.toCharArray();
        boolean test = true;
        for (char c : stringToCharArray) {
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                test = false;
            }
        }
        return test;
    }

    @Override
    public UUID getID(String username, String password) {
        //TODO load users id from DataBase
        return UUID.randomUUID();
    }

    @Override
    public UserType.type getType(UUID id) {
        //TODO return users type based on
        UserType.type type = UserType.type.USER;
        return type;
    }

}
