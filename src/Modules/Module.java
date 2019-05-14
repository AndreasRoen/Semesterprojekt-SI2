
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//NOTE ALL modules must inherit from the 'Module' class
public abstract class Module {
    
    protected PresentationInterface pI;
    
    public Module(PresentationInterface p){
        this.pI = p;
    }
    
    //Returns the 'Scene' for the module
    public abstract void showScene(Stage previousStage);
    
    //Returns an array of usertypes, for which this module is available
    public abstract UserType.type[] getAvailableTypes();
    
    //Returns the template overview scene, takes the wanted list and buttons as argument
    /*The first argument is the previous stage, this allows the method to return to the previous scene once the 'back' button is pressed.
     *The second argument is the list that should be shown in the scene.
     *The third argument is an boolean array that says which buttons should be added.
     *The fourth argument is a boolean that says wether there should be a textarea*/
    public void overViewTemplate(Stage previousStage, ObservableList<String> wantedList, Boolean[] buttons, boolean textArea){
        //TODO add textarea somewhere if 'textArea' is true
        //Sets up overview scene
        GridPane grid = new GridPane();
        ListView listView = new ListView();
        grid.add(listView, 0, 0);
        listView.setItems(wantedList);
        grid.getColumnConstraints().add(new ColumnConstraints(500));
        grid.getRowConstraints().add(new RowConstraints(700));
        Button back = new Button("back");
        grid.add(back, 1, 1);
        VBox vbox = new VBox();
        vbox.setPrefWidth(80.0);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(30.0);
        vbox.setPadding(new Insets(100, 10, 10, 10));
        
        //decides which buttons to add based on boolean array
        Button select = new Button("Select");
        Button add = new Button("Add");
        Button remove = new Button("Remove");
        if(buttons[0]){
            vbox.getChildren().add(select);
        }
        if(buttons[1]){
            vbox.getChildren().add(add);
        }
        if(buttons[2]){
            vbox.getChildren().add(remove);
        }
        grid.add(vbox, 1, 0); 
        
        Scene scene = new Scene(grid, 600, 780); 
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        //Adds functionality to buttons
        select.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO open diary overview (as select should only be avaiable to caregivers
                
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO save changes permanently
                listView.getItems().add(listView.getSelectionModel().getSelectedIndices());
            }
        });
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO save changes permanently
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO save changes permanently
                stage.close();
                previousStage.show();
            }
        }); 
    }
}
