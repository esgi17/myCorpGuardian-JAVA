package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;

public class EventController {
    @FXML
    ListView eventList;
    @FXML
    ToggleButton armButton;


    ObservableList<String> events = FXCollections.observableArrayList();
    public void setArm(){

    }
    public void fillEventList(){

    }

}
