package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;
import pa.Models.Api;
import pa.Models.Group;
import pa.Models.ListDatas;
import pa.Models.User;

public class GroupController {
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

    @FXML ListView groupList;
    @FXML ListView usersList;
    @FXML Button loadGroups;
    @FXML TextField groupNameField;
    @FXML Label head;

    ObservableList<String> users = FXCollections.observableArrayList();
    ObservableList<String> groups = FXCollections.observableArrayList();

    public void setHour(){
    }

    public String getHour(double number){
        return " ";
    }



    // Affiche la liste des groupes
    public Group[] fillGroupList() throws Exception {
        Group res[] = ListDatas.getGroups();
        groupList.getItems().clear();
        // Rempli le tableau de groupes
        for(int i=0 ; i< res.length ; i++ ){
            groups.add(res[i].getName());
        }
        groupList.setItems(groups);
        return res;
    }

    // Retourne le groupe selectionne
    public Group getGroupSelected() throws Exception {
        int groupIndex = groupList.getSelectionModel().getSelectedIndex();
        return fillGroupList()[groupIndex];
    }

    // Affiche la liste des users du groupe
    public User[] fillUsersList() throws Exception {
        Group selectedGroup = getGroupSelected();
        User res[] = selectedGroup.getUsers();
        groupNameField.setText(selectedGroup.getName());
        usersList.getItems().clear();
        // Rempli le tableau de users
        for(int i=0 ; i<res.length ; i++ ){
            users.add(userCreateLine(res[i]));
        }
        usersList.setItems(users);
        return res;
    }

    // Cree une ligne dans la listview de users
    public String userCreateLine(User user){
        return user.getLastname().toUpperCase() + ", " + user.getFirstname();
    }
}
