
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    
    public abstract String getName();
    
    //Returns an array of usertypes, for which this module is available
    public abstract ArrayList<UserType.type> getAvailableTypes();
    
    //Returns the overview scene generated based on its arguments
    /*The first argument is the previous stage, this allows the method to return to the previous scene once the 'back' button is pressed.
     *The second argument is the list that should be shown in the scene.
     *The third argument is an boolean array that says which buttons should be added.
     *The fourth argument is a boolean that says wether there should be a textarea*/
    public void overViewTemplate(Stage previousStage, ObservableList<String> wantedList, Boolean[] buttons){
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
                listPromt();
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
    
    private void listPromt() {
        //Sets up popup window
        GridPane pop = new GridPane();
        pop.setPadding(new Insets(25, 25, 25, 25));
        pop.setVgap(15.0);
        pop.setHgap(10.0);
        Label nameLabel = new Label("Name");
        nameLabel.setAlignment(Pos.CENTER);
        pop.add(nameLabel, 0, 0);
        TextField name = new TextField();
        pop.add(name, 0, 1);
        Label status = new Label("");
        pop.add(status, 0, 2);
        HBox hbox = new HBox();
        hbox.setSpacing(20.0);
        Button confirm = new Button("Confirm");
        hbox.getChildren().add(confirm);
        Button cancel = new Button("Cancel");
        hbox.getChildren().add(cancel);
        pop.add(hbox, 1, 2);

        //Shows popup window
        Scene popupScene = new Scene(pop, 450, 200);
        Stage popup = new Stage();
        popup.setScene(popupScene);
        popup.show();

        //Adds functionality to buttons in popup window
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO save additions permanently in another layer
                
                popup.close();
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.close();
            }
        });
    }
}
