/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLController implements Initializable {

    //IDs from Login screen
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label loginStatus;

    public void login(ActionEvent event) {
        if (txtUsername.getText().equals("user") && txtPassword.getText().equals("pass")) {
            moduleSelection(event);
        } else {
            loginStatus.setText("Wrong Username / Password");
        }
    }

    public void moduleSelection(ActionEvent event) {
        changeScene(event, "ModuleSelector");
    }

    public void dairy(ActionEvent event) {
        changeScene(event, "Dairy");
    }

    public void calender(ActionEvent event) {
        changeScene(event, "Calender");
    }
    
    public void logoff(ActionEvent event) {
        changeScene(event, "Login");
    }

    public void changeScene(ActionEvent event, String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FXMLController() {

    }

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
