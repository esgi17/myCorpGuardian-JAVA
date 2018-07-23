package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import pa.Main;
import pa.Models.*;

import java.util.ArrayList;


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
    @FXML Label setScheduleLabel;

    @FXML Label mondayLabel;
    @FXML Label tuesdayLabel;
    @FXML Label wednesdayLabel;
    @FXML Label thursdayLabel;
    @FXML Label fridayLabel;
    @FXML Label saturdayLabel;
    @FXML Label sundayLabel;

    @FXML ComboBox groupsList;
    @FXML Button createBtn;
    @FXML Button deleteBtn;
    @FXML Button updateBtn;

    @FXML Label doorLabel;

    @FXML CheckBox allDay;

    @FXML AnchorPane pane;

    private ObservableList<String> doors = FXCollections.observableArrayList();
    private ObservableList<String> groups = FXCollections.observableArrayList();

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }

    public void openUserPage() throws Exception {
        NavHandler.openUserPage(pane);
    }

    public void openGroupPage() throws Exception {
        NavHandler.openGroupPage(pane);
    }

    public void openDevicePage() throws Exception {
        NavHandler.openDevicePage(pane);
    }

    public void openEventPage() throws Exception {
        NavHandler.openEventPage(pane);
    }

    public void initialize() throws Exception{
        fillDoorsList();
        fillGroupsList();
    }

    // Affiche la liste des doors
    private void fillDoorsList() throws Exception {
        Device devicesArray[] = ListDatas.getDevices();
        doorsList.getItems().clear();
        // Rempli le tableau de doors
        for(int i=0 ; i< devicesArray.length ; i++ ){
            if(devicesArray[i].getDeviceTypeId().equalsIgnoreCase("1")) {
                doors.add( devicesArray[i].getName() );
            }
        }
        doorsList.setItems(doors);
    }

    //Rempli la combobox avec tout les groupes
    public Group[] fillGroupsList() throws Exception {
        if (isGroupAndDoorSelected()){
            loadSchedules();
        }
        groupsList.getItems().clear();
        Group[] groupsArray = ListDatas.getGroups();
        for (int i = 0 ; i < groupsArray.length ; i++){
            groups.add(groupsArray[i].getName());
        }
        groupsList.setItems(groups);
        return groupsArray;
    }

    public void allDays() {
        if(allDay.isSelected()){
            tuesdayOpenSlider.setDisable(true);
            tuesdayCloseSlider.setDisable(true);
            wednesdayOpenSlider.setDisable(true);
            wednesdayCloseSlider.setDisable(true);
            thursdayOpenSlider.setDisable(true);
            thursdayCloseSlider.setDisable(true);
            fridayOpenSlider.setDisable(true);
            fridayCloseSlider.setDisable(true);
            saturdayOpenSlider.setDisable(true);
            saturdayCloseSlider.setDisable(true);
            sundayOpenSlider.setDisable(true);
            sundayCloseSlider.setDisable(true);
        }
        else {
            tuesdayOpenSlider.setDisable(false);
            tuesdayCloseSlider.setDisable(false);
            wednesdayOpenSlider.setDisable(false);
            wednesdayCloseSlider.setDisable(false);
            thursdayOpenSlider.setDisable(false);
            thursdayCloseSlider.setDisable(false);
            fridayOpenSlider.setDisable(false);
            fridayCloseSlider.setDisable(false);
            saturdayOpenSlider.setDisable(false);
            saturdayCloseSlider.setDisable(false);
            sundayOpenSlider.setDisable(false);
            sundayCloseSlider.setDisable(false);
        }
    }


    public void setHour(){
        if(allDay.isSelected()){
            mondayOpen.textProperty().setValue(getHour(mondayOpenSlider.getValue()));
            mondayClose.textProperty().setValue(getHour(mondayCloseSlider.getValue()));
            tuesdayOpen.textProperty().setValue(getHour(mondayOpenSlider.getValue()));
            tuesdayClose.textProperty().setValue(getHour(mondayCloseSlider.getValue()));
            wednesdayOpen.textProperty().setValue(getHour(mondayOpenSlider.getValue()));
            wednesdayClose.textProperty().setValue(getHour(mondayCloseSlider.getValue()));
            thursdayOpen.textProperty().setValue(getHour(mondayOpenSlider.getValue()));
            thursdayClose.textProperty().setValue(getHour(mondayCloseSlider.getValue()));
            fridayOpen.textProperty().setValue(getHour(mondayOpenSlider.getValue()));
            fridayClose.textProperty().setValue(getHour(mondayCloseSlider.getValue()));
            saturdayOpen.textProperty().setValue(getHour(mondayOpenSlider.getValue()));
            saturdayClose.textProperty().setValue(getHour(mondayCloseSlider.getValue()));
            sundayOpen.textProperty().setValue(getHour(mondayOpenSlider.getValue()));
            sundayClose.textProperty().setValue(getHour(mondayCloseSlider.getValue()));
        }
        else {
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
    }

    private String getHour(double number){
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

    public void writeDoor() throws Exception{
        doorLabel.setVisible(true);
        doorLabel.setText(doors.get(doorsList.getSelectionModel().getSelectedIndex()));
        load();
    }
    public void load() throws Exception{
        if (isGroupAndDoorSelected()){
            loadSchedules();
        }
    }

    // Verif sur un chaine de caractere
    private boolean stringVerification(){
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
            newDoorRefLabel.setText("Ref.");
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

    public void addDoor() throws Exception {
        // Verif si champ vide
        if (stringVerification()) {
            //Creation door avec id de la device
            JSONObject bodyDoor = new JSONObject();
            bodyDoor.put( "name", newDoorNameField.getText());
            bodyDoor.put( "ref", newDoorRefField.getText());

            Api.callAPI( "POST", "door/", bodyDoor );
            Alert alert = new Alert( Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Door created");
            alert.showAndWait();
        }
        fillDoorsList();
    }

    private int parseHour(String time){
        int res = 0;
        String hours = time.substring(0,2);
        String minutes = time.substring(3,5);
        res = Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);
        return res;
    }

    public Group getGroup() throws Exception{
        Group[] groups = ListDatas.getGroups();
        int selectedGroupIndex = groupsList.getSelectionModel().getSelectedIndex();
        return groups[selectedGroupIndex];
    }

    public Door getDoor() throws Exception{
        Door[] doors = ListDatas.getDoors();
        int selectedDoorIndex = doorsList.getSelectionModel().getSelectedIndex();
        return doors[selectedDoorIndex];
    }

    private Schedule getSchedule(String groupId, String doorId, String day) throws Exception{
        boolean exist = false;
        Schedule[] schedules = ListDatas.getSchedule();
        Schedule schedule = new Schedule();
        for(int i = 0; i < schedules.length ; i++){
            if(schedules[i].getDoorId().equalsIgnoreCase(doorId)
                    && schedules[i].getGroupId().equalsIgnoreCase(groupId)
                    && schedules[i].getDay().equalsIgnoreCase(day)) {

                schedule = schedules[i];
                exist = true;
            }
        }
        if (exist) {
            return schedule;
        }
        else{
            return null;
        }
    }

    private Schedule loadDailySchedule(String day) throws Exception{
        Door door = getDoor();
        Group group = getGroup();
        return getSchedule(group.getId(), door.getId(), day);
    }

    public void loadSchedules() throws Exception {
        if(isGroupAndDoorSelected()) {
            setScheduleLabel.setText("Set Schedule");
            for (int i = 0; i < 7; i++) {
                Schedule schedule = loadDailySchedule( Integer.toString( i ) );
                if (schedule != null) {
                    switch (i) {
                        case 0:
                            mondayOpenSlider.setValue( parseHour( schedule.getHStart() ) );
                            mondayCloseSlider.setValue( parseHour( schedule.getHStop() ) );
                            break;
                        case 1:
                            tuesdayOpenSlider.setValue( parseHour( schedule.getHStart() ) );
                            tuesdayCloseSlider.setValue( parseHour( schedule.getHStop() ) );
                            break;
                        case 2:
                            wednesdayOpenSlider.setValue( parseHour( schedule.getHStart() ) );
                            wednesdayCloseSlider.setValue( parseHour( schedule.getHStop() ) );
                            break;
                        case 3:
                            thursdayOpenSlider.setValue( parseHour( schedule.getHStart() ) );
                            thursdayCloseSlider.setValue( parseHour( schedule.getHStop() ) );
                            break;
                        case 4:
                            fridayOpenSlider.setValue( parseHour( schedule.getHStart() ) );
                            fridayCloseSlider.setValue( parseHour( schedule.getHStop() ) );
                            break;
                        case 5:
                            saturdayOpenSlider.setValue( parseHour( schedule.getHStart() ) );
                            saturdayCloseSlider.setValue( parseHour( schedule.getHStop() ) );
                            break;
                        case 6:
                            sundayOpenSlider.setValue( parseHour( schedule.getHStart() ) );
                            sundayCloseSlider.setValue( parseHour( schedule.getHStop() ) );
                            break;
                        default:
                            break;
                    }
                    updateBtn.setDisable(false);
                    deleteBtn.setDisable(false);
                    createBtn.setDisable(true);
                }else{
                    switch (i) {
                        case 0:
                            mondayOpenSlider.setValue(0);
                            mondayCloseSlider.setValue(0);
                            break;
                        case 1:
                            tuesdayOpenSlider.setValue(0);
                            tuesdayCloseSlider.setValue(0);
                            break;
                        case 2:
                            wednesdayOpenSlider.setValue(0);
                            wednesdayCloseSlider.setValue(0);
                            break;
                        case 3:
                            thursdayOpenSlider.setValue(0);
                            thursdayCloseSlider.setValue(0);
                            break;
                        case 4:
                            fridayOpenSlider.setValue(0);
                            fridayCloseSlider.setValue(0);
                            break;
                        case 5:
                            saturdayOpenSlider.setValue(0);
                            saturdayCloseSlider.setValue(0);
                            break;
                        case 6:
                            sundayOpenSlider.setValue(0);
                            sundayCloseSlider.setValue(0);
                            break;
                        default:
                            break;
                    }
                    updateBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    createBtn.setDisable(false);
                }
                setHour();
            }
        }
        else{
            setScheduleLabel.setText("Select a Door and a Group");
        }
    }

    private boolean isGroupAndDoorSelected(){

        if(doorsList.getSelectionModel().getSelectedIndex()!=-1 && groupsList.getSelectionModel().getSelectedIndex()!=-1){
            return true;
        }
        else{
            return false;
        }
    }

    private String getStringHour(double value){
        return getHour( value ) + ":00";
    }

    private void createSchedule(String h_start, String h_stop, int day) throws Exception {
        Group[] groups = ListDatas.getGroups();
        String group_id = groups[groupsList.getSelectionModel().getSelectedIndex()].getId();
        Door[] doors = ListDatas.getDoors();
        String door_id = doors[doorsList.getSelectionModel().getSelectedIndex()].getId();

        JSONObject body = new JSONObject();
        body.put( "h_start", h_start);
        body.put( "h_stop", h_stop);
        body.put( "day", Integer.toString(day));
        body.put( "door_id", door_id);
        body.put( "group_id", group_id);

        String res = Api.callAPI( "POST", "schedule/", body );
        JSONObject apiReturn = new JSONObject( res );
    }


    private void createSchedules() throws Exception{
        if(isGroupAndDoorSelected() && isGoodSchedule()){
            for(int i = 0 ; i < 7 ; i++){
                String h_start = "";
                String h_stop = "";
                switch (i) {
                    case 0:
                        h_start = getStringHour(mondayOpenSlider.getValue());
                        h_stop = getStringHour(mondayCloseSlider.getValue());
                        createSchedule(h_start, h_stop, i);
                        break;
                    case 1:
                        h_start = getStringHour(tuesdayOpenSlider.getValue());
                        h_stop = getStringHour(tuesdayCloseSlider.getValue());
                        createSchedule(h_start, h_stop, i);
                        break;
                    case 2:
                        h_start = getStringHour(wednesdayOpenSlider.getValue());
                        h_stop = getStringHour(wednesdayCloseSlider.getValue());
                        createSchedule(h_start, h_stop, i);
                        break;
                    case 3:
                        h_start = getStringHour(thursdayOpenSlider.getValue());
                        h_stop = getStringHour(thursdayCloseSlider.getValue());
                        createSchedule(h_start, h_stop, i);
                        break;
                    case 4:
                        h_start = getStringHour(fridayOpenSlider.getValue());
                        h_stop = getStringHour(fridayCloseSlider.getValue());
                        createSchedule(h_start, h_stop, i);
                        break;
                    case 5:
                        h_start = getStringHour(saturdayOpenSlider.getValue());
                        h_stop = getStringHour(saturdayCloseSlider.getValue());
                        createSchedule(h_start, h_stop, i);
                        break;
                    case 6:
                        h_start = getStringHour(sundayOpenSlider.getValue());
                        h_stop = getStringHour(sundayCloseSlider.getValue());
                        createSchedule(h_start, h_stop, i);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private Schedule[] getSchedulesSelected() throws Exception {
        Schedule[] schedules = ListDatas.getSchedule();
        int nbSchedules = 0;
        int j = 0;
        String group_id = getGroup().getId();
        String door_id = getDoor().getId();

        for (int i = 0 ; i < schedules.length ; i++){
            if(schedules[i].getGroupId().equalsIgnoreCase( group_id) && schedules[i].getDoorId().equalsIgnoreCase(door_id)){
                nbSchedules++;
            }
        }
        Schedule[] schedulesInBdd = new Schedule[nbSchedules];
        for (int i = 0 ; i < schedules.length ; i++){
            if(schedules[i].getGroupId().equalsIgnoreCase( group_id) && schedules[i].getDoorId().equalsIgnoreCase(door_id)){
                schedulesInBdd[j] = schedules[i];
                j++;
            }
        }
        return schedulesInBdd;
    }

    public void deleteSchedule() throws Exception {
        if(isGroupAndDoorSelected()){
            Schedule[] schedules = getSchedulesSelected();

            if(schedules.length>0) {
                for (int i = 0; i < schedules.length; i++) {
                    JSONObject body = new JSONObject();
                    Api.callAPI( "DELETE", "schedule/"+schedules[i].getId(), body );
                }
            }
        }
    }

    public void deleteSchedules() throws Exception {
        deleteSchedule();
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Schedule deleted");
        alert.showAndWait();
    }


    public void updateSchedules() throws Exception {
        deleteSchedule();
        createSchedules();
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Schedule updated");
        alert.showAndWait();
    }

    private boolean isGoodSchedule(){
        boolean res = true;
        if(mondayOpenSlider.getValue() > mondayCloseSlider.getValue() ){
            mondayLabel.setText("Wrong Hour");
            res = false;
        }
        else{
            mondayLabel.setText(" Monday");
        }
        if(tuesdayOpenSlider.getValue() > tuesdayCloseSlider.getValue() ){
            tuesdayLabel.setText("Wrong Hour");
            res = false;
        }
        else{
            tuesdayLabel.setText(" Tuesday");
        }
        if(wednesdayOpenSlider.getValue() > wednesdayCloseSlider.getValue() ){
            wednesdayLabel.setText("Wrong Hour");
            res = false;
        }
        else{
            wednesdayLabel.setText(" Wednesday");
        }
        if(thursdayOpenSlider.getValue() > thursdayCloseSlider.getValue() ){
            thursdayLabel.setText("Wrong Hour");
            res = false;
        }
        else{
            thursdayLabel.setText(" Thursday");
        }
        if(fridayOpenSlider.getValue() > fridayCloseSlider.getValue() ){
            fridayLabel.setText("Wrong Hour");
            res = false;
        }
        else{
            fridayLabel.setText(" Friday");
        }
        if(saturdayOpenSlider.getValue() > saturdayCloseSlider.getValue() ){
            saturdayLabel.setText("Wrong Hour");
            res = false;
        }
        else{
            saturdayLabel.setText(" Saturday");
        }
        if(sundayOpenSlider.getValue() > sundayCloseSlider.getValue() ){
            sundayLabel.setText("Wrong Hour");
            res = false;
        }
        else{
            sundayLabel.setText(" Sunsday");
        }
        return res;
    }

}
