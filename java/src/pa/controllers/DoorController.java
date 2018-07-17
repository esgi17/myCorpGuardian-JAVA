package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;
import pa.Models.*;


public class DoorController {
    @FXML Label mondayOpen;
    @FXML Slider mondayOpenSlider;
    @FXML Label mondayClose;
    @FXML Slider mondayCloseSlider;
    @FXML Label tuesdayOpen;
    @FXML Slider tuesdayOpenSlider;
    @FXML Label tuesdayClose;
    @FXML Slider tuesdayCloseSlider;
    @FXML Label wednesdayOpen;
    @FXML Slider wednesdayOpenSlider;
    @FXML Label wednesdayClose;
    @FXML Slider wednesdayCloseSlider;
    @FXML Label thursdayOpen;
    @FXML Slider thursdayOpenSlider;
    @FXML Label thursdayClose;
    @FXML Slider thursdayCloseSlider;
    @FXML Label fridayOpen;
    @FXML Slider fridayOpenSlider;
    @FXML Label fridayClose;
    @FXML Slider fridayCloseSlider;
    @FXML Label saturdayOpen;
    @FXML Slider saturdayOpenSlider;
    @FXML Label saturdayClose;
    @FXML Slider saturdayCloseSlider;
    @FXML Label sundayOpen;
    @FXML Slider sundayOpenSlider;
    @FXML Label sundayClose;
    @FXML Slider sundayCloseSlider;

    @FXML Button loadDoorsBtn;
    @FXML ListView doorsList;
    @FXML Label newDoorNameLabel;
    @FXML TextField newDoorNameField;
    @FXML Label newDoorRefLabel;
    @FXML TextField newDoorRefField;
    @FXML Button newDoorBtn;

    @FXML ComboBox groupsList;
    @FXML Button createBtn;
    @FXML Button deleteBtn;
    @FXML Button updateBtn;

    ObservableList<String> doors = FXCollections.observableArrayList();
    ObservableList<String> groups = FXCollections.observableArrayList();


    // Affiche la liste des doors
    public Door[] fillDoorsList() throws Exception {
        Door res[] = ListDatas.getDoors();
        doorsList.getItems().clear();
        // Rempli le tableau de doors
        for(int i=0 ; i< res.length ; i++ ){
            doors.add(res[i].getName());
        }
        doorsList.setItems(doors);
        return res;
    }

    //Rempli la combobox avec tout les groupes
    public void fillGroupsList() throws Exception {
        groupsList.getItems().clear();
        Group[] groupsArray = ListDatas.getGroups();
        for (int i = 0 ; i < groupsArray.length ; i++){
            groups.add(groupsArray[i].getName());
        }
        groupsList.setItems(groups);
    }


    public void setHour(){
        mondayOpen.textProperty().setValue(getHour(mondayOpenSlider.getValue()));
        mondayClose.textProperty().setValue(getHour(mondayCloseSlider.getValue()));
        tuesdayOpen.textProperty().setValue(getHour(tuesdayOpenSlider.getValue()));
        tuesdayClose.textProperty().setValue(getHour(tuesdayCloseSlider.getValue()));
        wednesdayOpen.textProperty().setValue(getHour(wednesdayOpenSlider.getValue()));
        wednesdayClose.textProperty().setValue(getHour(wednesdayCloseSlider.getValue()));
        thursdayOpen.textProperty().setValue(getHour(thursdayOpenSlider.getValue()));
        thursdayClose.textProperty().setValue(getHour(thursdayCloseSlider.getValue()));
        fridayOpen.textProperty().setValue(getHour(fridayOpenSlider.getValue()));
        fridayClose.textProperty().setValue(getHour(fridayCloseSlider.getValue()));
        saturdayOpen.textProperty().setValue(getHour(saturdayOpenSlider.getValue()));
        saturdayClose.textProperty().setValue(getHour(saturdayCloseSlider.getValue()));
        sundayOpen.textProperty().setValue(getHour(sundayOpenSlider.getValue()));
        sundayClose.textProperty().setValue(getHour(sundayCloseSlider.getValue()));
    }

    public String getHour(double number){
        int hours = (int)number / 60;
        int minutes = (int) number % 60;
        minutes -= minutes % 5;
        String hour = "";
        String minute = "";
        if (hours == 0){
            hour = "00";
        }
        else{
            hour = Integer.toString(hours);
        }
        if (minutes < 10){
            minute = "0" + minutes;
        }
        else{
            minute = Integer.toString(minutes);
        }
        return hour + ":" + minute;
    }

    // Verif sur un chaine de caractere
    public boolean stringVerification(){
        boolean res = true;
        if (newDoorNameField.getText().equalsIgnoreCase( "" )) {
            newDoorNameLabel.setText("Empty Name");
            res = false;
        } else{
            newDoorNameLabel.setText("Name");
        }

        if (newDoorRefField.getText().equalsIgnoreCase( "" )) {
            newDoorRefLabel.setText("Empty Ref.");
            res = false;
        } else{
            newDoorNameLabel.setText("Ref.");
        }
        for(int i=0 ; i < newDoorNameField.getText().length() ; i++){
            char car = newDoorNameField.getText().charAt(i);
            if(!Character.isLetter(car) && !Character.isDigit(car) && car!=' '){
                newDoorNameLabel.setText("Incorrect character in Name");
                res = false;
            }
        }
        for(int i=0 ; i < newDoorRefField.getText().length() ; i++){
            char car = newDoorRefField.getText().charAt(i);
            if(!Character.isLetter(car) && !Character.isDigit(car) && car!=' '){
                newDoorRefLabel.setText("Incorrect character in Ref");
                res = false;
            }
        }
        if(res){
            newDoorNameLabel.setText("Name");
            newDoorRefLabel.setText("Ref.");
        }
        return res;
    }

    public boolean addDoor() throws Exception {
        // Verif si champ vide
        if (!stringVerification()) {
            return false;
        } else {
            //Creation device
            JSONObject bodyDevice = new JSONObject();
            bodyDevice.put( "device_type_id", "1");
            String res = Api.callAPI( "POST", "device/", bodyDevice );
            JSONObject device = new JSONObject(res);
            JSONObject deviceId = new JSONObject(device.getString("datas"));

            //Creation door avec id de la device
            JSONObject bodyDoor = new JSONObject();
            bodyDoor.put( "name", newDoorNameField.getText());
            bodyDoor.put( "ref", newDoorRefField.getText());
            bodyDoor.put( "device_id", deviceId.getString("id"));

            res = Api.callAPI( "POST", "door/", bodyDoor );
            JSONObject apiReturn = new JSONObject( res );

            if (apiReturn.getString( "success" ) == "true") {
                return true;
            } else {
                System.out.println( apiReturn.toString() );
                return false;
            }
        }
    }

    private void getSchedule(){

    }

}
