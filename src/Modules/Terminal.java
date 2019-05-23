
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Terminal extends Module{
    
    //List of usertypes, for which this module is available in the module selection menu
    private ArrayList<UserType.type> availables;

    public Terminal(PresentationInterface p) {
        super(p);
        //TODO remove 'UserType.type.USER' from 'availables' after testing
        availables = new ArrayList<>(Arrays.asList(UserType.type.SUPER_ADMIN, UserType.type.USER));
    }

    @Override
    public String getName() {
        return "Terminal";
    }
    
    @Override
    public ArrayList<UserType.type> getAvailableTypes() {
        return availables;
    }

    @Override
    public void showScene(Stage previousStage) {
        previousStage.hide();
        
        BorderPane bp = new BorderPane();
        
        //TODO open Terminal window
//        Runtime rt = Runtime.getRuntime();
//        try {
//            rt.exec("cmd.exe /c start command");
//        } catch (IOException ex) {
//            Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Label message = new Label("This module is not implemented");
        bp.setCenter(message);
        Button back = new Button("Back");
        back.setAlignment(Pos.BOTTOM_RIGHT);
        back.setPadding(new Insets(25, 25, 25, 25));
        
        Scene scene = new Scene(bp, 500, 400);        
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
}
