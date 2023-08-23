package ps.frontend.desktop.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import ps.frontend.desktop.App;
import ps.frontend.desktop.models.UserToLogin;


public class LoginViewController {
    
    private Stage stage;
    private Scene scene;
    
    @FXML
    private TextField emailField;

    @FXML
    private Label infoLabel;

    @FXML
    private PasswordField passwordField;

    public static String loggedToken;

    public void login(ActionEvent event){
        if(emailField.getText().isEmpty() || passwordField.getText().isEmpty())
            infoLabel.setText("Oba polja su obavezna");
        else{
            URL url;
            try {
                url = new URL(App.BASE_URL + "users/login");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                Gson gson = new Gson();
                String jsonUser =  gson.toJson(new UserToLogin(emailField.getText(), passwordField.getText()));

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(jsonUser.getBytes("UTF-8"));
                outputStream.close();

                int responseCode = conn.getResponseCode();
                if(responseCode == 200){

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    
                    reader.close();
                    loggedToken = response.toString();
                    String role = getRoleFromAuthToken(loggedToken);
                    if("ADMIN".compareTo(role) == 0){
                        Parent root = FXMLLoader.load(getClass().getResource("/ps/frontend/desktop/AdminFirstView.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else if("SUPERVISOR".compareTo(role) == 0){
                        Parent root = FXMLLoader.load(getClass().getResource("/ps/frontend/desktop/SupervisorFirstView.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
                else
                    infoLabel.setText("Kredencijali nevažeći");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String getRoleFromAuthToken(String bearerToken) {
        bearerToken = bearerToken.substring(7);
        String[] chunks = bearerToken.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String role = null;

        JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        JsonArray rolesArray = jsonObject.getAsJsonArray("roles");
        
        role = rolesArray.get(0).getAsString();

        return role;
    }
}
