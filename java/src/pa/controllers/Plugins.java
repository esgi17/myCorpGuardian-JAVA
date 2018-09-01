package pa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Plugins {

    public ObservableList install (ObservableList<String> pluginsToInstall) {
        ObservableList<String> plugins = FXCollections.observableArrayList();
        plugins.addAll(pluginsToInstall);
        return plugins;
    }

    public void uninstall (String pluginsToUninstall) {

    }
}
