
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import Modules.Module;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class StaffManegement extends Module{
    
    //List of usertypes, for which this module is available
    private ArrayList<UserType.type> availables;

    public StaffManegement(PresentationInterface p) {        
        super(p);
        availables = new ArrayList<>(Arrays.asList(UserType.type.ADMIN));
    }
    
    @Override
    public String getName(){
        return "Staff Management";
    }

    @Override
    public void showScene(Stage previousStage) {
        previousStage.hide();
        //Sets up input for the 'overView' template in the 'Module' class and returns the generated scene
        ObservableList<String> wantedList = FXCollections.observableArrayList();
        //TODO add the wanted list to 'wantedList'
        wantedList.add("Erik");
        wantedList.add("Henry");
        
        Boolean[] buttons = {false, true, true};
        super.overViewTemplate(previousStage, wantedList, buttons);
    }

    @Override
    public ArrayList<UserType.type> getAvailableTypes() {
        return availables;
    }
    
}
