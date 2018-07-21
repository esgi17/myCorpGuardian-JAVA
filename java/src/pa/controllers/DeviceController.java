package pa.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pa.Models.NavHandler;

public class DeviceController {
    @FXML AnchorPane pane;

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
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
}
