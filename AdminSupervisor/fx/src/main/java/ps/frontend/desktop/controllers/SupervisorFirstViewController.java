package ps.frontend.desktop.controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ps.frontend.desktop.models.Driver;
import ps.frontend.desktop.models.User;
import ps.frontend.desktop.models.UserTicket;

public class SupervisorFirstViewController implements Initializable{

    @FXML
    private Button closeButton;

    @FXML
    private Button driversButton;

    @FXML
    private TableColumn creditCollum;

    @FXML
    private Label creditLabel;

    @FXML
    private TextField usersCreditAddTextField;

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
    private Button terminalRequestButton;

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
    private Button userCreditPrevButton;

    @FXML
    private Button userCreditNextButton;

    @FXML
    private Button userCreditAddCreditButton;

    @FXML
    private ImageView userCreditUserPicture;

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
    private TextField transSearchFromTime;

    @FXML
    private TextField transSearchToTime;

    @FXML
    private TextField transSearchSupervisorId;

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
    private TableColumn driverJMBColumn;

    @FXML
    private TableColumn driverNameColumn;

    @FXML
    private TableColumn driverPinColumn;

    @FXML
    private TableColumn driverStatusColumn;

    @FXML
    private TableColumn driverSurnameColumn;

    @FXML
    private TableColumn driverSupervisorIDColumn;

    @FXML
    private TableView driverTableView;

    @FXML
    private Button driversNextPageButton;

    @FXML
    private AnchorPane driversPane;

    @FXML
    private Button driversPrevPageButton;

    @FXML
    private TextField driverRegistrationJMB;

    @FXML
    private TextField driverRegistrationName;

    @FXML
    private TextField driverRegistrationPin;

    @FXML
    private TextField driverRegistrationSurname;

    @FXML
    private TextField driverRegistrationSupervisorID;

    @FXML
    private CheckBox driverActiveCheckBox;

    @FXML
    private CheckBox driverInactiveCheckBox;

    @FXML
    private AnchorPane driverRegistrationPane;

    @FXML
    private Button driverStatusChangeAcceptButton;

    @FXML
    private ChoiceBox driverStatusChangeChoiceBox;

    @FXML
    private Label driverStatusJMBLabel;

    @FXML
    private Label driverStatusNameLabel;

    @FXML
    private AnchorPane driverStatusPane;

    @FXML
    private Label driverStatusPinLabel;

    @FXML
    private Label driverStatusStatusLabel;

    @FXML
    private Label driverStatusSupervisorIDLabel;

    @FXML
    private Label driverStatusSurnameLabel;

    @FXML
    private AnchorPane requestsPane;

    @FXML
    private Button requestsTicketRequestsNextPageButton;

    @FXML
    private Button requestsTicketRequestsPrevPageButton;

    @FXML
    private Button requestsTicketRequestsSupervisorIDSearchButton;

    @FXML
    private TextField requestsTicketRequestsSupervisorIDTextField;

    @FXML
    private TableView requestsTicketRequestsTableView;

    @FXML
    private TableColumn requestsTicketRequestsTicketType;

    @FXML
    private TableColumn requestsTicketRequestsUserName;

    @FXML
    private TableColumn requestsTicketRequestsUserSurname;

    @FXML
    private TableColumn requestsTicketRequestsUserID;

    @FXML
    private Button requestsTicketResponseAcceptButton;

    @FXML
    private Button requestsTicketResponseNextPageButton;

    @FXML
    private AnchorPane requestsTicketResponsePane;

    @FXML
    private Button requestsTicketResponsePrevPageButton;

    @FXML
    private Button requestsTicketResponseRejectButton;

    @FXML
    private Button requestsTicketResponseSearchButton;

    @FXML
    private TableColumn requestsTicketResponseUserName;

    @FXML
    private TableColumn requestsTicketResponseUserSurname;

    @FXML
    private TextField requestsTicketResponseSupervisorID;

    @FXML
    private TableView requestsTicketResponseTableView;

    @FXML
    private TableColumn requestsTicketResponseTicketStatus;

    @FXML
    private TableColumn requestsTicketResponseTicketType;

    @FXML
    private Label requestsTicketProcessingTicketTypeLabel;

    @FXML
    private TableColumn requestsTicketResponseUserID;

    @FXML
    private AnchorPane requestsTicketProcessingPane;

    @FXML
    private Label requestsTicketProcessingUserIDLabel;

    @FXML
    private Label requestsTicketProcessingUserJMBLabel;

