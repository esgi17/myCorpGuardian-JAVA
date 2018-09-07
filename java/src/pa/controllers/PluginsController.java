package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pa.Models.NavHandler;
import pa.plugins.Map2DPlugins;
import pa.plugins.PluginLoader;
import pa.plugins.PluginManager;
import pa.plugins.Plugins;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginsController {
    @FXML AnchorPane pane;
    @FXML ListView pluginList;
    @FXML ListView pluginInstallList;
    @FXML Label title;
    @FXML Button enableButton;

    private ObservableList<String> pluginsNames = FXCollections.observableArrayList();
    private ObservableList<String> pluginsPaths = FXCollections.observableArrayList();
    private ObservableList<String> pluginsInstallPaths = FXCollections.observableArrayList();
    private ObservableList<String> pluginsInstallName = FXCollections.observableArrayList();

    public void initialize () {
        //charger plugins dans pluginInstallList
        ObservableList<String> tmpList = FXCollections.observableArrayList();

        tmpList.addAll(PluginManager.getInstance().getInstalledPlugins());
        pluginInstallList.setItems(tmpList);
    }

    public void loadPlugins () {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser ();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Jar","*.jar");
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
        ArrayList<String> paths = new ArrayList<String>();
        paths.addAll(pluginsPaths);
        PluginLoader pluginLoader = new PluginLoader(paths);

        ObservableList<String> tmpList = FXCollections.observableArrayList();

        tmpList.addAll(PluginManager.getInstance().getInstalledPlugins());
        pluginInstallList.setItems(tmpList);
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(tmpList.size() + " Plugins Installed");
        alert.showAndWait();
        pluginList.getItems().clear();
        pluginsNames.clear();
        pluginsPaths.clear();
    }

    public void selectedPlugin() throws IOException {
        int index = pluginInstallList.getSelectionModel().getSelectedIndex();
        if (index != -1){
            //Si t'as besoin du nom :
            String name = pluginInstallList.getSelectionModel().getSelectedItem().toString();
            if( PluginManager.getInstance().isActive(name) ) {
                enableButton.setText("Disable");
            } else {
                enableButton.setText("Enable");
            }

        }
    }

    public void enablePlugin () throws IOException {
        int index = pluginInstallList.getSelectionModel().getSelectedIndex();
        if (index != -1){
            //Si t'as besoin du nom :
            String name = pluginInstallList.getSelectionModel().getSelectedItem().toString();
            System.out.println("NAME :"+ name);
            if( PluginManager.getInstance().isActive(name) ) {
                enableButton.setText("Disable");
                PluginManager.getInstance().disablePlugin(name);
            } else {
                enableButton.setText("Enable");
                PluginManager.getInstance().enablePlugin(name);
            }
        }
        else {
            Alert alert = new Alert( Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No Plugin selected");
            alert.showAndWait();
        }
    }


    public void uninstall () throws IOException {
        int index = pluginInstallList.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            String name = pluginInstallList.getSelectionModel().getSelectedItem().toString();
            if (PluginManager.getInstance().uninstallPlugin(name)) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText(name + " uninstall");
                alert.showAndWait();
            } else {
                System.out.println("Erreur lors de la desinstallation");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No Plugin selected...");
            alert.showAndWait();
        }
        initialize();
    }

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }

    public void setPluginManagement () {
        title.setText("Plugins Management");
    }

    public void setHomeReturn () {
        title.setText("Return to Home");
    }

    public void clear () {
        pluginList.getItems().clear();
        pluginsPaths.clear();
        pluginsPaths.clear();
    }
}
