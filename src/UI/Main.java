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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author andre
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {        
    try {
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("Login.fxml"));
	AnchorPane page = (AnchorPane) loader.load();
	Scene scene = new Scene(page);
	primaryStage.setScene(scene);
	primaryStage.show();
    } catch (IOException e) {
	e.printStackTrace();
    }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
