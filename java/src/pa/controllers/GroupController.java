package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONObject;
import pa.Models.*;

public class GroupController {

    @FXML ListView groupList;
    @FXML ListView usersList;
    @FXML Button loadGroups;
    @FXML Label usersFromGroup;

    @FXML TextField newGroupField;
    @FXML Label newGroupLabel;

    ObservableList<String> users = FXCollections.observableArrayList();
    ObservableList<String> groups = FXCollections.observableArrayList();

    @FXML AnchorPane pane;

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }

    public void openUserPage() throws Exception {
        NavHandler.openUserPage(pane);
    }

    public void openDevicePage() throws Exception {
        NavHandler.openDevicePage(pane);
    }

    public void openDoorPage() throws Exception {
        NavHandler.openDoorPage(pane);
    }

    public void openEventPage() throws Exception {
        NavHandler.openEventPage(pane);
    }

    public void initialize() throws Exception{
        fillGroupList();
    }


    Group groupSelected = new Group();
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
        groupSelected = getGroupSelected();
        User res[] = groupSelected.getUsers();
        usersFromGroup.setText("Users from "+ groupSelected.getName()+" :");
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

    public boolean isGoodGroupName() throws Exception {
        boolean res = true;
        Group groups[] = ListDatas.getGroups();
        if(newGroupField.getText().equalsIgnoreCase("")){
            res = false;
            newGroupLabel.setText("Empty Group Name");
        }
        else{
            for(int i=0 ; i< groups.length ; i++ ){
                if(groups[i].getName().equalsIgnoreCase(newGroupField.getText())){
                    res = false;
                    newGroupLabel.setText("Already exist");
                }
            }
        }
        if(res){
            newGroupLabel.setText("New Group");
            return true;
        }
        else{
            return false;
        }
    }

    public void createGroup() throws Exception {
        if(isGoodGroupName()){
            JSONObject body = new JSONObject();
            body.put( "name", newGroupField.getText() );
            Api.callAPI( "POST", "group/", body );
            fillGroupList();
        }
    }

    public void deleteGroup() throws Exception {
        System.out.println(groupList.getSelectionModel().getSelectedIndex());
        if(Integer.parseInt(groupSelected.getId()) > 0){
            JSONObject body = new JSONObject();
            Api.callAPI( "DELETE", "group/"+groupSelected.getId(), body );
            fillGroupList();
        }
    }
}
