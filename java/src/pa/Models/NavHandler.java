package pa.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pa.Main;

import java.io.IOException;

public abstract class NavHandler {

    public static void openHomePage(AnchorPane pane) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( Main.class.getResource("View/home.fxml"));
        Main.primaryStage.setTitle("My Corp Guardian - Home");
        pane = (AnchorPane) loader.load();
        Scene scene = new Scene(pane);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    public static void openDevicePage(AnchorPane pane) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/device.fxml"));
        Main.primaryStage.setTitle("My Corp Guardian - Device");
        pane = (AnchorPane) loader.load();
        Scene scene = new Scene(pane);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    public static void openUserPage(AnchorPane pane) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/user.fxml"));
        Main.primaryStage.setTitle("My Corp Guardian - User");
        pane = (AnchorPane) loader.load();
        Scene scene = new Scene(pane);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    public static void openGroupPage(AnchorPane pane) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/group.fxml"));
        Main.primaryStage.setTitle("My Corp Guardian - Group");
        pane = (AnchorPane) loader.load();
        Scene scene = new Scene(pane);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
    public static void openDoorPage(AnchorPane pane) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/door.fxml"));
        Main.primaryStage.setTitle("My Corp Guardian - Door");
        pane = (AnchorPane) loader.load();
        Scene scene = new Scene(pane);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    public static void openEventPage(AnchorPane pane) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/event.fxml"));
        Main.primaryStage.setTitle("My Corp Guardian - Event");
        pane = (AnchorPane) loader.load();
        Scene scene = new Scene(pane);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
    public static void openLoginPage(AnchorPane pane) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("View/login.fxml"));
        Main.primaryStage.setTitle("My Corp Guardian - Login");
        pane = (AnchorPane) loader.load();
        Scene scene = new Scene(pane);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}
