/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
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

/**
 *
 * @author andre
 */
public class Main extends Application {
    
    private Stage stage;
    
    private String type;
    
    private ObservableList<String> residents;
    
    private ObservableList<String> staff;
    
    //TODO use userId to fetch ObserableLists from other layers
    private UUID userId;
    
    private ListView listView;
    
    
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

        //Checks if login info is valid
        signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validLogin(txUsername.getText(), txPassword.getText())) {
                    //TODO load 'residents' / 'staff' lists from DATA layer
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
            } else if ("resident".equals(username) && "pass".equals(password)){
                type = "Resident";
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
        
        if ("Admin".equals(type)) {
            modules.getChildren().add(manageStaff);
            modules.getChildren().add(manageResidents);
        } else {
            modules.getChildren().add(diary);
            modules.getChildren().add(calender);
        }
        
        Scene scene = new Scene(moduleGrid, 450, 300);
        stage.setScene(scene);
        
        diary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if ("Caregiver".equals(type)) {
                    overview("Residents");
                } else {
                    //TODO set user id as argument
                    //TODO make 'diary' open diary of the user with the id as argument.
                    diary("");
                }                
            }
        });
        calender.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO open calender (Should not be implemented)
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
        listView = new ListView();
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
        
        //Sets content of 'Overview' based on user type and 'wantedList'
        if("Admin".equals(type)){
            vbox.getChildren().add(add);
            vbox.getChildren().add(edit);
            vbox.getChildren().add(remove);
        }
        if("Caregiver".equals(type)){
            vbox.getChildren().add(select);
        }
        
        //TODO make call to 'Domain' layer and get the wanted list from there
        if("Staff".equals(wantedList)){
            listView.setItems(staff);
        }
        if("Residents".equals(wantedList)){
            listView.setItems(residents);
        }
        
        Scene scene = new Scene(grid, 600, 780);
        stage.setScene(scene);
        
        select.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                diary(listView.getSelectionModel().getSelectedItem().toString());
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO save changes made to 'Resident' / 'Staff' list
                listPromt("");
            }
        });
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO save changes made to 'Resident' / 'Staff' list
                listPromt(listView.getSelectionModel().getSelectedItem().toString());
                //TODO remove original of edited item if edit is not aborted
            }
        });
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO make removes permanent
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                moduleSelection();
            }
        });
    }
    
    private void listPromt(String namePrompt){
        //Sets up popup window
        GridPane pop = new GridPane();
        pop.setPadding(new Insets(25,25,25,25));
        pop.setVgap(15.0);
        pop.setHgap(10.0);
        Label nameLabel = new Label("Name");
        nameLabel.setAlignment(Pos.CENTER);
        pop.add(nameLabel, 0, 0);
        TextField name = new TextField();
        name.setText(namePrompt);
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
                if (isValid(name.textProperty().getValue())){
                    //TODO save additions permanently in another layer
                    listView.getItems().add(name.textProperty().getValue());
                    popup.close();
                } else {
                    status.setText("Invalid information entered");
                }
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.close();
            }
        });
    }    
    
    private void diary(String name){ 
        //Sets up the scene
        GridPane grid = new GridPane();
        listView = new ListView();
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
        
        if("Caregiver".equals(type)){
            grid.add(input, 0, 1);
            vbox.getChildren().add(add);
            vbox.getChildren().add(remove);
        }
        
        Scene scene = new Scene(grid, 600, 780);
        Stage diary = new Stage();
        diary.setScene(scene);
        diary.setTitle(name+ " Diary");
        stage.close();
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
                stage.show();
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isValid(input.getText())){
                    //TODO save additions permanently in another layer
                    Date date = new Date();
                listView.getItems().add(dateFormat.format(date)+"\n"+input.getText());
                }
            }
        });
        //NOTE this 'remove' should ONLY be available for testing
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
            }
        });
    }
}
