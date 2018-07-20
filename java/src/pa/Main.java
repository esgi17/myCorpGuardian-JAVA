package pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static pa.Models.Api.checkToken;
import static pa.Models.Api.getToken;


public class Main extends Application {
    private Stage primaryStage;

    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("My Corp Guardian");

        initApp();
    }

    private void initApp() throws IOException {
        System.out.println("App initializing..."); // LOG
        if( checkLogin() ) {
            openGroupPage();

        } else {
            //openDoorPage();
            openGroupPage();
            //openUserPage();
            //openHomePage();
            //openAddUserPage();
            //openLoginPage();
        }
    }


    private boolean checkLogin() {
        if( getToken() == null || getToken().isEmpty() ) {
            System.out.println("No token provided...");
            return false;
        } else {
            if( !checkToken() ) {
                System.out.println("Bad token provided...");
                return false;
            }
        }
        System.out.println("Token ok! ");
        return true;

    }

    public static void main(String[] args) {
        launch(args);
    }


    public void openLoginPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/login.fxml"));
        primaryStage.setTitle("My Corp Guardian - Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void openUserPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/user.fxml"));
        primaryStage.setTitle("My Corp Guardian - USER");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void openGroupPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/group.fxml"));
        primaryStage.setTitle("My Corp Guardian - GROUP");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public void openDoorPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/door.fxml"));
        primaryStage.setTitle("My Corp Guardian - DOOR");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
