package pa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import pa.Models.Api;
import pa.Models.NavHandler;


public class LoginController {
    @FXML TextField login;
    @FXML PasswordField password;
    @FXML AnchorPane pane;


    public void authenticate() throws Exception {
        connect();
    }

    public void connect() throws Exception {

        JSONObject body = new JSONObject();
        body.put("login", login.getText());
        body.put("password", password.getText());

        String res = Api.callAPI("POST", "admin/a", body);
        JSONObject ret = new JSONObject(res);
        System.out.println(ret.getString("success"));
    }

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }
}
