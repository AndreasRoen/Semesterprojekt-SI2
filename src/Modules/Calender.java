package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Calender extends Module {

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
        previousStage.hide();
        //TODO build scene for module (Not being done in this project)
        BorderPane bp = new BorderPane();
        Label message = new Label("This module is not implemented");
        bp.setCenter(message);
        Button back = new Button("Back");
        back.setAlignment(Pos.BOTTOM_RIGHT);
        back.setPadding(new Insets(25, 25, 25, 25));
        
        Scene scene = new Scene(bp, 400, 300);        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        bp.setBottom(back);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
                previousStage.show();
            }
        });
    }
    
    @Override
    public ArrayList<UserType.type> getAvailableTypes() {
        return availables;
    }
    
}
