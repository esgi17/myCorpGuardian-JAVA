package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import pa.Models.*;
import pa.annotations.FunctionParsor;

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
    private User userSelected = new User();
    private Pass passSelected = new Pass();
    private int deviceSelectedId;

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

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Load all the devices on the listview",
            lastModified = "17/07/2018",
            apiRoutes = "GET on '/device' "
    )
    public Device[] loadDevices() throws Exception {
        if(devicesList.getSelectionModel().isEmpty()){
            devices.add("No devices");
            devicesList.setItems(devices);
        }
        if(allCheck.isSelected()){
            passCheck.setSelected(true);
            cameraCheck.setSelected(true);
            captorCheck.setSelected(true);
            doorCheck.setSelected(true);
        }
        Device [] res = ListDatas.getDevices();
        devicesList.getItems().clear();
        for(int i=0 ; i< res.length ; i++ ){
            switch(res[i].getDeviceTypeId()){
                case "1":
                    if(doorCheck.isSelected()){
                        devices.add("Door      : " + res[i].getName());
                    }
                    break;
                case "2":
                    if(captorCheck.isSelected()){
                        devices.add("Captor   : " + res[i].getName());
                    }
                    break;
                case "3":
                    if(passCheck.isSelected()){
                        devices.add("Pass       : " + res[i].getName());
                    }
                    break;
                case "4":
                    if(cameraCheck.isSelected()){
                        devices.add("Camera  : " + res[i].getName());
                    }
                    break;
                default:
                    break;
            }
        }
        devicesList.setItems(devices);
        return res;
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Load all the users on the choiceBox",
            lastModified = "17/07/2018",
            apiRoutes = "GET on '/user' "
    )
    public User[] fillUsersList() throws Exception {
        userList.getItems().clear();
        User[] usersArray = ListDatas.getUsers();
        for (int i = 0 ; i < usersArray.length ; i++){
            users.add(usersArray[i].getFirstname().toUpperCase() + ", " + usersArray[i].getLastname());
        }
        userList.setItems(users);
        return usersArray;
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Load all the disponible passes on the comboBox",
            lastModified = "17/07/2018",
            apiRoutes = {"GET on '/device' ", "GET on '/pass' "}
    )
    public Pass[] fillPassesList() throws Exception {
        int k = 0;
        passList.getItems().clear();
        Pass[] passesArray = ListDatas.getPass();
        Pass[] passesReturn = new Pass[passesArray.length];
        Device[] devicesArray = ListDatas.getDevices();
        for (int i = 0 ; i < passesArray.length ; i++){
            if(passesArray[i].getIdUser().equalsIgnoreCase("null")){
                for (int j = 0 ; j < devicesArray.length ; j++){
                    if(devicesArray[j].getId().equalsIgnoreCase(passesArray[i].getIdDevice())){
                        passesReturn[k] = passesArray[i];
                        k++;
                        passes.add(devicesArray[j].getName());
                    }
                }
            }
        }
        passList.setItems(passes);
        return passesReturn;
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Load the devices type on the comboBox",
            lastModified = "17/07/2018"
    )
    private void fillDeviceTypeList() throws Exception {
        deviceTypeList.getItems().clear();
        devicesType.addAll( "Door","Captor","Camera" );
        deviceTypeList.setItems(devicesType);
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Enable URL field if camera is chosen",
            lastModified = "17/07/2018"
    )
    public void enableUrl() {
        deviceTypeSelected = deviceTypeList.getSelectionModel().getSelectedIndex();
        if (deviceTypeSelected == 2){
            urlField.setDisable(false);
        }
        else{
            urlField.setDisable(true);
        }
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Test if string are ok for the textfield",
            lastModified = "17/07/2018"
    )
    private boolean stringVerif(Label label, TextField field, String text){
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


    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Create a device",
            lastModified = "17/07/2018",
            apiRoutes = {"POST on '/door' ", "POST on '/captor'", "POST on '/camera'"}
    )
    public void createDevice() throws Exception{
        if(stringVerif(nameLabel,nameField,"Name") && stringVerif(refLabel,refField,"Ref.")) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Device created");
            JSONObject body = new JSONObject();
            body.put( "name", nameField.getText() );
            body.put( "ref", refField.getText() );
            switch (deviceTypeSelected) {
                case 0:
                    Api.callAPI( "POST", "door/", body );
                    loadDevices();
                    resetValues();
                    alert.showAndWait();
                    break;

                case 1:
                    Api.callAPI( "POST", "captor/", body );
                    loadDevices();
                    resetValues();
                    alert.showAndWait();
                    break;

                case 2:
                    if(stringVerif(urlLabel,urlField,"URL")) {
                        body.put( "url", urlField.getText() );
                        Api.callAPI( "POST", "camera/", body );
                        loadDevices();
                        resetValues();
                        alert.showAndWait();
                    }
                    break;

                default:
                    headCreateDevice.setText( "Select a Device Type" );
                    break;
            }
        }
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Reset values of field and label",
            lastModified = "17/07/2018"
    )
    private void resetValues(){
        nameLabel.setText("Name");
        nameField.setText("");
        refLabel.setText("Ref.");
        refField.setText("");
        urlLabel.setText("URL");
        urlField.setText("");
        headCreateDevice.setText("Create Device");
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Return the user selected on the comboBox",
            lastModified = "17/07/2018"
    )
    private User getUserSelected() throws Exception {
        int userIndex = userList.getSelectionModel().getSelectedIndex();
        if(userIndex>-1) {
            userSelected = fillUsersList()[userIndex];
        }
        else{
            asignLabel.setText("Select a User");
        }
        return userSelected;
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Return the pass selected on the comboBox",
            lastModified = "17/07/2018"
    )
    private Pass getPassSelected() throws Exception {
        int passIndex = passList.getSelectionModel().getSelectedIndex();
        if(passIndex>-1) {
            passSelected = fillPassesList()[passIndex];
        }
        else{
            asignLabel.setText("Select a Pass");
        }
        return passSelected;
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Assign the selected pass to the user selected",
            lastModified = "17/07/2018",
            apiRoutes = {"PUT on '/pass' "}
    )
    public void asignPass() throws Exception {
        JSONObject body = new JSONObject();
        body.put( "user_id", getUserSelected().getId());
        body.put( "id", getPassSelected().getId());
        String res = Api.callAPI( "PUT", "pass/", body );
        if(!res.equalsIgnoreCase("")){
            asignLabel.setText("Assign Pass");
            Alert alert = new Alert( Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Pass assigned");
            alert.showAndWait();
        }
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Set the device selected on the listview",
            lastModified = "17/07/2018"
    )
    public void getDeviceSelected(){
        deviceSelectedId = devicesList.getSelectionModel().getSelectedIndex();
    }

    @FunctionParsor(
            createdBy = "Antoine Cheval",
            description ="Delete devices from listview",
            lastModified = "17/07/2018",
            apiRoutes = {"DELETE on '/door' ", "DELETE on '/captor' ", "DELETE on '/pass' ", "DELETE on '/' "}
    )
    public void deleteDevice() throws Exception {
        JSONObject empty = new JSONObject();
        Device[] devices = loadDevices();
        if(deviceSelectedId!=-1){
            System.out.println(devices[deviceSelectedId].getDeviceTypeId());
            switch (devices[deviceSelectedId].getDeviceTypeId()){
                case "1" :
                    Api.callAPI("DELETE","door/"+devices[deviceSelectedId].getId(),empty);
                    break;
                case "2" :
                    Api.callAPI("DELETE","captor/"+devices[deviceSelectedId].getId(),empty);
                    break;
                case "3" :
                    Api.callAPI("DELETE","pass/"+devices[deviceSelectedId].getId(),empty);
                    break;
                case "4" :
                    Api.callAPI("DELETE","camera/"+devices[deviceSelectedId].getId(),empty);
                    break;
            }
            Alert alert = new Alert( Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Device deleted");
            alert.showAndWait();
        }
        loadDevices();
    }
}
