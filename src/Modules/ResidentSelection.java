
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
    
    //List of usertypes, for which this module is available
    private ArrayList<UserType.type> avaiables;

    public ResidentSelection(PresentationInterface p) {
        super(p);
        avaiables = new ArrayList<>();
    }
    
    @Override
    public String getName(){
        return "Resident Selection";
    }

    @Override
    public void showScene(Stage previousStage) {
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
        return avaiables;
    }
}
