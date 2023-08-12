package ps.frontend.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class FirstViewSupervisorController {

    @FXML
    private Button close;

    @FXML
    private Button controlers;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button requests;

    @FXML
    private Button terminals;

    @FXML
    private Button transactions;

    @FXML
    private Button transport_lines;

    @FXML
    private Button users;

    public void close() {
        System.exit(0);
    }

    

    

}
