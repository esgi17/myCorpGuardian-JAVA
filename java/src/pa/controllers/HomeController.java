package pa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONObject;
import pa.Models.Api;
import pa.Models.NavHandler;
import pa.annotations.FunctionParsor;

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

    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description = "Initiation or stop the alarm",
            lastModified = "02/07/2018",
            apiRoutes = "PUT on '/state'"
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


    @FunctionParsor(
            createdBy = "Angelo Deliessche",
            description = "Set the alarm button",
            lastModified = "02/07/2018",
            apiRoutes = "GET on '/state' "
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
