
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import Modules.Module;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ResidentSelection extends Module{
    
    //List of usertypes, for which this module is available in the module selection menu
    private ArrayList<UserType.type> availables;

    public ResidentSelection(PresentationInterface p) {
        super(p);
        availables = new ArrayList<>(Arrays.asList(UserType.type.USER));
    }
    
    @Override
    public String getName(){
        return "Diary";
    }

    @Override
    public void showScene(Stage previousStage) {
        previousStage.hide();
        //Sets up input for the 'overView' template in the 'Module' class and returns the generated scene
        ObservableList<String> wantedList = FXCollections.observableArrayList();
        //TODO add the wanted list to 'wantedList'
        wantedList.add("Ravi");
        wantedList.add("Rikke");
        
        Boolean[] buttons = {true, false, false};
        super.overViewTemplate(previousStage, wantedList, buttons);
    }

    @Override
    public ArrayList<UserType.type> getAvailableTypes() {
        return availables;
    }
}
