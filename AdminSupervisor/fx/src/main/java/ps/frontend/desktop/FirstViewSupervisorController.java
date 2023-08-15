package ps.frontend.desktop;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ps.frontend.desktop.models.User;
import ps.frontend.desktop.models.UserTicket;

public class FirstViewSupervisorController implements Initializable{

    @FXML
    private Button closeButton;

    @FXML
    private Button controllersButton;

    @FXML
    private TableColumn creditCollum;

    @FXML
    private Label creditLabel;

    @FXML
    private AnchorPane initialPane;

    @FXML
    private TableColumn mailCollum;

    @FXML
    private TextField mailField;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TableColumn nameCollum;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private Button requestsButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn surnameCollum;

    @FXML
    private TextField surnameField;

    @FXML
    private Label surnameLabel;

    @FXML
    private Button terminalsButton;

    @FXML
    private TableColumn ticketNameCollum;

    @FXML
    private Button transactionsButton;

    @FXML
    private Button transport_linesButton;

    @FXML
    private TableColumn transporterCollum;

    @FXML
    private TableView userTicketsTable;

    @FXML
    private Button usersButton;

    @FXML
    private AnchorPane usersForm;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private AnchorPane userForm;

    @FXML
    private Button nextButton;

    @FXML
    private AnchorPane transactionPane;

    @FXML
    private Button previousButton;

    @FXML
    private TextField transSearchTerminal;

    @FXML
    private TextField transSearchTime;

    @FXML
    private TextField transSearchUser;

    @FXML
    private TableColumn transactionCostColumn;

    @FXML
    private TableColumn transactionEmailColumn;

    @FXML
    private Button transactionSearchButton;

    @FXML
    private TableColumn transactionTerminalidColumn;

    @FXML
    private TableColumn transactionTimeColumn;

    @FXML
    private TableView transactionsTable;

    @FXML
    private CheckBox controlerActiveCheckBox;

    @FXML
    private CheckBox controlerInactiveCheckBox;

    @FXML
    private TableColumn controlerJMBColumn;

    @FXML
    private TableColumn controlerNameColumn;

    @FXML
    private TableColumn controlerPinColumn;

    @FXML
    private TableColumn controlerStatusColumn;

    @FXML
    private TableColumn controlerSurnameColumn;

    @FXML
    private TableView controlerTableView;

    @FXML
    private Button controlersNextPageButton;

    @FXML
    private AnchorPane controlersPane;

    @FXML
    private Button controlersPrevPageButton;

    @FXML
    private TextField controlerRegistrationJMB;

    @FXML
    private TextField controlerRegistrationName;

    @FXML
    private TextField controlerRegistrationPin;

    @FXML
    private TextField controlerRegistrationSurname;



    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainForm.setVisible(true);
        usersForm.setVisible(false);
        transactionPane.setVisible(false);
        controlersPane.setVisible(false);
        userForm.setVisible(false);
        nameCollum.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
        surnameCollum.setCellValueFactory(new PropertyValueFactory<User, String>("Surname"));
        mailCollum.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        creditCollum.setCellValueFactory(new PropertyValueFactory<User, String>("Credit"));
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
        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getLastName());
        creditLabel.setText(user.getCredit().toString());
        this.ticketNameCollum.setCellValueFactory(new PropertyValueFactory<UserTicket, String>("Name"));
        this.transporterCollum.setCellValueFactory(new PropertyValueFactory<UserTicket, String>("Transporter"));

        List<UserTicket> tickets = new ArrayList<UserTicket>();

        for(int i = 0; i < 3; i++)
            tickets.add(new UserTicket("Transporter " + i, "name " + i));

        userTicketsTable.getItems().setAll(tickets);
    }

    private List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        users.add(new User("Marko", "Markovic", "markomarkovic@gmail.com", new BigDecimal(420), 1));
        return users;
    }

    @FXML
    void switchPane(ActionEvent event) {

        if (event.getSource() == usersButton) {
            usersForm.setVisible(true);
            transactionPane.setVisible(false);
            initialPane.setVisible(false);
            controlersPane.setVisible(false);
        }

        else if(event.getSource() == transactionsButton) {
            transactionPane.setVisible(true);
            initialPane.setVisible(false);
            usersForm.setVisible(false);
            userForm.setVisible(false);
            controlersPane.setVisible(false);
        }

        else if(event.getSource() == controllersButton) {
            controlersPane.setVisible(true);
            transactionPane.setVisible(false);
            initialPane.setVisible(false);
            usersForm.setVisible(false);
            userForm.setVisible(false);
        } 
    }

}
