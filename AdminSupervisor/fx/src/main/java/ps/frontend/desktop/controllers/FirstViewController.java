package ps.frontend.desktop.controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ps.frontend.desktop.models.User;
import ps.frontend.desktop.models.UserTicket;

public class FirstViewController implements Initializable {

    @FXML
    private AnchorPane mainForm;

    @FXML
    private AnchorPane supervisorsForm;

    @FXML
    private AnchorPane controllersForm;

    @FXML
    private AnchorPane usersForm;

    @FXML
    private AnchorPane transactionsForm;

    @FXML
    private AnchorPane userForm;

    @FXML
    private AnchorPane transportersForm;

    @FXML
    private AnchorPane terminalsForm;

    @FXML
    private AnchorPane terminalForm;

    @FXML
    private AnchorPane requestsForm;

    @FXML
    private Button requestsButton;

    @FXML
    private Button terminalsButton;

    @FXML
    private Button transportersButton;

    @FXML
    private Button usersButton;

    @FXML
    private Button supervisorsButton;

    @FXML
    private Button controllersButton;

    @FXML
    private Button transactionsButton;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private TableColumn<User, String> creditColumn;

    @FXML
    private TableView userTicketsTable;

    @FXML
    private TableColumn ticketNameColumn;

    @FXML
    private TableColumn transporterColumn;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label userLastNameLabel;

    @FXML
    private Label creditLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainForm.setVisible(true);
        usersForm.setVisible(false);
        supervisorsForm.setVisible(false);
        transactionsForm.setVisible(false);
        controllersForm.setVisible(false);
        transportersForm.setVisible(false);
        requestsForm.setVisible(false);
        terminalsForm.setVisible(false);
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Credit"));
        this.usersTable.getItems().setAll(this.getAllUsers());
        usersTable.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User user = row.getItem();
                    showUser(user);
                }
            });
            return row;
        });
    }
    
    private void showUser(User user) {

        userForm.setVisible(true);
        userNameLabel.setText(user.getName());
        userLastNameLabel.setText(user.getLastName());
        creditLabel.setText(user.getCredit().toString());
        this.transporterColumn.setCellValueFactory(new PropertyValueFactory<UserTicket, String>("transporter"));
        this.ticketNameColumn.setCellValueFactory(new PropertyValueFactory<UserTicket, String>("Name"));

        List<UserTicket> tickets = new ArrayList<UserTicket>();

        for(int i = 0; i < 5; i++)
            tickets.add(new UserTicket("Transporter " + i, "name " + i));

        userTicketsTable.getItems().setAll(tickets);
    }

    private List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        users.add(new User("Kezic", "Dejan", "rampagedeki@gmail.com", new BigDecimal(69), 1));
        return users;
    }

    public void switchPane(ActionEvent event){

        if (event.getSource() == usersButton) {
            usersForm.setVisible(true);
            supervisorsForm.setVisible(false);
            mainForm.setVisible(false);
            userForm.setVisible(false);
            controllersForm.setVisible(false);
            transportersForm.setVisible(false);
            requestsForm.setVisible(false);
            terminalsForm.setVisible(false);
            terminalForm.setVisible(false);
        }
        else if (event.getSource() == supervisorsButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            supervisorsForm.setVisible(true);
            transactionsForm.setVisible(false);
            controllersForm.setVisible(false);
            transportersForm.setVisible(false);
            requestsForm.setVisible(false);
            terminalsForm.setVisible(false);
            terminalForm.setVisible(false);
        }
        else if (event.getSource() == transactionsButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            supervisorsForm.setVisible(false);
            transactionsForm.setVisible(true);
            controllersForm.setVisible(false);
            transportersForm.setVisible(false);
            requestsForm.setVisible(false);
            terminalsForm.setVisible(false);
            terminalForm.setVisible(false);
        }
        else if (event.getSource() == controllersButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            supervisorsForm.setVisible(false);
            transactionsForm.setVisible(false);
            controllersForm.setVisible(true);
            transportersForm.setVisible(false);
            requestsForm.setVisible(false);
            terminalsForm.setVisible(false);
            terminalForm.setVisible(false);
        }
        else if (event.getSource() == transportersButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            supervisorsForm.setVisible(false);
            transactionsForm.setVisible(false);
            controllersForm.setVisible(false);
            transportersForm.setVisible(true);
            requestsForm.setVisible(false);
            terminalsForm.setVisible(false);
            terminalForm.setVisible(false);
        }
        else if (event.getSource() == requestsButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            supervisorsForm.setVisible(false);
            transactionsForm.setVisible(false);
            controllersForm.setVisible(false);
            transportersForm.setVisible(false);
            requestsForm.setVisible(true);
            terminalsForm.setVisible(false);
            terminalForm.setVisible(false);
        }
        else if (event.getSource() == terminalsButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            supervisorsForm.setVisible(false);
            transactionsForm.setVisible(false);
            controllersForm.setVisible(false);
            transportersForm.setVisible(false);
            requestsForm.setVisible(false);
            terminalsForm.setVisible(true);
            terminalForm.setVisible(false);
        }
    }
    
}