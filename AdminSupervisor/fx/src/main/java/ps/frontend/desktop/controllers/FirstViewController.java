package ps.frontend.desktop.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class FirstViewController implements Initializable {

    @FXML
    private AnchorPane mainForm;

    @FXML
    private AnchorPane homeForm;

    @FXML
    private AnchorPane usersForm;

    @FXML
    private Button usersButton;

    @FXML
    private Button supervisorsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainForm.setVisible(true);

    }
    
    public void switchPane(ActionEvent event){
        if (event.getSource() == usersButton) {

            FileChooser open = new FileChooser();
        File file = open.showOpenDialog(usersForm.getScene().getWindow());
            /*usersForm.setVisible(true);
            homeForm.setVisible(false);*/

        }
        else if (event.getSource() == supervisorsButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            homeForm.setVisible(true);

        }
    }
    
}