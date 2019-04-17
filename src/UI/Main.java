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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author andre
 */
public class Main extends Application {
    
    private Stage stage;
    
    private String type;
    
    @Override
    public void start(Stage primaryStage) {
//        stage.setTitle("Socialportalen");
        this.stage = primaryStage;
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 960, 720);
        stage.setScene(scene);
//        scene.getStylesheets().add(Main.class.getResource("Stylesheet.css").toExternalForm());
        stage.setResizable(false);
        stage.show();
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
        loginGrid.add(txUsername, 0 , 1);
        TextField txPassword = new TextField();
        txPassword.setPromptText("Password");
        loginGrid.add(txPassword, 0 , 2);
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
                if(validLogin(txUsername.getText(), txPassword.getText()) == true){
                    //TODO go to next page
                    //TODO set ser type
                    if("Caregiver".equals(type)){
                        moduleSelection();
                    }
                    if("Admin".equals(type)){
                        manager();
                    }
                } else {
                    status.setText("Invalid Username / Password");
                }
            }

            
        });
        
    }
    private boolean validLogin(String username, String password) {
        //TODO check with existing accounts in DATA layer
        if("user".equals(username) && "pass".equals(password)){
            type = "Caregiver";
            return true;
        } else if ("admin".equals(username) && "pass".equals(password)) {
            type = "Admin";
            return true;
        } else {
            return false;
        }
    }
    
    private void moduleSelection(){
        //Sets up module selection scene
        
    }

    private void manager(){
        //Sets up manager scene
        
    }
    
}
