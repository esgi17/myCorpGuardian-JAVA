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

    User userSelected= new User();

    ObservableList<String> users = FXCollections.observableArrayList();
    ObservableList<String> groups = FXCollections.observableArrayList();

    // Affiche la liste des users
    public User[] fillUserList() throws Exception {
        User res[] = getUsers();
        usersList.getItems().clear();
        // Rempli le tableau de users
        for(int i=0 ; i<res.length ; i++ ){
            users.add(userCreateLine(res[i]));
        }
        usersList.setItems(users);
        return res;
    }

    // Retourne un tableau de users avec tout les users
    public User[] getUsers() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "user/", empty);
        JSONObject datas = new JSONObject(json);
        JSONArray jArray = new JSONArray(datas.getString("datas"));
        User[] users = ListDatas.createUsersArray(jArray);
        return users;
    }

    // Renvoie le nom de groupe avec l'id en parametre
    public String getGroupName(String id) throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String str = Api.callAPI("GET", "group/" + id, empty);
        JSONArray jArray = new JSONArray(str);
        JSONObject res = jArray.getJSONObject(0);

        return res.getString("name");
    }

    // Cree une ligne dans la listview de users
    public String userCreateLine(User user){
        return user.getLastname().toUpperCase() + ", " + user.getFirstname();
    }


    //Rempli la combobox avec tout les groupes
    public void fillGroupList() throws Exception {
        listGroup.getItems().clear();
        Group[] group = ListDatas.getGroups();
        for(int i = 0 ; i <group.length ; i++){
            groups.add(group[i].getId() + " : " + group[i].getName());
        }
        listGroup.setItems(groups);
    }

    // Retourne un tableau de tout les groupes
    public String[] getGroups() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "group/", empty);
        JSONArray jArray = new JSONArray(json);
        String res[] = new String[jArray.length()];

        //Met les noms de groupe dans un tableau de String
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject group = jArray.getJSONObject( i );
            res[i] = group.getString("id") + " : " + group.getString("name");
        }

        return res;
    }

    // Verif sur un chaine de caractere
    public boolean stringVerification(){
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

    // Execute requete add ou update d'un user
    public boolean addOrUpdateUser(String method, String id) throws Exception {
        // Verif si champ vide
        if (!stringVerification()){
            return false;
        } else {

            //Creation json user
            JSONObject body = new JSONObject();
            body.put( "firstname", firstname.getText() );
            body.put( "lastname", lastname.getText() );
            body.put( "job", job.getText() );

            //Si c'est update on passe l'id
            if(method.equalsIgnoreCase("PUT")){
                body.put("id", id);
            }
            // Recuperation de l'id du groupe
            if (listGroup.getValue() != null) {
                String groupId = listGroup.getValue().toString().substring( 0, listGroup.getValue().toString().indexOf( " " ) );
                body.put( "group_id", groupId );
            } else {
                body.put( "group_id", "0" );
            }
            String res = Api.callAPI( method, "user/", body );
            JSONObject apiReturn = new JSONObject( res );

            //Raz champs
            fillUserList();
            createForm();

            if (apiReturn.getString( "success" ) == "true") {
                return true;
            } else {
                System.out.println( apiReturn.toString() );
                return false;
            }
        }
    }

    // Connecté au bouton add user
    public void addUser() throws Exception {
        addOrUpdateUser("POST","0");
    }

    // Formulaire en mode update
    public void updateForm() throws Exception {
        head.setText("Update User");
        deleteBtn.setVisible(true);
        addBtn.setVisible(false);
        updateBtn.setVisible(true);
        User user = getUserSelected();
        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        job.setText(user.getJob());
        listGroup.getSelectionModel().select(Integer.parseInt(userSelected.getIdGroup()));
    }

    // Formulaire en mode create
    public void createForm() {
        head.setText("New User");
        deleteBtn.setVisible(false);
        updateBtn.setVisible(false);
        labelFirstname.setText("Firstname");
        labelLastname.setText( "Lastname");
        addBtn.setVisible(true);
        firstname.setText("");
        lastname.setText("");
        job.setText("");
        listGroup.getSelectionModel().clearSelection();
    }

    //Modifie user
    public void updateUser() throws Exception {
        fillUserList();
        String id = userSelected.getId();
        addOrUpdateUser("PUT", id);
    }

    // Retourne le user selectionne
    public User getUserSelected() throws Exception {
        int userIndex = usersList.getSelectionModel().getSelectedIndex();
        userSelected = fillUserList()[userIndex];
        return userSelected;
    }


    // Supprimer user
    public void deleteUser() throws Exception {
        JSONObject body = new JSONObject();
        body.put( "id", userSelected.getId());
        Api.callAPI( "DELETE", "user/", body );
        fillUserList();
    }
}
