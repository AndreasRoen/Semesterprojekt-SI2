/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLController implements Initializable {

    @FXML
    private AnchorPane LoginScreen;
    @FXML
    private AnchorPane ModuleSelection;
    @FXML
    private AnchorPane Diary;
    @FXML
    private AnchorPane RootPane;
    
    private ArrayList<AnchorPane> panes;
    
    public FXMLController(){
        System.out.println("FXMLController contructor activated");
        this.panes = new ArrayList<>();
        panes.add(LoginScreen);
        panes.add(ModuleSelection);
        panes.add(Diary);
//        Scene scene = 
        switchTo(LoginScreen);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void switchTo(AnchorPane pane){
        System.out.println("switching to active pane");
//        for (AnchorPane p: panes){
//            if (p != pane){
//                p.setDisable(true);
//                p.setVisible(false);                
//            }else{
//                p.setDisable(false);
//                p.setVisible(true);
//            }
//            
//        }
        System.out.println("active pane is " + pane);
//        RootPane.setPrefSize(pane.getPrefHeight(), pane.getPrefWidth());
    }
    
}
