package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class PluginsController {
    @FXML AnchorPane pane;
    @FXML ListView pluginList;
    @FXML ListView pluginInstallList;

    private ObservableList<String> pluginsNames = FXCollections.observableArrayList();
    private ObservableList<String> pluginsPaths = FXCollections.observableArrayList();
    private ObservableList<String> pluginsInstallPaths = FXCollections.observableArrayList();
    private ObservableList<String> pluginsInstallName = FXCollections.observableArrayList();

    public void loadPlugins () {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser ();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Textes","*.txt");
        fileChooser.getExtensionFilters().add(fileExtensions);
        List<File> files = fileChooser.showOpenMultipleDialog(stage);
        if (files != null) {
            for ( int i = 0 ; i < files.size() ; i++){
                pluginsPaths.add(files.get(i).toString());
                pluginsNames.add(files.get(i).getName());
                pluginList.setItems(pluginsNames);
            }
        }
    }

    public void install () {
        Plugins pluginsInstall = new Plugins();
        pluginsInstallPaths = pluginsInstall.install(pluginsPaths);
        for(int i = 0 ; i < pluginsInstallPaths.size() ; i++){
            String path = pluginsInstallPaths.get(i);
            pluginsInstallName.add(path.substring(path.lastIndexOf('\\') + 1,path.length()));
        }
        pluginInstallList.setItems(pluginsInstallName);
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(pluginsInstallPaths.size() + " Plugins Installed");
        alert.showAndWait();
        pluginList.getItems().clear();
        pluginsNames.clear();
        pluginsPaths.clear();
    }


    public void uninstall () {
        int index = pluginInstallList.getSelectionModel().getSelectedIndex();
        if (index != -1){
            String path = pluginsInstallPaths.get(index);
            String name = pluginsInstallName.get(index);
            pluginsInstallPaths.remove(index);
            pluginsInstallName.remove(index);
            Alert alert = new Alert( Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(name + " uninstall");
            alert.showAndWait();

            Plugins pluginsUninstall = new Plugins();
            pluginsUninstall.uninstall(path);
        }
        else {
            Alert alert = new Alert( Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No Plugin selected");
            alert.showAndWait();
        }
    }
}
