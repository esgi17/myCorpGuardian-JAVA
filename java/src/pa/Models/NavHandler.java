package pa.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavHandler {
    private Stage stage;

    public void openLoginPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/login.fxml"));
        stage.setTitle("My Corp Guardian - Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void openHomePage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/home.fxml"));
        stage.setTitle("My Corp Guardian - HOME");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void openAddUserPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/addUser.fxml"));
        stage.setTitle("My Corp Guardian - Add User");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
