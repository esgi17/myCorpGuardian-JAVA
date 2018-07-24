package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import pa.Models.*;
import pa.annotations.FunctionParsor;

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

    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description ="Fill and return all groups",
            lastModified = "13/07/2018",
            apiRoutes = {"GET on '/group' "}
    )
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

    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description ="Return the selected group",
            lastModified = "13/07/2018",
            apiRoutes = {"GET on '/group' "}
    )
    private Group getGroupSelected() throws Exception {
        int groupIndex = groupList.getSelectionModel().getSelectedIndex();
        return fillGroupList()[groupIndex];
    }

    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description ="Fill and return all users of the selected group",
            lastModified = "13/07/2018",
            apiRoutes = {"GET on '/users' "}
    )
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

    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description ="Create a line with information about a user",
            lastModified = "13/07/2018"
    )
    private String userCreateLine(User user){
        return user.getLastname().toUpperCase() + ", " + user.getFirstname();
    }

    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description ="Test the textfield",
            lastModified = "13/07/2018"
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

    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description ="Create selected group",
            lastModified = "13/07/2018",
            apiRoutes = {"POST on '/group' "}
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

    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description ="Delete selected group",
            lastModified = "13/07/2018",
            apiRoutes = {"DELETE on '/group' "}
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
