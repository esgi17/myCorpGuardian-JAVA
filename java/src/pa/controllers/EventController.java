package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import pa.Models.NavHandler;

public class EventController {
    @FXML
    ListView eventList;
    @FXML
    ToggleButton armButton;
    @FXML
    AnchorPane pane;

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }

    public void openUserPage() throws Exception {
        NavHandler.openUserPage(pane);
    }

    public void openGroupPage() throws Exception {
        NavHandler.openGroupPage(pane);
    }

    public void openDoorPage() throws Exception {
        NavHandler.openDoorPage(pane);
    }

    public void openDevicePage() throws Exception {
        NavHandler.openDevicePage(pane);
    }

    ObservableList<String> events = FXCollections.observableArrayList();
    public void setArm(){

    }
    public void fillEventList(){

    }

}
