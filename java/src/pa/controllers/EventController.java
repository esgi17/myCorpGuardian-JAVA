package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import pa.Models.*;
import pa.annotations.FunctionParsor;


public class EventController  {
    @FXML ListView eventList;
    @FXML ListView doorList;
    @FXML ListView userList;
    @FXML AnchorPane pane;

    private ObservableList<String> events = FXCollections.observableArrayList();
    private ObservableList<String> doors  = FXCollections.observableArrayList();
    private ObservableList<String> users  = FXCollections.observableArrayList();


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

    public void initialize() throws Exception{
        loadDatas();
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Fill the event list depends on the door or the user selected",
            lastModified = "17/07/2018",
            apiRoutes = "GET on '/event' "
    )
    private void fillEventList(int searchType, String doorId, String passId) throws Exception {
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
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Fill and return all doors",
            lastModified = "17/07/2018",
            apiRoutes = "GET on '/door' "
    )
    private void fillDoorList() throws Exception {
        Device devicesArray[] = ListDatas.getDevices();
        doorList.getItems().clear();
        for(int i=0 ; i< devicesArray.length ; i++ ) {
            if (devicesArray[i].getDeviceTypeId().equalsIgnoreCase( "1" )) {
                doors.add( devicesArray[i].getName() );
            }
        }
        doorList.setItems(doors);
    }


    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Fill and return all users",
            lastModified = "17/07/2018",
            apiRoutes = "GET on '/user' "
    )
    private void fillUserList() throws Exception {
        User res[] = ListDatas.getUsers();;
        userList.getItems().clear();
        for(int i=0 ; i< res.length ; i++ ){
            users.add(res[i].getFirstname().toUpperCase() + ", " + res[i].getLastname());
        }
        userList.setItems(users);
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Fill the 3 listView",
            lastModified = "17/07/2018",
            apiRoutes = {"GET on '/door' ","GET on '/event' ","GET on '/user' "}
    )
    public void loadDatas() throws Exception{
        fillEventList(0,"","");
        fillUserList();
        fillDoorList();
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Fill the event list depends on door ",
            lastModified = "17/07/2018",
            apiRoutes = {"GET on '/door' ","GET on '/event' "}
    )
    public void fillDoorEvent() throws Exception {
        Door selectedDoor = getDoor();
        fillEventList(1,selectedDoor.getId(),"");
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Fill the event list depends on user ",
            lastModified = "17/07/2018",
            apiRoutes = {"GET on '/user' ","GET on '/event' "}
    )
    public void fillUserEvent() throws Exception {
        fillEventList(2,"",getUserPassId());
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Return selected door",
            lastModified = "17/07/2018"
    )
    public Door getDoor() throws Exception{
        Door[] doors = ListDatas.getDoors();
        int selectedDoorIndex = doorList.getSelectionModel().getSelectedIndex();
        return doors[selectedDoorIndex];
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Return selected user",
            lastModified = "17/07/2018"
    )
    public User getUser() throws Exception {
        User[] users = ListDatas.getUsers();
        int selectedUserIndex = userList.getSelectionModel().getSelectedIndex();
        return users[selectedUserIndex];
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Return id pass of the user ",
            lastModified = "17/07/2018",
            apiRoutes = {"GET on '/pass' "}
    )
    private String getUserPassId() throws Exception {
        String res = "0";
        User userSelected = getUser();
        Pass[] passes = ListDatas.getPass();
        for(int i=0 ; i< passes.length ; i++ ){
            if(passes[i].getIdUser().equalsIgnoreCase(userSelected.getId())){
                res = passes[i].getId();
                break;
            }
        }
        return res;
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Parse the event hour to display it ",
            lastModified = "17/07/2018"
    )
    private String parseHour(String hour){
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
