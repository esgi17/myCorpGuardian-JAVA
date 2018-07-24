package pa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import pa.Models.Api;
import pa.Models.NavHandler;
import pa.annotations.FunctionParsor;

public class LoginController {
    @FXML TextField login;
    @FXML PasswordField password;
    @FXML AnchorPane pane;


    @FunctionParsor(
            createdBy = "Robin Tersou",
            description = "Allow the authentication",
            lastModified = "21/07/2018"
    )
    public void authenticate() throws Exception {
        connect();
    }

    @FunctionParsor(
            createdBy = "Robin Tersou",
            description = "Set the token by connecting",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public void connect() throws Exception {

        JSONObject body = new JSONObject();
        body.put( "login", login.getText() );
        body.put( "password", password.getText() );

        String res = Api.callAPI( "POST", "", body );
        if(!res.equalsIgnoreCase("")){
            JSONObject ret = new JSONObject( res );
            Api.setToken( ret.getString( "token" ) );
            if (ret.getString( "success" ) == "true") {
                openHomePage();
            }
        }
    }

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }
}
