package UI;

import DataLayer.DatabaseManager;
import DomainLayer.DomainHub;
import DomainLayer.PresentationInterface;
import Modules.Module;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.*; 

public class Main extends Application {

    private Connection connection;
    
    DatabaseManager connect = new DatabaseManager() {}; 
    
    private Stage stage;

    private PresentationInterface pI;

    Random r = new Random();

    @Override
    public void start(Stage primaryStage) {
        //Sets up interface
        pI = (PresentationInterface) new DomainHub();

        //Basic setup of the GUI
        this.stage = primaryStage;
        stage.setResizable(false);
        stage.setTitle("Socialportalen");
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

//        Should be done automaticly
        //TODO delete when finish testing
        Button db = new Button("Create Database");
        loginGrid.add(db, 0, 5);

//        Checks if login info is valid and loads users info if it is
        signin.setOnAction( new EventHandler<ActionEvent>() {
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
        
                db.setOnAction(e ->
        {
            connect.createDatabase();
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
        for (Module m : pI.getAvaiableModules()) {
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
    
    
//     private void connectToDB() {
//         // Makes the program able to take other DBS etc. Need to make this work instead of a hard coded DB. 
//        // Get database information from the user input
////        String driver = cboDriver
////                .getSelectionModel().getSelectedItem();
////        String url = cboURL.getSelectionModel().getSelectedItem();
////        String username = tfUsername.getText().trim();
////        String password = pfPassword.getText().trim();
//
//        // Connection to the database
//        try {
////            Class.forName(driver);
//            connection = DriverManager.getConnection(
//                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "ASDqwe123");
//            System.out.println("Connected to DB ");
////            lblConnectionStatus.setText("Connected to " + url);
//
//        }
//        catch (java.lang.Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
