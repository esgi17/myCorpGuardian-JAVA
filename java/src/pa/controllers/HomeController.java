package pa.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import pa.Main;
import pa.Models.Api;
import pa.Models.NavHandler;
import pa.Models.State;
import pa.annotations.TesterInfo;

import java.io.IOException;

public class HomeController{

    @FXML AnchorPane pane;
    @FXML ToggleButton armBtn;


    public void openDevicePage() throws Exception {
        NavHandler.openDevicePage(pane);
    }

    public void openUserPage() throws Exception {
        NavHandler.openUserPage(pane);
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
        setArmBtn();
    }

    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    public void armedOrDisarmed() throws Exception{
        JSONObject body = new JSONObject();
        if(armBtn.getText().equalsIgnoreCase("Armed")){
            body.put("state","false");
            body.put("id","1");
            Api.callAPI("PUT", "state", body);
            armBtn.setText("DISARMED");
        }
        else{
            body.put("state","true");
            body.put("id","1");
            Api.callAPI("PUT", "state", body);
            armBtn.setText("ARMED");
        }
    }


    @TesterInfo(
            createdBy = "Rou",
            lastModified = "21/07/2018",
            apiRoutes = "POST on '/' "
    )
    private void setArmBtn() throws Exception {
        JSONObject body = new JSONObject();
        String armed = Api.callAPI("GET","state/",body);
        JSONObject json = new JSONObject(armed);
        JSONArray jArray = new JSONArray(json.getString("datas"));
        JSONObject state = jArray.getJSONObject(0);

        if(state.getString("state").equalsIgnoreCase("true")){
            armBtn.setText("ARMED");
            armBtn.setSelected(true);
        }
        else{
            armBtn.setText("DISARMED");
            armBtn.setSelected(false);
        }
    }

}
