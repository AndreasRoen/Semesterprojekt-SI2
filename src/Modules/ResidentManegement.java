
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import Modules.Module;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ResidentManegement extends Module{
    
    //List of usertypes, for which this module is available
    private ArrayList<UserType.type> avaiables;

    public ResidentManegement(PresentationInterface p) {
        super(p);
        avaiables.add(UserType.type.ADMIN);
    }
    
    @Override
    public String getName(){
        return "Resident Manegement";
    }

    @Override
    public void showScene(Stage previousStage) {
        //Sets up input for the 'overView' template in the 'Module' class and returns the generated scene
        ObservableList<String> wantedList = FXCollections.observableArrayList();
        //TODO add the wanted list to 'wantedList'
        wantedList.add("Eva");
        wantedList.add("Lone");
        
        Boolean[] buttons = {false, true, true};
        super.overViewTemplate(previousStage, wantedList, buttons);
    }

    @Override
    public ArrayList<UserType.type> getAvailableTypes() {
        return avaiables;
    }
    
}