    @FXML
    private Label requestsTicketProcessingUserNameLabel;

    @FXML
    private Label requestsTicketProcessingUserSurnameLabel;

    @FXML
    private Hyperlink requestsTicketProcessingDocumentsHyperlink;

    @FXML
    private ImageView requestsTicketProcessingUserPictureBox;

    @FXML
    private Button terminalRequestSendButton;

    @FXML
    private TextField terminalTerminalIDTextField;

    @FXML
    private TextField terminalTransporterIDTextField;
    

    //ps.frontend.desktop.controllers.SupervisorFirstViewController


    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainForm.setVisible(true);
        usersForm.setVisible(false);
        transactionPane.setVisible(false);
        driversPane.setVisible(false);
        userForm.setVisible(false);
        driversPane.setVisible(false);
        driverRegistrationPane.setVisible(false);
        driverStatusPane.setVisible(false);
        requestsPane.setVisible(false);
        requestsTicketResponsePane.setVisible(false);
        requestsTicketProcessingPane.setVisible(false);

        driverStatusChangeChoiceBox.setItems(FXCollections.observableArrayList("Aktivan", "Neaktivan"));

        nameCollum.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
        surnameCollum.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
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

        driverNameColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("Name"));
        driverSurnameColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("LastName"));
        driverJMBColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("JMB"));
        driverPinColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("Pin"));
        driverStatusColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("Status"));
        driverSupervisorIDColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("SupervisorID"));
        this.driverTableView.getItems().setAll(this.getAllDrivers());
        driverTableView.setRowFactory(tv -> {
            TableRow<Driver> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())) {
                    driverRegistrationPane.setVisible(false);
                    Driver driver = row.getItem();
                    showDriver(driver);
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

    private void showDriver(Driver driver) {
        driverStatusPane.setVisible(true);
        driverStatusNameLabel.setText(driver.getName());
        driverStatusSurnameLabel.setText(driver.getLastName());
        driverStatusJMBLabel.setText(Integer.toString(driver.getJMB()));
        driverStatusPinLabel.setText(Integer.toString(driver.getPin()));
        driverStatusStatusLabel.setText(driver.getStatus());
        driverStatusSupervisorIDLabel.setText(Integer.toString(driver.getSupervisorID()));
    }

    private List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        users.add(new User("Marko", "Markovic", "markomarkovic@gmail.com", new BigDecimal(420), 1));
        return users;
    }

    private List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("Simo", "Simic", 2, 193724, 1, "Aktivan"));
        return drivers;
    }

    @FXML
    void switchPane(ActionEvent event) {

        if (event.getSource() == usersButton) {
            usersForm.setVisible(true);
            transactionPane.setVisible(false);
            initialPane.setVisible(false);
            driversPane.setVisible(false);
            driverRegistrationPane.setVisible(false);
            driverStatusPane.setVisible(false);
            requestsPane.setVisible(false);
            requestsTicketResponsePane.setVisible(false);
            requestsTicketProcessingPane.setVisible(false);
        }

        else if(event.getSource() == transactionsButton) {
            transactionPane.setVisible(true);
            initialPane.setVisible(false);
            usersForm.setVisible(false);
            userForm.setVisible(false);
            driversPane.setVisible(false);
            driverRegistrationPane.setVisible(false);
            driverStatusPane.setVisible(false);
            requestsPane.setVisible(false);
            requestsTicketResponsePane.setVisible(false);
            requestsTicketProcessingPane.setVisible(false);
        }

        else if(event.getSource() == driversButton) {
            driversPane.setVisible(true);
            transactionPane.setVisible(false);
            initialPane.setVisible(false);
            usersForm.setVisible(false);
            userForm.setVisible(false);
            driverRegistrationPane.setVisible(true);
            driverStatusPane.setVisible(false);
            requestsPane.setVisible(false);
            requestsTicketResponsePane.setVisible(false);
            requestsTicketProcessingPane.setVisible(false);
        }

        else if(event.getSource() == requestsButton) {
            requestsPane.setVisible(true);
            requestsTicketResponsePane.setVisible(true);
            requestsTicketProcessingPane.setVisible(false); //za requestsTicketRequestTableView (event.getClickCount() == 2)    ==> ovo je true


            driversPane.setVisible(false);
            transactionPane.setVisible(false);
            initialPane.setVisible(false);
            usersForm.setVisible(false);
            userForm.setVisible(false);
            driverRegistrationPane.setVisible(false);
            driverStatusPane.setVisible(false);
        }
    }
}
