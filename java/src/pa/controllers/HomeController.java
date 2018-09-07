package pa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import org.json.JSONArray;
import org.json.JSONObject;
import pa.Models.Api;
import pa.Models.NavHandler;
import pa.annotations.FunctionParsor;
import pa.plugins.CameraPlugins;
import pa.plugins.Map2DPlugins;
import pa.plugins.PluginLoader;
import pa.plugins.PluginManager;

public class HomeController{

    @FXML AnchorPane pane;
    @FXML ToggleButton armBtn;
    @FXML Label mapLabel;
    @FXML Button cameraButton;


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

    public void openPluginsPage() throws Exception {
        NavHandler.openPluginsPage(pane);
    }

    public void openCameraPage() throws Exception {
        if( PluginManager.getInstance().isActive("CameraPlugin")) {
            CameraPlugins[] camera = PluginManager.getInstance().getCameraPluginsList().toArray(new CameraPlugins[0]);
            System.out.println(camera.length);
            if( camera.length > 0 ) {
                NavHandler.openCameraPage(pane);
            }
        }
    }

    public void openMapPage() throws Exception {
        if( PluginManager.getInstance().isActive("Map2DPlugin")) {
            Map2DPlugins[] map2D = PluginManager.getInstance().getMap2DPluginsList().toArray(new Map2DPlugins[0]);
            System.out.println(map2D.length);
            if( map2D.length > 0 ) {
                System.out.println(map2D[0].getName());
                NavHandler.openMapPage(pane);
            }
        }
    }

    private boolean initPlugins() throws Exception {
        try {
            PluginLoader pluginLoader = new PluginLoader();
            return true;
        } catch( Exception e ) {
            e.printStackTrace();
            return false;
        }

    }

    public void initialize() throws Exception{
        if( initPlugins() ) {
            Map2DPlugins[] map2D = PluginManager.getInstance().getMap2DPluginsList().toArray(new Map2DPlugins[0]);
            if( map2D.length > 0 ) {
                System.out.println(map2D[0].getName());
            }
            CameraPlugins[] camera = PluginManager.getInstance().getCameraPluginsList().toArray(new CameraPlugins[0]);
            if( camera.length > 0 ) {
                System.out.println(camera[0].getName());
            }
        }else {
            System.out.println( "ERROOOOR" );
        }
        if( PluginManager.getInstance().isActive("Map2DPlugin")){
            mapLabel.setVisible(true);
        }
        if( PluginManager.getInstance().isActive("CameraPlugin")){
            cameraButton.setVisible(true);
        }
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
        if(!armed.equalsIgnoreCase("")){

            JSONObject json = new JSONObject(armed);
            System.out.println(json);
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

}
