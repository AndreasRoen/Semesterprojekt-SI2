
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import Modules.Module;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class StaffOverviewGUI extends Module{
    
    //List of usertypes, for which this module is available
    final private UserType.type[] avaiables = {UserType.type.ADMIN};

    public StaffOverviewGUI(PresentationInterface p) {
        super(p);
    }

    @Override
    public void showScene(Stage previousStage) {
        //Sets up input for the 'overView' template in the 'Module' class and returns the generated scene
        ObservableList<String> wantedList = FXCollections.observableArrayList();
        //TODO add the wanted list to 'wantedList'
        wantedList.add("Erik");
        wantedList.add("Henry");
        
        Boolean[] buttons = {false, true, true};
        super.overViewTemplate(previousStage, wantedList, buttons, false);
    }

    @Override
    public UserType.type[] getAvailableTypes() {
        return avaiables;
    }
    
}
