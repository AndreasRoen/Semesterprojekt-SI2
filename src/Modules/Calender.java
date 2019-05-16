
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.stage.Stage;

public class Calender extends Module{
    
    //List of usertypes, for which this module is available in the module selection menu
    private ArrayList<UserType.type> availables;

    public Calender(PresentationInterface p) {
        super(p);
        availables = new ArrayList<>(Arrays.asList(UserType.type.USER));
    }

    @Override
    public String getName() {
        return "Calender";
    }

    @Override
    public void showScene(Stage previousStage) {
        //TODO build scene for module (Not being done in this project)
    }

    @Override
    public ArrayList<UserType.type> getAvailableTypes() {
        return availables;
    }
    
}
