package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONObject;
import pa.Models.*;
import pa.annotations.TesterInfo;

public class GroupController {

    @FXML ListView groupList;
    @FXML ListView usersList;
    @FXML Label usersFromGroup;

    @FXML TextField newGroupField;
    @FXML Label newGroupLabel;

    @FXML AnchorPane pane;

    private ObservableList<String> users = FXCollections.observableArrayList();
    private ObservableList<String> groups = FXCollections.observableArrayList();


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


    private Group groupSelected = new Group();

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    // Affiche la liste des groupes
    private Group[] fillGroupList() throws Exception {
        Group res[] = ListDatas.getGroups();
        groupList.getItems().clear();
        // Rempli le tableau de groupes
        for(int i=0 ; i< res.length ; i++ ){
            groups.add(res[i].getName());
        }
        groupList.setItems(groups);
        return res;
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    // Retourne le groupe selectionne
    private Group getGroupSelected() throws Exception {
        int groupIndex = groupList.getSelectionModel().getSelectedIndex();
        return fillGroupList()[groupIndex];
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    // Affiche la liste des users du groupe
    public User[] fillUsersList() throws Exception {
        groupSelected = getGroupSelected();
        User res[] = groupSelected.getUsers();
        usersFromGroup.setText("Users from "+ groupSelected.getName()+" ( " + res.length +" ) :");
        usersList.getItems().clear();
        // Rempli le tableau de users
        for(int i=0 ; i<res.length ; i++ ){
            users.add(userCreateLine(res[i]));
        }
        usersList.setItems(users);
        return res;
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    // Cree une ligne dans la listview de users
    private String userCreateLine(User user){
        return user.getLastname().toUpperCase() + ", " + user.getFirstname();
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    private boolean isGoodGroupName() throws Exception {
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

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public void createGroup() throws Exception {
        if(isGoodGroupName()){
            JSONObject body = new JSONObject();
            body.put( "name", newGroupField.getText() );
            Api.callAPI( "POST", "group/", body );
            fillGroupList();
            newGroupField.setText("");
            Alert alert = new Alert( Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Group created");
            alert.showAndWait();
        }
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public void deleteGroup() throws Exception {
        if(Integer.parseInt(groupSelected.getId()) > 0){
            JSONObject body = new JSONObject();
            Api.callAPI( "DELETE", "group/"+groupSelected.getId(), body );
            fillGroupList();
            Alert alert = new Alert( Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Group deleted");
            alert.showAndWait();
        }
    }
}
