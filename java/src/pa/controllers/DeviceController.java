package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import pa.Models.*;

public class DeviceController {
    @FXML AnchorPane pane;
    @FXML ListView devicesList;
    @FXML CheckBox passCheck;
    @FXML CheckBox cameraCheck;
    @FXML CheckBox doorCheck;
    @FXML CheckBox captorCheck;

    @FXML ComboBox deviceTypeList;
    @FXML ComboBox userList;
    @FXML ComboBox passList;

    private ObservableList<String> devices = FXCollections.observableArrayList();

    private ObservableList<String> users = FXCollections.observableArrayList();
    private ObservableList<String> devicesType = FXCollections.observableArrayList();
    private ObservableList<String> passes = FXCollections.observableArrayList();

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

    public void openEventPage() throws Exception {
        NavHandler.openEventPage(pane);
    }

    public Device[] loadDevices() throws Exception {
        Device [] res = ListDatas.getDevices();
        devicesList.getItems().clear();
        // Rempli le tableau de groupes
        for(int i=0 ; i< res.length ; i++ ){
            switch(res[i].getDeviceTypeId()){
                case "1":
                    if(doorCheck.isSelected()){
                        devices.add("Door     : " + res[i].getName());
                    }
                    break;
                case "2":
                    if(captorCheck.isSelected()){
                        devices.add("Captor : " + res[i].getName());
                    }
                    break;
                case "3":
                    if(passCheck.isSelected()){
                        devices.add("Pass    : " + res[i].getName());
                    }
                    break;
                case "4":
                    if(cameraCheck.isSelected()){
                        devices.add("Camera : " + res[i].getName());
                    }
                    break;
                default:
                    break;
            }
        }
        devicesList.setItems(devices);

        return res;
    }

    //Rempli la combobox avec tout les groupes
    public User[] fillUsersList() throws Exception {
        userList.getItems().clear();
        User[] usersArray = ListDatas.getUsers();
        for (int i = 0 ; i < usersArray.length ; i++){
            users.add(usersArray[i].getFirstname().toUpperCase() + ", " + usersArray[i].getLastname());
        }
        userList.setItems(users);
        return usersArray;
    }

    //Rempli la combobox avec tout les groupes
    public Pass[] fillPassesList() throws Exception {
        passList.getItems().clear();
        Pass[] passesArray = ListDatas.getPass();
        for (int i = 0 ; i < passesArray.length ; i++){
            passes.add(passesArray[i].getId());
        }
        passList.setItems(passes);
        return passesArray;
    }

    //Rempli la combobox avec tout les groupes
    public void fillDeviceTypeList() throws Exception {
        devicesType.addAll( "Door","Captor","Pass","Camera" );

        deviceTypeList.setItems(devicesType);
    }
}
