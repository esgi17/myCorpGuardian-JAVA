import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import pa.Models.ListDatas;
import pa.Models.NavHandler;
import pa.plugins.CameraPlugins;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;


public class Camera implements CameraPlugins {


    @FXML StackPane stack;
    @FXML ListView devicesList;
    @FXML AnchorPane pane;

    private ObservableList<String> cameras = FXCollections.observableArrayList();

    public void initialize () throws  Exception{
        fillDeviceList();
    }

    public void fillDeviceList () throws Exception{
        devicesList.getItems().clear();
        pa.Models.Camera[] cameraArray = ListDatas.getCamera();
        for (int i = 0 ; i < cameraArray.length ; i++){
            cameras.add(cameraArray[i].getUrl());
        }
        devicesList.setItems(cameras);
    }

    public void launchVideo() {
        int index = devicesList.getSelectionModel().getSelectedIndex();
        if(index != -1) {

            Media media;
            MediaPlayer mediaplayer;
            MediaView mediaview;

            final File file = new File(cameras.get(index));
            media = new Media(file.toURI().toString());
            mediaplayer = new MediaPlayer(media);
            mediaview = new MediaView(mediaplayer);
            stack.getChildren().setAll(mediaview);
            mediaview.fitWidthProperty().bind(stack.widthProperty());
            mediaview.fitHeightProperty().bind(stack.heightProperty());
            mediaplayer.play();
        }
    }


    public void print(String str) {

    }
    public void plug() {

    }
    public void unplug() {

    }
    public String getName() {
        return "Camera";
    }

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }


}

