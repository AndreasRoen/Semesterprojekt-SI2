/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author andre
 */
public class Main extends Application {
    
    private Stage stage;
    
    private String type;
    
    private ObservableList<String> residents;
    
    private ObservableList<String> staff;
    
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 960, 720);
        stage.setScene(scene);
//        scene.getStylesheets().add(Main.class.getResource("Stylesheet.css").toExternalForm());
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Socialportalen");
        residents = FXCollections.observableArrayList();
        staff = FXCollections.observableArrayList();
        login();
    }
    
    public static void main(String[] args) {
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

        //Checks if login info is valid
        signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validLogin(txUsername.getText(), txPassword.getText())) {
                    //TODO load 'residents' / 'staff' from DATA layer
                    moduleSelection();
                } else {
                    status.setText("Invalid Username / Password");
                }
            }
        });
        
    }
    
    private boolean validLogin(String username, String password) {
        if (isValid(username) && isValid(password)) {
            //TODO check with existing accounts in DATA layer
            if ("user".equals(username) && "pass".equals(password)) {
                type = "Caregiver";
                return true;
            } else if ("admin".equals(username) && "pass".equals(password)) {
                type = "Admin";
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    private boolean isValid(String string) {
        char[] stringToCharArray = string.toCharArray();
        boolean test = true;
        for (char c : stringToCharArray) {
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                test = false;
            }
        }
        return test;
    }
    
    private void moduleSelection() {
        //Sets up module selection scene
        BorderPane moduleGrid = new BorderPane();
        HBox modules = new HBox();
        modules.setAlignment(Pos.CENTER);
        modules.setPadding(new Insets(25, 25, 25, 25));
        modules.setSpacing(20.0);
        moduleGrid.setCenter(modules);
        Button diary = new Button();
        diary.setText("Diary");        
        Button calender = new Button();
        calender.setText("Calender");        
        Button manageStaff = new Button();
        manageStaff.setText("Manage Staff");        
        Button manageResidents = new Button();
        manageResidents.setText("Manage Residents");        
        Button back = new Button();
        back.setText("Back");
        HBox backBox = new HBox();
        backBox.getChildren().add(back);
        backBox.setAlignment(Pos.CENTER_RIGHT);
        backBox.setPadding(new Insets(25, 25, 25, 25));
        moduleGrid.setBottom(backBox);

        //Enable buttons available to user type
        if ("Caregiver".equals(type)) {
            modules.getChildren().add(diary);
            modules.getChildren().add(calender);
        }
        if ("Admin".equals(type)) {
            modules.getChildren().add(manageStaff);
            modules.getChildren().add(manageResidents);
        }
        
        Scene scene = new Scene(moduleGrid, 450, 300);
        stage.setScene(scene);
        
        diary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                overview("Residents");
            }
        });
        calender.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO open calender
            }
        });
        manageStaff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                overview("Staff");
            }
        });
        manageResidents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                overview("Residents");
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login();
            }
        });
    }
    
    private void overview(String wantedList) {
        //Sets up overview scene
        GridPane grid = new GridPane();
        ListView listView = new ListView();
        grid.add(listView, 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(500));
        grid.getRowConstraints().add(new RowConstraints(700));
        Button back = new Button();
        back.setText("Back");
        grid.add(back, 1, 1);
        VBox vbox = new VBox();
        vbox.setPrefWidth(80.0);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(30.0);
        vbox.setPadding(new Insets(100, 10, 10, 10));
        Button add = new Button("Add");
        add.setMinWidth(vbox.getPrefWidth());        
        Button edit = new Button("Edit");
        edit.setMinWidth(vbox.getPrefWidth());        
        Button remove = new Button("Remove");
        remove.setMinWidth(vbox.getPrefWidth());        
        Button select = new Button("Select");
        grid.add(vbox, 1, 0);
        
        //TODO load listview with data for wantedList
        
        if("Admin".equals(type)){
            vbox.getChildren().add(add);
            vbox.getChildren().add(edit);
            vbox.getChildren().add(remove);
        }
        if("Caregiver".equals(type)){
            vbox.getChildren().add(select);
        }
        
        //Tests listView with dummy data
        listView.getItems().add("Test Item1");
        listView.getItems().add("Test Item2");
        if("Staff".equals(wantedList)){
            listView.getItems().add("Test Staff 1");
        }
        if("Residents".equals(wantedList)){
            listView.getItems().add("Test Resident 1");
        }
        
        Scene scene = new Scene(grid, 600, 780);
        stage.setScene(scene);
        
        select.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO make button open diary for selected 'Resident' in 'listView'
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO make button open window where data for new 'Staff' / 'Resident' can be added (based on 'wantedList')
            }
        });
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO make button open window from 'add' with data filled in
            }
        });
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO make button remove selected item in 'listView'
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                moduleSelection();
            }
        });
    }
    
}
