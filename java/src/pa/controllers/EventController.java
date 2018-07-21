package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import pa.Models.*;

public class EventController {
    @FXML ListView eventList;
    @FXML ListView doorList;
    @FXML ListView userList;
    @FXML AnchorPane pane;

    ObservableList<String> events = FXCollections.observableArrayList();
    ObservableList<String> doors  = FXCollections.observableArrayList();
    ObservableList<String> users  = FXCollections.observableArrayList();

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

    public Event[] fillEventList(int searchType, String doorId, String passId) throws Exception {
        Event res[] = ListDatas.getEvent();;
        eventList.getItems().clear();
        // Rempli le tableau de groupes
        for(int i=0 ; i< res.length ; i++ ){
            switch(searchType){
                case 0:
                    events.add(parseHour(res[i].getDate()) + "  " + res[i].getTitle());
                    break;
                case 1:
                    if(res[i].getDeviceId().equalsIgnoreCase(doorId)){
                        events.add(parseHour(res[i].getDate()) + "  " + res[i].getTitle());
                    }
                    break;
                case 2:
                    if(res[i].getPassId().equalsIgnoreCase(passId)){
                        events.add(parseHour(res[i].getDate()) + "  " + res[i].getTitle());
                    }
                    break;

            }

        }
        eventList.setItems(events);
        return res;
    }

    public Door[] fillDoorList() throws Exception {
        Door res[] = ListDatas.getDoors();;
        doorList.getItems().clear();
        // Rempli le tableau de groupes
        for(int i=0 ; i< res.length ; i++ ){
            doors.add(res[i].getName());
        }
        doorList.setItems(doors);
        return res;
    }

    public User[] fillUserList() throws Exception {
        User res[] = ListDatas.getUsers();;
        userList.getItems().clear();
        // Rempli le tableau de groupes
        for(int i=0 ; i< res.length ; i++ ){
            users.add(res[i].getFirstname().toUpperCase() + ", " + res[i].getLastname());
        }
        userList.setItems(users);
        return res;
    }

    public void loadDatas() throws Exception{
        fillEventList(0,"","");
        fillUserList();
        fillDoorList();
    }

    public void fillDoorEvent() throws Exception {
        Door selectedDoor = getDoor();
        fillEventList(1,selectedDoor.getId(),"");
    }

    public void fillUserEvent() throws Exception {
        fillEventList(2,"",getUserPassId());
    }

    public Door getDoor() throws Exception{
        Door[] doors = ListDatas.getDoors();
        int selectedDoorIndex = doorList.getSelectionModel().getSelectedIndex();
        return doors[selectedDoorIndex];
    }

    public User getUser() throws Exception {
        User[] users = ListDatas.getUsers();
        int selectedUserIndex = userList.getSelectionModel().getSelectedIndex();
        return users[selectedUserIndex];
    }

    public String getUserPassId() throws Exception {
        String res = "0";
        User userSelected = getUser();
        Pass[] passes = ListDatas.getPass();
        System.out.println(passes[0].getId());
        for(int i=0 ; i< passes.length ; i++ ){
            System.out.println(passes.length);
            if(passes[i].getIdUser().equalsIgnoreCase(userSelected.getId())){
                res = passes[i].getId();
                break;
            }
        }
        return res;
    }

    public String parseHour(String hour){
        String res = "";
        if(hour.length() > 0){
            res = hour.substring(8,10) + "/"
                    + hour.substring(5,7) + "/"
                    + hour.substring(2,4) +" à "
                    + hour.substring(11,16);
        }
        return res;
    }

}
