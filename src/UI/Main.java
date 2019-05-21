
package UI;

import DomainLayer.DomainHub;
import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
import Modules.Module;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private Stage stage;

    //TODO let 'currentUserType' in domain layer be used instead of 'type'
    private UserType.type type;

    //TODO remove? (then get wantedLists from pI interface)
    private ObservableList<String> residents;

    //TODO remove? (then get WantedList from pI interface)
    private ObservableList<String> staff;

    //TODO move to domain layer
    private UUID userId;

    private PresentationInterface pI;

    private ListView listView;

    private boolean toggleOn;
    
    Random r = new Random();

    @Override
    public void start(Stage primaryStage) {
        //Sets up interface
        pI = (PresentationInterface) new DomainHub();

        //Basic setup of the GUI
        this.stage = primaryStage;
        stage.setResizable(false);
        stage.setTitle("Socialportalen");
        residents = FXCollections.observableArrayList();
        staff = FXCollections.observableArrayList();

        //Tests listView with dummy data
        //TODO load real data from another layer (AFTER SUCCESFUL LOGIN)
        residents.add("Lone");
        residents.add("Paul");
        staff.add("Erik");
        login();
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        launch(args);
    }

    private void login() {
        //Sets up login screen
        GridPane loginGrid = new GridPane();
        loginGrid.add(new Label("Sign in"), 0, 0);
        loginGrid.setVgap(20.0);
        loginGrid.setAlignment(Pos.CENTER);
        TextField txUsername = new TextField();
        txUsername.setPromptText("Username");
        loginGrid.add(txUsername, 0, 1);
        TextField txPassword = new TextField();
        txPassword.setPromptText("Password");
        loginGrid.add(txPassword, 0, 2);
        Label status = new Label();
        status.setText("");
        loginGrid.add(status, 0, 3);
        Button signin = new Button();
        signin.setText("Login");
        loginGrid.add(signin, 0, 4);
        Scene scene = new Scene(loginGrid, 400, 350);
        stage.setScene(scene);
        stage.show();

        //Checks if login info is valid and loads users info if it is
        signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (pI.validLogin(txUsername.getText(), txPassword.getText())) {
                    pI.setID(txUsername.getText(), txPassword.getText());
                    moduleSelection();
                } else {
                    status.setText("Invalid Username / Password");
                }
            }
        });
    }

    private void moduleSelection() {
        //Sets up module selection scene
        BorderPane moduleGrid = new BorderPane();
        HBox modules = new HBox();
        modules.setAlignment(Pos.CENTER);
        modules.setPadding(new Insets(25, 25, 25, 25));
        modules.setSpacing(20.0);
        moduleGrid.setCenter(modules);
        
        //TODO make modues modular, so that modules are automaticly detected and added
        //TODO load every module in 'Modules' package and add them to HBox in scene as new 'Module'
        
        //Adds every module available for the usertype
        for (Module m : pI.getAvaiableModules()){
            Button b = new Button(m.getName());
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                m.showScene(stage);
                }
            });
            modules.getChildren().add(b);
        }
        
        Button back = new Button();
        back.setText("Log out");
        HBox backBox = new HBox();
        backBox.getChildren().add(back);
        backBox.setAlignment(Pos.CENTER_RIGHT);
        backBox.setPadding(new Insets(25, 25, 25, 25));
        moduleGrid.setBottom(backBox);

        Scene scene = new Scene(moduleGrid, 450, 300);
        stage.setScene(scene);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login();
            }
        });
    }
}
