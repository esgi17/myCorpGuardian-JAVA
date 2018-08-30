package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PluginsController {
    @FXML AnchorPane pane;
    @FXML ListView pluginList;

    private ObservableList<String> pluginsNames = FXCollections.observableArrayList();
    private ObservableList<String> pluginsPaths = FXCollections.observableArrayList();

    public void loadPlugins () {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser ();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Textes","*.txt");
        fileChooser.getExtensionFilters().add(fileExtensions);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            pluginsPaths.add(file.toString());
            pluginsNames.add(file.getName());
            pluginList.setItems(pluginsNames);
            System.out.println(file.getName());
        }
    }

    /*
    public void install () {
        int index = pluginList.getSelectionModel().getSelectedIndex();
        if (index != -1){
            String name = pluginsNames.get(index);
            String path = pluginsPaths.get(index);
            pluginsNames.remove(index);
            pluginsPaths.remove(index);
        }
    }

    public void uninstall () {
        if (pluginList.getSelectionModel().getSelectedIndex() != -1){

        }
    }*/
}
