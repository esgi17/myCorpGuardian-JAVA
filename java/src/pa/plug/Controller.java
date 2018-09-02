package pa.plug;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import pa.Models.Camera;
import pa.Models.Device;
import pa.Models.ListDatas;
import pa.Models.Pass;

import java.io.File;

public class Controller {
    @FXML StackPane stack;
    @FXML ListView devicesList;

    private ObservableList<String> cameras = FXCollections.observableArrayList();

    public void initialize () throws  Exception{
        fillDeviceList();
    }

    public Camera[] fillDeviceList () throws Exception {
        int k = 0;
        devicesList.getItems().clear();
        Camera[] cameraArray = ListDatas.getCamera();
        Camera[] camerasReturn = new Camera[cameraArray.length];
        for (int i = 0 ; i < cameraArray.length ; i++){
            cameras.add(cameraArray[i].getUrl());
        }
        devicesList.setItems(cameras);
        return camerasReturn;
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
}