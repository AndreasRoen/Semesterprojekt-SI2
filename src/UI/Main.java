
package UI;

import DomainLayer.DomainHub;
import DomainLayer.PresentationInterface;
import DomainLayer.UserType;
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

<<<<<<< HEAD
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

=======
    private String type;

    private ObservableList<String> residents;

    private ObservableList<String> staff;

    //TODO use userId to fetch ObserableLists from other layers
    private UUID userId;

    private ListView listView;

    private boolean toggleOn;
    
    Random r = new Random();

>>>>>>> AutoGen
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

<<<<<<< HEAD
=======
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
            } else if ("resident".equals(username) && "pass".equals(password)) {
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
>>>>>>> AutoGen
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
        //TODO load every package in 'Modules' package and add them to HBox in scene as new 'Module'
        //TODO call the 'getScene' of selected 'Module', then that scene while hiding the 'moduleSelection' scene
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
<<<<<<< HEAD
        //TODO change button addition to Domain layer
        if(type == UserType.type.ADMIN){
=======
        if ("Admin".equals(type)) {
>>>>>>> AutoGen
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
                if (type == UserType.type.USER) {
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
                //TODO open calender (Not being implemented in this project)
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

<<<<<<< HEAD
    //TODO delete method when modules are fully implemnted
=======
>>>>>>> AutoGen
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
<<<<<<< HEAD
=======
        Button edit = new Button("Edit");
        edit.setMinWidth(vbox.getPrefWidth());
>>>>>>> AutoGen
        Button remove = new Button("Remove");
        remove.setMinWidth(vbox.getPrefWidth());
        Button select = new Button("Select");
        grid.add(vbox, 1, 0);

        //Sets content of 'Overview' based on user type and 'wantedList'
<<<<<<< HEAD
        if (type == UserType.type.ADMIN) {
=======
        if ("Admin".equals(type)) {
>>>>>>> AutoGen
            vbox.getChildren().add(add);
            vbox.getChildren().add(remove);
        }
<<<<<<< HEAD
        if (type == UserType.type.USER) {
=======
        if ("Caregiver".equals(type)) {
>>>>>>> AutoGen
            vbox.getChildren().add(select);
        }

        //TODO make call to 'Domain' layer and get the wanted list from there
        if ("Staff".equals(wantedList)) {
            listView.setItems(staff);
        }
        if ("Residents".equals(wantedList)) {
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

<<<<<<< HEAD
    //TODO delete method when modules are fully implemented
=======
>>>>>>> AutoGen
    private void listPromt(String namePrompt) {
        //Sets up popup window
        GridPane pop = new GridPane();
        pop.setPadding(new Insets(25, 25, 25, 25));
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
<<<<<<< HEAD
                //TODO save additions permanently in another layer
                listView.getItems().add(name.textProperty().getValue());
                popup.close();
=======
                if (isValid(name.textProperty().getValue())) {
                    //TODO save additions permanently in another layer
                    listView.getItems().add(name.textProperty().getValue());
                    popup.close();
                } else {
                    status.setText("Invalid information entered");
                }
>>>>>>> AutoGen
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.close();
            }
        });
    }

<<<<<<< HEAD
    //TODO delete method when modules are fully implemented
=======
>>>>>>> AutoGen
    private void diary(String name) {
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
        Button autoGen = new Button("AutoGen");
        Button stopGen = new Button("HaltAuto");
        grid.add(back, 1, 1);

        //Date getter, delete if automatic timestamp is not needed in diary
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

<<<<<<< HEAD
        if (type == UserType.type.USER) {
=======
        if ("Caregiver".equals(type)) {
>>>>>>> AutoGen
            grid.add(input, 0, 1);
            vbox.getChildren().add(add);
            vbox.getChildren().add(remove);
            vbox.getChildren().add(autoGen);
            vbox.getChildren().add(stopGen);
        }

        Scene scene = new Scene(grid, 600, 780);
        Stage diary = new Stage();
        diary.setScene(scene);
        diary.setTitle(name + " Diary");
        stage.close();
        diary.show();
<<<<<<< HEAD
=======
        
        Timeline autoGenMessage = new Timeline(new KeyFrame(Duration.seconds(r.nextInt(8 - 2 + 1) + 2), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Date date = new Date();
                String[] messages = new String[5];
                messages[0] = "Borger har modtaget penicillin";
                messages[1] = "Borger har modtaget insulin";
                messages[2] = "Borger har modtaget paracetamol";
                messages[3] = "Borger har modtaget melatonin";
                messages[4] = "Borger har modtaget halcion";
                listView.getItems().add(dateFormat.format(date) + "\n" + messages[r.nextInt(messages.length)]);
            }
        }));
        autoGenMessage.setCycleCount(Timeline.INDEFINITE);
>>>>>>> AutoGen

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
                toggleOn = false;
                diary.close();
                stage.show();
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
<<<<<<< HEAD
                //TODO save additions permanently in another layer
                Date date = new Date();
                //TODO add users name after date but before input text.
                listView.getItems().add(dateFormat.format(date) + "\n" + input.getText());

=======
                //NOTE 'isValid' is not necessarily needed since only approved users have access
                if (isValid(input.getText())) {
                    //TODO save additions permanently in another layer
                    Date date = new Date();
                    listView.getItems().add(dateFormat.format(date) + "\n" + input.getText());
                }
>>>>>>> AutoGen
            }
        });

        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
            }
        });
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
        
        
    }

}
