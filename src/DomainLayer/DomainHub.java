package DomainLayer;

import DataLayer.DBController;
import DataLayer.DatabaseManager;
import Modules.Calender;
import Modules.Diary;
import Modules.Module;
import Modules.ResidentManegement;
import Modules.ResidentSelection;
import Modules.StaffManegement;
import Modules.Terminal;
import java.util.ArrayList;
import java.util.UUID;

public class DomainHub implements PresentationInterface {
    
    private UserType.type currentUserType;
    
    private UUID userID;
    
    private DBController dbm;
    
    //Sets all modules for project
    //TODO automate process (Not being done in this project)
    StaffManegement sm = new StaffManegement(this);
    ResidentManegement rm = new ResidentManegement(this);
    ResidentSelection rs = new ResidentSelection(this);
    Diary d = new Diary(this);
    Calender c = new Calender(this);
    Terminal t = new Terminal(this);
    private Module[] modules = {sm, rm, rs, d, c, t};
    
    public DomainHub(){
//        dbm = new DatabaseManager();
    }
    

    @Override
    public boolean validLogin(String username, String password) {
        if (isValid(username) && isValid(password)) {
            //TODO check with existing accounts in DATA layer and return true if information exits there
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
    public void setID(String username, String password) {
        //TODO load users id from DataBase using 'username' and 'password'
        userID = UUID.randomUUID();
        currentUserType = getType(userID);
    }
    
    @Override
    public UUID getID(){
        return userID;
    }

    @Override
    public UserType.type getType(UUID id) {
        //TODO return users type based on id
        UserType.type type = UserType.type.USER;
        return type;
    }

    @Override
    public UserType.type currentUserType(){
        return currentUserType;
    }
    
    @Override
    public ArrayList<Module> getAvaiableModules(){
        ArrayList<Module> a = new ArrayList<>();
        for (Module m : modules){
            if (m.getAvailableTypes().contains(currentUserType)){
                a.add(m);
            }
        }
        return a;
    }
}
