package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import pa.Models.*;
import pa.annotations.TesterInfo;


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

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
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

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    // Affiche la liste des doors
    private void fillDoorList() throws Exception {
        Device devicesArray[] = ListDatas.getDevices();
        doorList.getItems().clear();
        // Rempli le tableau de doors
        for(int i=0 ; i< devicesArray.length ; i++ ) {
            if (devicesArray[i].getDeviceTypeId().equalsIgnoreCase( "1" )) {
                doors.add( devicesArray[i].getName() );
            }
        }
        doorList.setItems(doors);
    }


    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    private void fillUserList() throws Exception {
        User res[] = ListDatas.getUsers();;
        userList.getItems().clear();
        // Rempli le tableau de groupes
        for(int i=0 ; i< res.length ; i++ ){
            users.add(res[i].getFirstname().toUpperCase() + ", " + res[i].getLastname());
        }
        userList.setItems(users);
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public void loadDatas() throws Exception{
        fillEventList(0,"","");
        fillUserList();
        fillDoorList();
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public void fillDoorEvent() throws Exception {
        Door selectedDoor = getDoor();
        fillEventList(1,selectedDoor.getId(),"");
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public void fillUserEvent() throws Exception {
        fillEventList(2,"",getUserPassId());
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public Door getDoor() throws Exception{
        Door[] doors = ListDatas.getDoors();
        int selectedDoorIndex = doorList.getSelectionModel().getSelectedIndex();
        return doors[selectedDoorIndex];
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public User getUser() throws Exception {
        User[] users = ListDatas.getUsers();
        int selectedUserIndex = userList.getSelectionModel().getSelectedIndex();
        return users[selectedUserIndex];
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
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

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
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
