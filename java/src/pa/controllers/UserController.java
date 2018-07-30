package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import pa.Models.*;
import pa.annotations.FunctionParsor;

public class UserController {
    @FXML ListView usersList;
    @FXML ComboBox listGroup;
    @FXML TextField firstname;
    @FXML TextField lastname;
    @FXML TextField job;
    @FXML Label labelFirstname;
    @FXML Label labelLastname;
    @FXML Label head;
    @FXML Button addBtn;
    @FXML Button deleteBtn;
    @FXML Button updateBtn;

    private User userSelected= new User();

    private ObservableList<String> users = FXCollections.observableArrayList();
    private ObservableList<String> groups = FXCollections.observableArrayList();

    @FXML AnchorPane pane;

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }

    public void openDevicePage() throws Exception {
        NavHandler.openDevicePage(pane);
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

    public void initialize() throws Exception{
        fillUserList();
        fillGroupList();
    }

    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Fill and return the list of users",
            lastModified = "17/07/2018",
            apiRoutes = {"GET on '/user' "}
    )
    private User[] fillUserList() throws Exception {
        User res[] = ListDatas.getUsers();
        usersList.getItems().clear();
        // Rempli le tableau de users
        for(int i=0 ; i<res.length ; i++ ){
            users.add(userCreateLine(res[i]));
        }
        usersList.setItems(users);
        return res;
    }


    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Create a line with information about a user",
            lastModified = "17/07/2018"
    )
    private String userCreateLine(User user){
        return user.getLastname().toUpperCase() + ", " + user.getFirstname();
    }


    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Fill and return the list of groups",
            lastModified = "17/07/2018",
            apiRoutes = {"GET on '/user' "}
    )
    private void fillGroupList() throws Exception {
        listGroup.getItems().clear();
        Group[] group = ListDatas.getGroups();
        for(int i = 0 ; i <group.length ; i++){
            groups.add(group[i].getId() + " : " + group[i].getName());
        }
        listGroup.setItems(groups);
    }


    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Test the textfield",
            lastModified = "17/07/2018"
    )
    private boolean stringVerification(){
        boolean res = true;
        if (firstname.getText().equalsIgnoreCase( "" )) {
            labelFirstname.setText("Empty Firstname");
            labelLastname.setStyle("-fx-color:red");
            res = false;
        } else{
            labelFirstname.setText("Firstname");
        }

        if (lastname.getText().equalsIgnoreCase( "" )) {
            labelLastname.setText( "Empty Lastname" );
            labelLastname.setStyle("-fx-color:red");
            res = false;
        }else{
            labelLastname.setText("Lastname");
        }

        for(int i=0 ; i < firstname.getText().length() ; i++){
            char car = firstname.getText().charAt(i);
            if(!Character.isLetter(car)){
                labelFirstname.setText("Incorrect character in Firstname");
                labelLastname.setStyle("-fx-color:red");
                res = false;
            }
        }

        for(int i=0 ; i < lastname.getText().length() ; i++){
            char car = lastname.getText().charAt(i);
            if(!Character.isLetter(car)){
                labelLastname.setText("Incorrect character in Lastname");
                labelLastname.setStyle("-fx-color:red");
                res = false;
            }
        }
        if(res){
            labelLastname.setText("Lastname");
            labelFirstname.setText("Firstname");
        }
        return res;
    }

    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Post or update a user",
            lastModified = "17/07/2018",
            apiRoutes = {"POST on '/user' ", "PUT on '/user' "}
    )
    private void addOrUpdateUser(String method, String id) throws Exception {
        // Verif si champ vide
        if (stringVerification()){
            //Creation json user
            JSONObject body = new JSONObject();
            body.put( "firstname", firstname.getText() );
            body.put( "lastname", lastname.getText() );
            body.put( "job", job.getText() );

            //Si c'est update on passe l'id
            if(method.equalsIgnoreCase("PUT")){
                Alert alert = new Alert( Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("User updated");
                alert.showAndWait();
                body.put("id", id);
            }
            else {
                Alert alert = new Alert( Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("User created");
                alert.showAndWait();
            }
            // Recuperation de l'id du groupe
            if (listGroup.getValue() != null) {
                String groupId = listGroup.getValue().toString().substring( 0, listGroup.getValue().toString().indexOf( " " ) );
                body.put( "group_id", groupId );
            } else {
                body.put( "group_id", "1" );
            }
            String res = Api.callAPI( method, "user/", body );
            JSONObject apiReturn = new JSONObject( res );
            //Raz champs
            fillUserList();
            createForm();
        }
    }

    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Add a user",
            lastModified = "17/07/2018",
            apiRoutes = "POST on '/user' "
    )
    public void addUser() throws Exception {
        JSONObject empty = new JSONObject();
        if(!Api.callAPI("GET","group/",empty).equalsIgnoreCase("")) {
            addOrUpdateUser("POST","0");
        }
        else{
            head.setText("Create a group first");
        }
    }

    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Change the added form in updated form",
            lastModified = "17/07/2018"
    )
    public void updateForm() throws Exception {
        head.setText("Update User");
        deleteBtn.setDisable(false);
        addBtn.setDisable(true);
        updateBtn.setDisable(false);
        User user = getUserSelected();
        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        job.setText(user.getJob());
        listGroup.getSelectionModel().select(getGroup(userSelected.getIdGroup()));
    }

    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Return selected group",
            lastModified = "17/07/2018",
            apiRoutes = "POST on '/user' "
    )
    public int getGroup(String group_id){
        int res = 0;
        for (int i = 0 ; i<groups.size() ; i++){
            if(group_id.equalsIgnoreCase(groups.get(i).substring(0,1))){
                res = i;
            }
        }
        return res;
    }
    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Change the updated form in added form",
            lastModified = "17/07/2018"
    )
    public void createForm() {
        head.setText("New User");
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        labelFirstname.setText("Firstname");
        labelLastname.setText( "Lastname");
        addBtn.setDisable(false);
        firstname.setText("");
        lastname.setText("");
        job.setText("");
        listGroup.getSelectionModel().clearSelection();
    }

    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Update a user",
            lastModified = "17/07/2018",
            apiRoutes = "PUT on '/user' "
    )
    public void updateUser() throws Exception {
        fillUserList();
        String id = userSelected.getId();
        addOrUpdateUser("PUT", id);
    }

    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Return selected user",
            lastModified = "17/07/2018",
            apiRoutes = "GET on '/user' "
    )
    private User getUserSelected() throws Exception {
        int userIndex = usersList.getSelectionModel().getSelectedIndex();
        userSelected = fillUserList()[userIndex];
        return userSelected;
    }


    @FunctionParsor(
            createdBy = "Robin Tersou",
            description ="Delete a user",
            lastModified = "17/07/2018",
            apiRoutes = "DELETE on '/user' "
    )
    public void deleteUser() throws Exception {
        JSONObject body = new JSONObject();
        body.put( "id", userSelected.getId());
        Api.callAPI( "DELETE", "user/" + userSelected.getId(), body );
        fillUserList();
        createForm();
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("User deleted");
        alert.showAndWait();
    }
}
