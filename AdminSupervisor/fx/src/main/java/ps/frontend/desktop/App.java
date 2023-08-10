package ps.frontend.desktop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static String BASE_URL;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("AdminFirstView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String propertiesFilePath = System.getProperty("user.dir") + "\\AdminSupervisor\\fx\\src\\main\\config.properties";
        
        try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)) {
            Properties prop = new Properties();
            prop.load(fileInputStream);
            BASE_URL = prop.getProperty("BASE_URL");
            launch(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}