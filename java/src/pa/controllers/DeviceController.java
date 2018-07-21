package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import pa.Models.*;

public class DeviceController {
    @FXML AnchorPane pane;
    @FXML ListView devicesList;
    @FXML CheckBox passCheck;
    @FXML CheckBox cameraCheck;
    @FXML CheckBox doorCheck;
    @FXML CheckBox captorCheck;
    @FXML CheckBox allCheck;

    @FXML ComboBox deviceTypeList;
    @FXML ComboBox userList;
    @FXML ComboBox passList;

    @FXML TextField nameField;
    @FXML TextField refField;
    @FXML TextField urlField;
    @FXML Label nameLabel;
    @FXML Label refLabel;
    @FXML Label urlLabel;
    @FXML Label headCreateDevice;
    @FXML Label asignLabel;

    private ObservableList<String> devices = FXCollections.observableArrayList();

    private ObservableList<String> users = FXCollections.observableArrayList();
    private ObservableList<String> devicesType = FXCollections.observableArrayList();
    private ObservableList<String> passes = FXCollections.observableArrayList();
    private int deviceTypeSelected = -1;
    User userSelected = new User();
    Pass passSelected = new Pass();

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

    public void initialize() throws Exception {
        passCheck.setSelected(true);
        allCheck.setSelected(true);
        cameraCheck.setSelected(true);
        captorCheck.setSelected(true);
        doorCheck.setSelected(true);
        fillPassesList();
        fillUsersList();
        fillDeviceTypeList();
        loadDevices();
    }

    public Device[] loadDevices() throws Exception {
        if(allCheck.isSelected()){
            passCheck.setSelected(true);
            cameraCheck.setSelected(true);
            captorCheck.setSelected(true);
            doorCheck.setSelected(true);
        }
        Device [] res = ListDatas.getDevices();
        devicesList.getItems().clear();
        // Rempli le tableau de groupes
        for(int i=0 ; i< res.length ; i++ ){
            switch(res[i].getDeviceTypeId()){
                case "1":
                    if(doorCheck.isSelected()){
                        devices.add("Door    : " + res[i].getName());
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
        deviceTypeList.getItems().clear();
        devicesType.addAll( "Door","Captor","Pass","Camera" );
        deviceTypeList.setItems(devicesType);
    }

    public void enableUrl() {
        deviceTypeSelected = deviceTypeList.getSelectionModel().getSelectedIndex();
        if (deviceTypeSelected == 3){
            urlField.setDisable(false);
        }
        else{
            urlField.setDisable(true);
        }
    }

    public boolean stringVerif(Label label, TextField field, String text){
        boolean res = true;
        if (field.getText().equalsIgnoreCase( "" )) {
            label.setText("Empty "+text);
            res = false;
        } else{
            label.setText(text);
        }
        for(int i=0 ; i < field.getText().length() ; i++){
            char car = field.getText().charAt(i);
            if(!Character.isLetter(car) && !Character.isDigit(car) && car!=' '){
                label.setText("Incorrect character in " + text);
                res = false;
            }
        }
        if(res){
            label.setText(text);
        }
        return res;
    }


    public void createDevice() throws Exception{
        if(stringVerif(nameLabel,nameField,"Name") && stringVerif(refLabel,refField,"Ref.")) {
            JSONObject body = new JSONObject();
            body.put( "name", nameField.getText() );
            body.put( "ref", refField.getText() );
            System.out.println(deviceTypeList.getSelectionModel().getSelectedIndex());
            switch (deviceTypeSelected) {
                case 0:
                    Api.callAPI( "POST", "door/", body );
                    loadDevices();
                    resetValues();
                    break;

                case 1:
                    Api.callAPI( "POST", "captor/", body );
                    loadDevices();
                    resetValues();
                    break;

                case 2:
                    Api.callAPI( "POST", "pass/", body );
                    loadDevices();
                    resetValues();
                    break;

                case 3:
                    if(stringVerif(urlLabel,urlField,"URL")) {
                        body.put( "url", urlField.getText() );
                        Api.callAPI( "POST", "camera/", body );
                        loadDevices();
                        resetValues();
                    }
                    break;

                default:
                    headCreateDevice.setText( "Select a Device Type" );
                    break;

            }
        }
    }

    public void resetValues(){
        nameLabel.setText("Name");
        nameField.setText("");
        refLabel.setText("Ref.");
        refField.setText("");
        urlLabel.setText("URL");
        urlField.setText("");
        headCreateDevice.setText("Create Device");
    }

    // Retourne le user selectionne
    public User getUserSelected() throws Exception {
        int userIndex = userList.getSelectionModel().getSelectedIndex();
        if(userIndex>-1) {
            userSelected = fillUsersList()[userIndex];
        }
        else{
            asignLabel.setText("Select a User");
        }
        return userSelected;
    }

    // Retourne le user selectionne
    public Pass getPassSelected() throws Exception {
        int passIndex = passList.getSelectionModel().getSelectedIndex();
        if(passIndex>-1) {
            passSelected = fillPassesList()[passIndex];
        }
        else{
            asignLabel.setText("Select a Pass");
        }
        return passSelected;
    }

    public void asignPass() throws Exception {
        JSONObject body = new JSONObject();
        body.put( "user_id", getUserSelected().getId());
        body.put( "id", getPassSelected().getId());
        String res = Api.callAPI( "PUT", "pass/", body );
        if(!res.equalsIgnoreCase("")){
            asignLabel.setText("Assign Pass");
        }
    }

    public void deleteDevice() throws Exception {
        JSONObject empty = new JSONObject();
        Device[] devices = loadDevices();
        int index = devicesList.getSelectionModel().getSelectedIndex();
        if(index!=-1){
            switch (devices[index].getDeviceTypeId()){
                case "1" :
                    Api.callAPI("DELETE","door/"+devices[index].getId(),empty);
                    break;
                case "2" :
                    Api.callAPI("DELETE","captor/"+devices[index].getId(),empty);
                    break;
                case "3" :
                    Api.callAPI("DELETE","pass/"+devices[index].getId(),empty);
                    break;
                case "4" :
                    Api.callAPI("DELETE","camera/"+devices[index].getId(),empty);
                    break;
            }
        }
        loadDevices();
    }
}
