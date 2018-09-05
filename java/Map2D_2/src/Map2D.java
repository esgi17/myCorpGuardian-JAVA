import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pa.plugins.Map2DPlugins;
import pa.plugins.Plugins;

public class Map2D implements Map2DPlugins {
    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
*/

    public static void main(String[] args) {
        //launch(args);
    }

    public void print(String str) {

    }

    public void plug() {
        System.out.println("Map2D plugged");
    }

    public void unplug() {
        System.out.println("Map2D unPlugged");
    }

    public String getName() {
        return "Map2D";
    }
}


