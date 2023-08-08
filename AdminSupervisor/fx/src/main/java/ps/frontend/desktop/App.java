package ps.frontend.desktop;

import java.io.File;
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

        Parent root = FXMLLoader.load(getClass().getResource("FirstView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties prop;
		prop = new Properties();
		prop.load(new FileInputStream(new File("C:\\Users\\Kezija\\Desktop\\AdminSupervisor\\fx\\src\\main\\config.properties")));
		BASE_URL = prop.getProperty("BASE_URL");
        launch(args);
    }

}