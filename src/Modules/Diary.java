package Modules;

import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

public class Diary extends Module {

    private UUID diaryOwner;
    
    Random r = new Random();

    //List of usertypes, for which this module is available
    private ArrayList<UserType.type> availables;

    public Diary(PresentationInterface p) {
        super(p);
        availables = new ArrayList<>();
    }

    public void setOwner(UUID id) {
        diaryOwner = id;
    }

    public UUID getOwner() {
        return diaryOwner;
    }

    @Override
    public String getName() {
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
//        //NOTE this 'remove' should ONLY be availble for testing
//        Button remove = new Button("Remove");
//        remove.setMinWidth(vbox.getPrefWidth());
        Button back = new Button("Back");
        Button autoGen = new Button("AutoGen");
        Button stopGen = new Button("Stop Auto");
        grid.add(back, 1, 1);

        //Date getter, delete if automatic timestamp is not needed in diary
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        grid.add(input, 0, 1);

        //TODO check if user type is resident or user
        if (pI.currentUserType() == UserType.type.USER) {
            vbox.getChildren().add(add);
            vbox.getChildren().add(autoGen);
            vbox.getChildren().add(stopGen);
//            vbox.getChildren().add(remove);
        }

        Scene scene = new Scene(grid, 600, 780);
        Stage diary = new Stage();
        diary.setScene(scene);
        diary.show();

        //Adds functinality to buttons and listView
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String s = newValue.substring(newValue.indexOf('\n')+1);
                input.setText(s);
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
                //Please forgive this horrible line of code
                listView.getItems().add("--------------------------------------------------------------------------------");
                //TODO add users name instead of 'pI.getID' if possible, else keep the ID.
                listView.getItems().add(dateFormat.format(date) + "  by: " + pI.getID() + "\n" + input.getText());
            }
        });
        
        Timeline autoGenMessage = new Timeline(new KeyFrame(Duration.seconds(r.nextInt(8 - 2 + 1) + 2), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Date date = new Date();
                String[] messages = new String[12];
                messages[0] = "Borger har modtaget penicillin";
                messages[1] = "Borger har modtaget insulin";
                messages[2] = "Borger har modtaget paracetamol";
                messages[3] = "Borger har modtaget melatonin";
                messages[4] = "Borger har modtaget halcion";
                messages[5] = "Borgeren har været i bad";
                messages[6] = "Borgeren har gået en tur";
                messages[7] = "Borgeren har sovet middagslur";
                messages[8] = "Borgeren har spidst frokost";
                messages[9] = "Borgeren har været til lægen";
                messages[10] = "Borgeren har haft besøg af familien";
                messages[11] = "Borgeren har været sur hele morgenen";
                
                //TODO save additions permanently
                listView.getItems().add("--------------------------------------------------------------------------------");
                listView.getItems().add(dateFormat.format(date) + "  by: AutoGen" + "\n" + messages[r.nextInt(messages.length)]);
            }
        }));
        autoGenMessage.setCycleCount(Timeline.INDEFINITE);
        
        autoGen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                autoGenMessage.play();
            }
        });
        stopGen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                autoGenMessage.stop();
            }
        });

//        remove.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
//            }
//        });
    }

    @Override
    public ArrayList<UserType.type> getAvailableTypes() {
        return availables;
    }
}
