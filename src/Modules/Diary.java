
package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Diary extends Module{
    
    private UUID diaryOwner;
    
    //List of usertypes, for which this module is available
    private ArrayList<UserType.type> availables;
    
    public Diary(PresentationInterface p) {
        super(p);
        availables = new ArrayList<>();
    }
    
    public void setOwner(UUID id){
        diaryOwner = id;
    }
    
    public UUID getOwner(){
        return diaryOwner;
    }
    
    @Override
    public String getName(){
        return "Diary";
    }
    
    @Override
    public void showScene(Stage previousStage) {
        previousStage.hide();
        //Sets up the scene
        GridPane grid = new GridPane();
        ListView listView = new ListView();
        //TODO '.setItems' in 'listView' based on existing diaries for 'name' based on their 'id' (UUID)
        grid.add(listView, 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(500));
        grid.getRowConstraints().add(new RowConstraints(600));
        TextArea input = new TextArea();
        input.setMinSize(500, 150);
        input.setWrapText(true);
        input.setPromptText("Write here..");
        VBox vbox = new VBox();
        vbox.setPrefWidth(80.0);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(30.0);
        vbox.setPadding(new Insets(100, 10, 10, 10));
        grid.add(vbox, 1, 0);
        Button add = new Button("Add");
        add.setMinWidth(vbox.getPrefWidth());
        //NOTE this 'remove' should ONLY be availble for testing
        Button remove = new Button("Remove");
        remove.setMinWidth(vbox.getPrefWidth());
        Button back = new Button("Back");
        grid.add(back, 1, 1);

        //Date getter, delete if automatic timestamp is not needed in diary
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        grid.add(input, 0, 1);
        
        //TODO check if user type is resident or user
        if(pI.currentUserType() == UserType.type.USER){
            vbox.getChildren().add(add);
            vbox.getChildren().add(remove);
        }
        
        Scene scene = new Scene(grid, 600, 780);
        Stage diary = new Stage();
        diary.setScene(scene);
        diary.show();

        //Adds functinality to buttons and listView
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //TODO remove the Date info before putting it into the 'input' TextArea
                input.setText(newValue);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                diary.close();
                previousStage.show();
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO save additions permanently in another layer
                Date date = new Date();
                //TODO add users name after date but before input text.
                listView.getItems().add(dateFormat.format(date) + "\n" + input.getText());
            }
        });

        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
            }
        });
    }

    @Override
    public ArrayList<UserType.type> getAvailableTypes() {
        return availables;
    }
}
