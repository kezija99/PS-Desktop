package ps.frontend.desktop.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import ps.frontend.desktop.App;
import ps.frontend.desktop.models.Controller;
import ps.frontend.desktop.models.Route;
import ps.frontend.desktop.models.RouteHistory;
import ps.frontend.desktop.models.ScanInterraction;
import ps.frontend.desktop.models.Supervisor;
import ps.frontend.desktop.models.SupervisorWithPassword;
import ps.frontend.desktop.models.Terminal;
import ps.frontend.desktop.models.TerminalActivationRequest;
import ps.frontend.desktop.models.TicketType;
import ps.frontend.desktop.models.TicketTypeRequest;
import ps.frontend.desktop.models.Transaction;
import ps.frontend.desktop.models.Transporter;
import ps.frontend.desktop.models.User;
import ps.frontend.desktop.models.UserTicket;

public class AdminFirstViewController implements Initializable {

    private Stage stage;
    private Scene scene;

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
    private AnchorPane requestsForm, ticketsForm, routesForm;

    @FXML
    private Button requestsButton, ticketsButton, routesButton;

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
    private TableView supervisorsTable, controllersTable, ticketsTable;

    @FXML
    private TableColumn controllersNameColumn, controllersLastNameColumn, controllersJMBColumn, controllersPinColumn, controllersStatusColumn;

    @FXML
    private TableColumn supervisorNameColumn, ticketsNameColumn, ticketsPriceColumn, ticketsAmountColumn, ticketsDurationColumn, ticketsStatusColumn;

    @FXML
    private TableColumn supervisorLastNameColumn;

    @FXML
    private TableColumn supervisorEmailColumn;

    @FXML
    private TableColumn supervisorTransporterColumn;

    @FXML
    private TableColumn supervisorStatusColumn;

    @FXML
    private TableView transactionsTable;

    @FXML
    private TableColumn transactionsEmailColumn;

    @FXML
    private TableColumn transactionsValueColumn;

    @FXML
    private TableColumn transactionsTimestampColumn;

    @FXML
    private TableColumn transactionsTerminalColumn;

    @FXML
    private TableColumn transactionsSupervisorColumn;

    @FXML
    private TableView userTicketsTable, terminalsTable;

    @FXML
    private TableColumn terminalIdColumn, terminalTransporterColumn, terminalStatusColumn;

    @FXML
    private TableView transportersTable, routesTable;

    @FXML
    private TableView terminalRequestTable, scanInterractionsTable, routeHistoryTable;

    @FXML
    private TableColumn requestSerialColumn, requestTransporterColumn, requestStatusColumn;

    @FXML
    private TableColumn transporterIdColumn, scanTerminalColumn, scanUserColumn, scanRouteColumn, scanTimeColumn;

    @FXML
    private TableColumn transporterNameColumn, routeHistoryStartTimeColumn, routeHistoryRouteColumn, routeHistoryDriverColumn, routeHistoryEndTimeColumn;

    @FXML
    private TableColumn ticketNameColumn, routesIdColumn, routesTransporterColumn, routesNameColumn, routesStatusColumn;

    @FXML
    private TableColumn usageColumn;

    @FXML
    private TableColumn validUntilColumn;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label userLastNameLabel;

    @FXML
    private Label creditLabel;

    @FXML
    private Label usersInfoLabel;

    @FXML
    private TextField userFirstNameField;

    @FXML
    private TextField controllersNameField, controllersLastNameField, controllersPinField, controllersJMBField;

    @FXML
    private TextField userLastNameField;

    @FXML
    private TextField userEmailField;

    @FXML
    private TextField supervisorNameField;

    @FXML
    private TextField supervisorLastNameField;

    @FXML
    private TextField supervisorEmailField, interractionsTimeField, newRouteField;

    @FXML
    private PasswordField supervisorPasswordFirst;

    @FXML
    private PasswordField supervisorPasswordSecond;

    @FXML
    private RadioButton activeSupervisors, inactiveSupervisors, activeTerminals, inactiveTerminals;

    @FXML
    private RadioButton activeControllers, inactiveControllers, needsDocumentationRadio, periodicRadio, amountRadio;

    @FXML
    private RadioButton allRequests, unprocessedRequests;

    @FXML
    private Label supervisorInfoLabel, pickedRequestLabel;

    @FXML
    private Label transporterInfoLabel, requestsInfoLabel;

    @FXML
    private TextField newTransporterField, ticketPriceField, documentationNameField, ticketInputField;

    @FXML
    private TextField timeAfterField, timeFromField, timeUntilField, userIdField, supervisorIdField, terminalIdField, ticketNameField;

    @FXML
    private Label transactionsInfoLabel, controllersInfoLabel, terminalsInfoLabel, ticketInfoLabel, routesInfoLabel;

    @FXML
    private ImageView userImage;

    @FXML
    private ChoiceBox<String> pickTransporterChoiceBox, searchByTransporterChoiceBox, transactionsTransporterChoiceBox, requestsTransportersChoiceBox,
    terminalTransporterChoiceBox, ticketsByTransporterChoiceBox, ticketTransportersChoiceBox, routesTransportersChoiceBox,
    newRouteTransporterChoiceBox;

    private String token = null;
    private int usersPage = 0;
    private int supervisorsPage = 0;
    private int controllersPage = 0;
    private int activeControllersPage = 0;
    private int inactiveControllersPage = 0;
    private int activeSupervisorsPage = 0;
    private int inactiveSupervisorsPage = 0;
    private int allTransactionsPage = 0;
    private int transactionsBetweenPage = 0;
    private int transactionsSupervisorIdPage = 0;
    private int transactionsTerminalIdPage = 0;
    private int transactionsTransporterIdPage = 0;
    private int transactionsUserIdPage = 0;
    private int ticketsPage = 0;
    private List<Transporter> transporters = null;
    private List<Supervisor> supervisors = null;
    private List<User> users = null;
    private Terminal pickedTerminal = null;
    private TerminalActivationRequest pickedRequest = null;
    private List<Integer> ticketTransporters = new ArrayList<>();

    public void logout(ActionEvent event){

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Potvrda odjave");
        alert.setHeaderText("Potvrdi odjavu");
        alert.setContentText("Da li se želite odjaviti?");

        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        yesButton.setText("Da");

        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        noButton.setText("Ne");

        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                token = null;
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/ps/frontend/desktop/LoginView.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        token = LoginViewController.loggedToken;
        mainForm.setVisible(true);
        usersForm.setVisible(false);
        supervisorsForm.setVisible(false);
        transactionsForm.setVisible(false);
        controllersForm.setVisible(false);
        transportersForm.setVisible(false);
        requestsForm.setVisible(false);
        terminalsForm.setVisible(false);
        ticketsForm.setVisible(false);
        routesForm.setVisible(false);
        initializeTransportersForm();
        initializeSupervisorsForm();
        initializeUsersForm();
        
    }

    public void initializeTicketsForm(){

        ticketsPage = 0;
        needsDocumentationRadio.setSelected(false);
        periodicRadio.setSelected(false);
        amountRadio.setSelected(false);
        ticketsNameColumn.setCellValueFactory(new PropertyValueFactory<TicketType, String>("name"));
        ticketsPriceColumn.setCellValueFactory(new PropertyValueFactory<TicketType, String>("cost"));
        ticketsAmountColumn.setCellValueFactory(new PropertyValueFactory<TicketType, String>("amount"));
        ticketsDurationColumn.setCellValueFactory(new PropertyValueFactory<TicketType, String>("validFor"));
        ticketsStatusColumn.setCellValueFactory(new Callback<CellDataFeatures<TicketType, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<TicketType, String> cellData) {
                TicketType ticket = cellData.getValue();
                if(ticket.getInUse())
                    return new SimpleStringProperty("U upotrebi");
                else
                    return new SimpleStringProperty("Van upotrebe");
            }
        });
        
        ticketsTable.getItems().clear();
        this.ticketsTable.getItems().setAll(getAllTickets(ticketsPage));

        ticketsTable.setRowFactory(tv -> {
            TableRow<TicketType> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    TicketType ticket = row.getItem();
                    changeTicketStatus(ticket);
                }
            });
            return row;
        });

        ticketsByTransporterChoiceBox.getItems().clear();
        List<String> transporterNames = new ArrayList<>();
        for(int i = 0; i < transporters.size(); i++)
            transporterNames.add(transporters.get(i).getName());
        ticketsByTransporterChoiceBox.getItems().addAll(transporterNames);
        ticketTransportersChoiceBox.getItems().clear();
        ticketTransportersChoiceBox.getItems().addAll(transporterNames);
        ticketTransportersChoiceBox.setOnAction(this::getTicketTransporter);
        ticketInfoLabel.setText("");
    }

    public void getTicketTransporter(ActionEvent event){

        if(ticketTransportersChoiceBox.getValue() != null){
            ticketTransporters.add(getTransporterIdFromName(ticketTransportersChoiceBox.getValue()));
            if(ticketInfoLabel.getText().isEmpty())
                ticketInfoLabel.setText("Odabrani prevoznici: ");
            if("Odabrani prevoznici: ".compareTo(ticketInfoLabel.getText()) == 0)
                ticketInfoLabel.setText(ticketInfoLabel.getText() + " " + ticketTransportersChoiceBox.getValue());
            else 
                ticketInfoLabel.setText(ticketInfoLabel.getText() + ", " + ticketTransportersChoiceBox.getValue());
            ticketTransportersChoiceBox.getItems().remove(ticketTransportersChoiceBox.getValue());
        }
    }

    public void addNewTicket(ActionEvent event){

        if(ticketNameField.getText().isEmpty() || ticketPriceField.getText().isEmpty() || ticketTransporters.size() == 0 || ticketInputField.getText().isEmpty())
            ticketInfoLabel.setText("Polja naziv, cijena, vrijednost i prevoznik su obavezna");
        else if(needsDocumentationRadio.isSelected() && documentationNameField.getText().isEmpty())
            ticketInfoLabel.setText("Naziv dokumentacije je obavezan");
        else if(!periodicRadio.isSelected() && !amountRadio.isSelected())
            ticketInfoLabel.setText("Karta mora biti ili periodična ili količinska");
        else{
            TicketTypeRequest request = new TicketTypeRequest();
            request.setTransporterIds(ticketTransporters.toArray(new Integer[ticketTransporters.size()]));
            TicketType ticketType = new TicketType();
            try{
                if(amountRadio.isSelected()){
                    ticketType.setAmount(Integer.parseInt(ticketInputField.getText()));
                    ticketType.setType("amount");
                }
                else{
                    ticketType.setValidFor(Integer.parseInt(ticketInputField.getText()));
                    ticketType.setType("periodic");   
                }
                ticketType.setCost(new BigDecimal(ticketPriceField.getText()));
                ticketType.setNeedsDocumentaion(needsDocumentationRadio.isSelected());
                ticketType.setDocumentaionName(documentationNameField.getText());
                ticketType.setName(ticketNameField.getText());
                request.setTicketType(ticketType);
                if(addTicket(request))
                    initializeTicketsForm();
                else
                    ticketInfoLabel.setText("Greska pri dodavanju nove karte");
            }
            catch(Exception e){
                ticketInfoLabel.setText("Greska pri unosu podataka");
            }
        }
    }

    private boolean addTicket(TicketTypeRequest request){

            URL url;
            try {
                url = new URL(App.BASE_URL + "tickets/addTicketType");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                Gson gson = new Gson();
                String jsonTicket =  gson.toJson(request);

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(jsonTicket.getBytes("UTF-8"));
                outputStream.close();

                int responseCode = conn.getResponseCode();
                if(responseCode == 200){
                    return true;
                }
                else
                    return false;
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        return false;
    }

    public void nextTicketsPage(ActionEvent event){

        List<TicketType> tickets = null;
        if(ticketsByTransporterChoiceBox.getValue() == null){
            tickets = getAllTickets(ticketsPage + 1);
            if(tickets.size() != 0){
                ++ticketsPage;
                ticketsTable.getItems().clear();
                ticketsTable.getItems().setAll(tickets);
            }
        }
    }

    public void previousTicketsPage(ActionEvent event){

        if(ticketsByTransporterChoiceBox.getValue() == null)
            if(ticketsPage != 0){
                ticketsTable.getItems().clear();
                ticketsTable.getItems().setAll(getAllTickets(--ticketsPage));
            }
    }

    public void showFilteredTickets(ActionEvent event){

        if(ticketsByTransporterChoiceBox.getValue() != null){

            ticketsTable.getItems().clear();
            ticketsTable.getItems().setAll(getInUseTicketsForTransporterId(getTransporterIdFromName(ticketsByTransporterChoiceBox.getValue())));
        }
    }

    private List<TicketType> getInUseTicketsForTransporterId(int transporterId){

        URL url;
        try {
            url = new URL(App.BASE_URL + "tickets/getInUseTicketsForTransporter/" + transporterId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<TicketType> tickets = gson.fromJson(response.toString(), new TypeToken<List<TicketType>>(){}.getType());
                return tickets;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private void changeTicketStatus(TicketType ticket){

        URL url;
        try {
            url = new URL(App.BASE_URL + "tickets/ChangeisActiveTicketTypeId=" + ticket.getId() + "andIsActive=" + !ticket.getInUse());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200)
                initializeTicketsForm();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	
    }

    private List<TicketType> getAllTickets(int pageNumber){

        URL url;
        try {
            url = new URL(App.BASE_URL + "tickets/getAllTickets/pagesize=" + pageNumber + "size=22");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<TicketType> tickets = gson.fromJson(response.toString(), new TypeToken<List<TicketType>>(){}.getType());
                return tickets;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void initializeRoutesForm(){

        routesInfoLabel.setText("");
        routesIdColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("id"));
        routesNameColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("name"));
        routesStatusColumn.setCellValueFactory(new Callback<CellDataFeatures<Route, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Route, String> cellData) {
                Route route = cellData.getValue();
                if(route.isActive())
                    return new SimpleStringProperty("Aktivna");
                else
                    return new SimpleStringProperty("Neaktivna");
            }
        });
        routesTransporterColumn.setCellValueFactory(new Callback<CellDataFeatures<Route, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Route, String> cellData) {
                Route route = cellData.getValue();
                return new SimpleStringProperty(getTransporterNameFromId(route.getTransporterId()));
            }
        });

        routesTable.setRowFactory(tv -> {
            TableRow<Route> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Route route = row.getItem();
                    changeRouteStatus(route);
                }
            });
            return row;
        });

        routesTransportersChoiceBox.getItems().clear();
        List<String> transporterNames = new ArrayList<>();
        for(int i = 0; i < transporters.size(); i++)
            transporterNames.add(transporters.get(i).getName());
        routesTransportersChoiceBox.getItems().addAll(transporterNames);
        newRouteTransporterChoiceBox.getItems().clear();
        newRouteTransporterChoiceBox.getItems().addAll(transporterNames);

        routesTable.getItems().clear();
        routesTable.getItems().addAll(getAllRoutes());
    }

    private void changeRouteStatus(Route route){

        URL url;
        try {
            url = new URL(App.BASE_URL + "routes/ChangeisActiveRouteId=" + route.getId() + "andIsActive=" + !route.isActive());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200)
                initializeRoutesForm();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	
    }

    public void getFilteredRoutes(ActionEvent event){

        if(routesTransportersChoiceBox.getValue() == null)
            routesInfoLabel.setText("Nije odabran prevoznik za pretragu");
        else{
            routesTable.getItems().clear();
            routesTable.getItems().addAll(getAllRoutesByTransporterId(getTransporterIdFromName(routesTransportersChoiceBox.getValue())));
        }
    }

    private List<Route> getAllRoutesByTransporterId(int transporterId){

        URL url;
        try {
            url = new URL(App.BASE_URL + "routes/getAllRoutesByTransporterId=" + transporterId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Route> routes = gson.fromJson(response.toString(), new TypeToken<List<Route>>(){}.getType());
                return routes;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;   
    }

    private List<Route> getAllRoutes(){

        URL url;
        try {
            url = new URL(App.BASE_URL + "routes/getAll");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Route> routes = gson.fromJson(response.toString(), new TypeToken<List<Route>>(){}.getType());
                return routes;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;   
    }

    public void addNewRoute(ActionEvent event){

        if(newRouteField.getText().isEmpty() || newRouteTransporterChoiceBox.getValue() == null)
            routesInfoLabel.setText("Oba polja su obavezna");
        else{
            URL url;
                try {
                    url = new URL(App.BASE_URL + "routes/addRoute");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    
                    conn.setRequestProperty("Authorization", "Bearer " + token);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    Gson gson = new Gson();
                    String jsonRoute =  gson.toJson(new Route(newRouteField.getText(), getTransporterIdFromName(newRouteTransporterChoiceBox.getValue())));

                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(jsonRoute.getBytes("UTF-8"));
                    outputStream.close();

                    int responseCode = conn.getResponseCode();
                    if(responseCode == 200){
                        
                        initializeRoutesForm();
                        routesInfoLabel.setText("Uspješno dodavanje nove linije.");   
                    }
                    else
                        routesInfoLabel.setText("Dodavanje nove linije neuspješno.");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public void initializeTerminalsForm(){

        if(pickedTerminal != null)
            pickedTerminal = null;

        activeTerminals.setSelected(false);
        inactiveTerminals.setSelected(false);
        terminalIdColumn.setCellValueFactory(new PropertyValueFactory<Terminal, String>("id"));
        terminalTransporterColumn.setCellValueFactory(new Callback<CellDataFeatures<Terminal, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Terminal, String> cellData) {
                Terminal terminal = cellData.getValue();

                return new SimpleStringProperty(getTransporterNameFromId(terminal.getTransporterId()));
            }
        });
        terminalStatusColumn.setCellValueFactory(new Callback<CellDataFeatures<Terminal, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Terminal, String> cellData) {
                Terminal terminal = cellData.getValue();

                if (terminal.isActive()) {
                    return new SimpleStringProperty("Aktivan");
                } else {
                    return new SimpleStringProperty("Neaktivan");
                }
            }
        });

        terminalsTable.setRowFactory(tv -> {
            TableRow<Terminal> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Terminal terminal = row.getItem();
                    changeTerminalStatus(terminal);
                }
            });
            return row;
        });
        terminalsTable.setRowFactory(tv -> {
            TableRow<Terminal> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Terminal terminal = row.getItem();
                    pickedTerminal = terminal;
                }
            });
            return row;
        });

        terminalsTable.getItems().clear();
        terminalTransporterChoiceBox.getItems().clear();
        List<String> transporterNames = new ArrayList<>();
        for(int i = 0; i < transporters.size(); i++)
            transporterNames.add(transporters.get(i).getName());
        terminalTransporterChoiceBox.getItems().addAll(transporterNames);

        scanTerminalColumn.setCellValueFactory(new Callback<CellDataFeatures<ScanInterraction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<ScanInterraction, String> cellData) {
                ScanInterraction scanInterraction = cellData.getValue();
                return new SimpleStringProperty(scanInterraction.getId().getRouteHistoryTerminalId().toString());
            }
        });
        scanUserColumn.setCellValueFactory(new Callback<CellDataFeatures<ScanInterraction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<ScanInterraction, String> cellData) {
                ScanInterraction scanInterraction = cellData.getValue();
                return new SimpleStringProperty(getUserNameFromId(scanInterraction.getId().getUserId()));
            }
        });
        scanTimeColumn.setCellValueFactory(new Callback<CellDataFeatures<ScanInterraction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<ScanInterraction, String> cellData) {
                ScanInterraction scanInterraction = cellData.getValue();
                return new SimpleStringProperty(scanInterraction.getId().getTime().toString());
            }
        });
        routeHistoryStartTimeColumn.setCellValueFactory(new Callback<CellDataFeatures<RouteHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<RouteHistory, String> cellData) {
                RouteHistory routeHistory = cellData.getValue();
                return new SimpleStringProperty(routeHistory.getPrimaryKey().getFromDateTime().toString());
            }
        });
        routeHistoryDriverColumn.setCellValueFactory(new Callback<CellDataFeatures<RouteHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<RouteHistory, String> cellData) {
                RouteHistory routeHistory = cellData.getValue();
                return new SimpleStringProperty(routeHistory.getDriverId().toString());
            }
        });
        routeHistoryEndTimeColumn.setCellValueFactory(new Callback<CellDataFeatures<RouteHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<RouteHistory, String> cellData) {
                RouteHistory routeHistory = cellData.getValue();

                if(routeHistory.getToDateTime() != null)
                    return new SimpleStringProperty(routeHistory.getToDateTime().toString());
                else
                    return new SimpleStringProperty("");
            }
        });
    }

    private String getUserNameFromId(int userId){

        for(int i = 0; i < users.size(); i++)
            if(users.get(i).getId() == userId)
                return users.get(i).getFirstName() + " " + users.get(i).getLastName();
        return null;
    }

    public void showTerminalDetails(ActionEvent event){

        if(pickedTerminal != null){
            terminalForm.setVisible(true);
            routeHistoryTable.getItems().clear();
            routeHistoryTable.getItems().addAll(getRouteHistories(pickedTerminal.getId()));
        }
        else
            terminalsInfoLabel.setText("Morate odabrati terminal");
    }

    private List<RouteHistory> getRouteHistories(int terminalId){

        URL url;
        try {
            url = new URL(App.BASE_URL + "routesHistory/GetRouteHistoriesByTerminalId=" + terminalId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<RouteHistory> routeHistories = gson.fromJson(response.toString(), new TypeToken<List<RouteHistory>>(){}.getType());
                return routeHistories;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void showTerminalInterractions(ActionEvent event){

        if(!interractionsTimeField.getText().isEmpty()){
            try{
                Long minutes = Long.parseLong(interractionsTimeField.getText());
                scanInterractionsTable.getItems().clear();
                scanInterractionsTable.getItems().addAll(getTerminalInterractions(pickedTerminal, minutes));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private List<ScanInterraction> getTerminalInterractions(Terminal terminal, long minutes){

        URL url;
        try {
            url = new URL(App.BASE_URL + "terminals/admin/getScanInterractionsByTerminalId=" + terminal.getId() + "andNotOlderThan=" + minutes);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<ScanInterraction> interractions = gson.fromJson(response.toString(), new TypeToken<List<ScanInterraction>>(){}.getType());
                return interractions;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void showFilteredTerminals(ActionEvent event){

        if(activeTerminals.isSelected() && terminalTransporterChoiceBox.getValue() != null){
            terminalsTable.getItems().clear();
            terminalsTable.getItems().addAll(getTerminals(getTransporterIdFromName(terminalTransporterChoiceBox.getValue()), true));
        }
        else if(inactiveTerminals.isSelected() && terminalTransporterChoiceBox.getValue() != null){
            terminalsTable.getItems().clear();
            terminalsTable.getItems().addAll(getTerminals(getTransporterIdFromName(terminalTransporterChoiceBox.getValue()), false));
        }
        else
            terminalsInfoLabel.setText("Prevoznik mora biti odabran, kao i jedan od dva izbora iznad");
    }

    private List<Terminal> getTerminals(int transporterId, boolean flag){

        URL url;
        try {
            if(flag)
                url = new URL(App.BASE_URL + "terminals/admin/getTerminalInUseByTransporterdId=" + transporterId);
            else
                url = new URL(App.BASE_URL + "terminals/admin/getTerminalNotInUseByTransporterdId=" + transporterId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Terminal> terminals = gson.fromJson(response.toString(), new TypeToken<List<Terminal>>(){}.getType());
                return terminals;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private void changeTerminalStatus(Terminal terminal){

        URL url;
        try {
            url = new URL(App.BASE_URL + "terminals/admin/ChangeisActiveTerminalId=" + terminal.getId() + "andIsActive=" + !terminal.isActive());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200)
                initializeTerminalsForm();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	
    }

    public void initializeUsersForm(){

        users = getAllUsers(usersPage);
        usersPage = 0;
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Credit"));
        this.usersTable.getItems().setAll(users);
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

    public void initializeTransportersForm(){

        transporters = getAllTransporters();
        transporterIdColumn.setCellValueFactory(new PropertyValueFactory<Transporter, String>("Id"));
        transporterNameColumn.setCellValueFactory(new PropertyValueFactory<Transporter, String>("Name"));
        transportersTable.getItems().setAll(transporters);
        List<String> transporterNames = new ArrayList<>();
        for(int i = 0; i < transporters.size(); i++)
            transporterNames.add(transporters.get(i).getName());
        pickTransporterChoiceBox.getItems().clear();
        searchByTransporterChoiceBox.getItems().clear();
        pickTransporterChoiceBox.getItems().addAll(transporterNames);
        searchByTransporterChoiceBox.getItems().addAll(transporterNames);
    }

    public void initializeRequestsForm(){

        pickedRequestLabel.setText("");
        if(pickedRequest != null)
            pickedRequest = null;

        allRequests.setSelected(false);
        unprocessedRequests.setSelected(false);
        requestSerialColumn.setCellValueFactory(new PropertyValueFactory<TerminalActivationRequest, String>("serialNumber"));
        requestTransporterColumn.setCellValueFactory(new Callback<CellDataFeatures<TerminalActivationRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<TerminalActivationRequest, String> cellData) {
                TerminalActivationRequest request = cellData.getValue();

                return new SimpleStringProperty(getTransporterNameFromId(request.getTransporterId()));
            }
        });
        requestStatusColumn.setCellValueFactory(new Callback<CellDataFeatures<TerminalActivationRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<TerminalActivationRequest, String> cellData) {
                TerminalActivationRequest request = cellData.getValue();
        
                if (request.getProcessed()) {
                    return new SimpleStringProperty("Procesiran");
                } else {
                    return new SimpleStringProperty("Na čekanju");
                }
            }
        });

        List<String> transporterNames = new ArrayList<>();
        for(int i = 0; i < transporters.size(); i++)
            transporterNames.add(transporters.get(i).getName());
        requestsTransportersChoiceBox.getItems().clear();
        requestsTransportersChoiceBox.getItems().addAll(transporterNames);
        terminalRequestTable.getItems().clear();
        terminalRequestTable.getItems().addAll(getAllRequests());

        terminalRequestTable.setRowFactory(tv -> {
            TableRow<TerminalActivationRequest> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    TerminalActivationRequest request = row.getItem();
                    pickedRequest = request;
                    pickedRequestLabel.setText(request.getSerialNumber() + " | " + getTransporterNameFromId(request.getTransporterId()));
                }
            });
            return row;
        });
    }

    public void approveRequest(ActionEvent event){

        if(pickedRequest != null){
            if(pickedRequest.getProcessed())
                requestsInfoLabel.setText("Zahtjev je vec procesiran.");
            else{
                if(processRequest(pickedRequest, true)){
                    initializeRequestsForm();
                    requestsInfoLabel.setText("Zahtjev je uspjesno procesiran.");
                }
            }
        }
        else
            requestsInfoLabel.setText("Nije odabran nijedan zahtjev.");
    }

    public void denyRequest(ActionEvent event){

        if(pickedRequest != null){
            if(pickedRequest.getProcessed())
                requestsInfoLabel.setText("Zahtjev je vec procesiran.");
            else{
                if(processRequest(pickedRequest, false)){
                    initializeRequestsForm();
                    requestsInfoLabel.setText("Zahtjev je uspjesno procesiran.");
                }
            }
        }
        else
            requestsInfoLabel.setText("Nije odabran nijedan zahtjev.");
    }

    private boolean processRequest(TerminalActivationRequest request, boolean flag){

        URL url;
        try {
            url = new URL(App.BASE_URL + "terminals/admin/ProcessTerminalAR" + request.getId() + "approval=" + flag);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                return true;
            }
            
        } catch (Exception e) {
            return false;
        }
	    
        return false;
    }

    public void showRequests(ActionEvent event){

        if(unprocessedRequests.isSelected() && requestsTransportersChoiceBox.getValue() == null){
            terminalRequestTable.getItems().clear();
            terminalRequestTable.getItems().addAll(getPendingRequests());
        }
        else if(!unprocessedRequests.isSelected() && !allRequests.isSelected() && requestsTransportersChoiceBox.getValue() != null){
            terminalRequestTable.getItems().clear();
            terminalRequestTable.getItems().addAll(getRequestsByTransporterId(getTransporterIdFromName(requestsTransportersChoiceBox.getValue())));
        }
        else{
            terminalRequestTable.getItems().clear();
            terminalRequestTable.getItems().addAll(getAllRequests());
        }
    }

    private List<TerminalActivationRequest> getPendingRequests() {
        
        URL url;
        try {
            url = new URL(App.BASE_URL + "terminals/admin/getAllPendingAR");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<TerminalActivationRequest> requests = gson.fromJson(response.toString(), new TypeToken<List<TerminalActivationRequest>>(){}.getType());
                return requests;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<TerminalActivationRequest> getRequestsByTransporterId(int transporterId) {
        
        URL url;
        try {
            url = new URL(App.BASE_URL + "terminals/admin/getARByTransporterId=" + transporterId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<TerminalActivationRequest> requests = gson.fromJson(response.toString(), new TypeToken<List<TerminalActivationRequest>>(){}.getType());
                return requests;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<TerminalActivationRequest> getAllRequests(){

        URL url;
        try {
            url = new URL(App.BASE_URL + "terminals/admin/getAllAR");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<TerminalActivationRequest> requests = gson.fromJson(response.toString(), new TypeToken<List<TerminalActivationRequest>>(){}.getType());
                return requests;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void initializeControllersForm(){

        activeControllers.setSelected(false);
        inactiveControllers.setSelected(false);
        controllersPage = 0;
        controllersNameColumn.setCellValueFactory(new PropertyValueFactory<Controller, String>("firstname"));
        controllersLastNameColumn.setCellValueFactory(new PropertyValueFactory<Controller, String>("lastname"));
        controllersJMBColumn.setCellValueFactory(new PropertyValueFactory<Controller, String>("jmb"));
        controllersPinColumn.setCellValueFactory(new PropertyValueFactory<Controller, String>("Pin"));
        controllersStatusColumn.setCellValueFactory(new Callback<CellDataFeatures<Controller, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Controller, String> cellData) {
                Controller controller = cellData.getValue();
        
                if (controller.getIsActive()) {
                    return new SimpleStringProperty("Aktivan");
                } else {
                    return new SimpleStringProperty("Neaktivan");
                }
            }
        });
        List<Controller> controllers = this.getAllControllers(controllersPage);
        this.controllersTable.getItems().setAll(controllers);

        controllersTable.setRowFactory(tv -> {
            TableRow<Controller> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Controller controller = row.getItem();
                    changeControllerStatus(controller);
                }
            });
            return row;
        });
    }

    public void registerController(ActionEvent event){

        if(controllersNameField.getText().isEmpty() || controllersLastNameField.getText().isEmpty() || controllersPinField.getText().isEmpty() ||
        controllersJMBField.getText().isEmpty())
            controllersInfoLabel.setText("Sva polja moraju biti unesena.");
        else{
            Controller controller = new Controller(controllersPinField.getText(), controllersNameField.getText(), controllersLastNameField.getText(), 
            controllersJMBField.getText());
            URL url;
            try {
                url = new URL(App.BASE_URL + "pinusers/register/controller");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                Gson gson = new Gson();
                String jsonController =  gson.toJson(controller);

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(jsonController.getBytes("UTF-8"));
                outputStream.close();

                int responseCode = conn.getResponseCode();
                if(responseCode == 200){
                    
                    controllersInfoLabel.setText("Registracija uspjesna.");
                    initializeControllersForm();
                }
                else
                    controllersInfoLabel.setText("Registracija neuspjesna.");
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void nextControllersPage(ActionEvent event){

        List<Controller> controllers = null;
        if(!activeControllers.isSelected() && !inactiveControllers.isSelected()){
            controllers = getAllControllers(controllersPage + 1);
            if(controllers.size() != 0){
                ++controllersPage;
                controllersTable.getItems().clear();
                controllersTable.getItems().setAll(controllers);
            }
        }
        else{
            if(activeControllers.isSelected()){
                controllers = getFilteredControllers(true, activeControllersPage + 1);
                if(controllers.size() != 0){
                    ++activeControllersPage;
                    controllersTable.getItems().clear();
                    controllersTable.getItems().setAll(controllers);
                }
            }
            else{
                controllers = getFilteredControllers(false, inactiveControllersPage + 1);
                if(controllers.size() != 0){
                    ++inactiveControllersPage;
                    controllersTable.getItems().clear();
                    controllersTable.getItems().setAll(controllers);
                }
            }
        }

    }

    public void previousControllersPage(ActionEvent event){

        if(!activeControllers.isSelected() && !inactiveControllers.isSelected())
            if(controllersPage != 0){
                controllersTable.getItems().clear();
                controllersTable.getItems().setAll(getAllControllers(--controllersPage));
            }
        else{
            if(activeControllers.isSelected()){
                if(activeControllersPage != 0){
                    controllersTable.getItems().clear();
                    controllersTable.getItems().setAll(getFilteredControllers(true, --activeControllersPage));
                }
            }
            else{
                if(inactiveControllersPage != 0){
                    controllersTable.getItems().clear();
                    controllersTable.getItems().setAll(getFilteredControllers(false, --inactiveControllersPage));
                }
            }
        }
    }

    public void showFilteredControllers(ActionEvent event){

        List<Controller> filteredControllers = null;
        if(activeControllers.isSelected()){
            activeControllersPage = 0;
            filteredControllers = getFilteredControllers(true, activeControllersPage);
        }
        else if(inactiveControllers.isSelected()){
            inactiveControllersPage = 0;
            filteredControllers = getFilteredControllers(false, inactiveControllersPage); 
        }
        if(filteredControllers != null){
            controllersTable.getItems().clear();
            controllersTable.getItems().setAll(filteredControllers);
        }
    }

    private void changeControllerStatus(Controller controller){

        boolean status = true;
        if(controller.getIsActive())
            status = false;
        URL url;
        try {
            url = new URL(App.BASE_URL + "pinusers/controllers/ChangeisActiveControllerId=" + controller.getId() + "andIsActive=" + status);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("POST");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                controllersInfoLabel.setText("Status kontrolera uspjesno promjenjen.");
                initializeControllersForm();
            }
            else
                controllersInfoLabel.setText("Status kontrolera neuspjesno promjenjen.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Controller> getAllControllers(int pageNumber) {
        
        URL url;
        try {
            url = new URL(App.BASE_URL + "pinusers/controllers/getControllers/pagesize="+ pageNumber + "size=22");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Controller> controllers = gson.fromJson(response.toString(), new TypeToken<List<Controller>>(){}.getType());
                return controllers;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<Controller> getFilteredControllers(boolean status, int pageNumber) {
        
        URL url;
        try {
            if(status)
                url = new URL(App.BASE_URL + "pinusers/controllers/getActiveControllers/pagesize=" + pageNumber + "size=22");
            else
                url = new URL(App.BASE_URL + "pinusers/controllers/getInactiveControllers/pagesize=" + pageNumber + "size=22");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Controller> controllers = gson.fromJson(response.toString(), new TypeToken<List<Controller>>(){}.getType());
                return controllers;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void initializeTransactionsForm(){

        transactionsEmailColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Email"));
        transactionsEmailColumn.setCellValueFactory(new Callback<CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Transaction, String> cellData) {
                Transaction transaction = cellData.getValue();
                if(transaction.getUserId() != null)
                    return new SimpleStringProperty(getUserEmailById(transaction.getUserId()));
                else
                    return new SimpleStringProperty("-");
            }
        });
        transactionsValueColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Amount"));
        transactionsTimestampColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Timestamp"));
        transactionsTerminalColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("terminalId"));
        transactionsSupervisorColumn.setCellValueFactory(new Callback<CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Transaction, String> cellData) {
                Transaction transaction = cellData.getValue();
                if(transaction.getSupervisorId() != null)
                    return new SimpleStringProperty(getSupervisorFullName(transaction.getSupervisorId()));
                else
                    return new SimpleStringProperty("");
            }
        });
        
        List<String> transporterNames = new ArrayList<>();
        for(int i = 0; i < transporters.size(); i++)
            transporterNames.add(transporters.get(i).getName());
        transactionsTransporterChoiceBox.getItems().clear();
        transactionsTransporterChoiceBox.getItems().addAll(transporterNames);
    }

    private String getUserEmailById(Integer userId){
        for(int i = 0; i < users.size(); i++)
            if(users.get(i).getId() == userId)
                return users.get(i).getEmail();
        return null;
    }

    private String getSupervisorFullName(int id){
        for(int i = 0; i < supervisors.size(); i++){
            if(supervisors.get(i).getId() == id)
                return supervisors.get(i).getFirstName() + " " + supervisors.get(i).getLastName();
        }
        return null;
    }

    public void showTransactions(ActionEvent event){

        allTransactionsPage = 0;
        transactionsBetweenPage = 0;
        transactionsUserIdPage = 0;
        transactionsSupervisorIdPage = 0;
        transactionsTerminalIdPage = 0;
        transactionsTransporterIdPage = 0;
        if(!timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(isValidTimestampFormat(timeAfterField.getText())){
                List<Transaction> transactions = getTransactionsAfter(timeAfterField.getText(), allTransactionsPage);
                transactionsTable.getItems().clear();
                transactionsTable.getItems().addAll(transactions);
            }
            else
                transactionsInfoLabel.setText("Neispravan format vremena.");
        }
        else if(timeAfterField.getText().isEmpty() && !timeFromField.getText().isEmpty() && !timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(isValidTimestampFormat(timeFromField.getText()) && isValidTimestampFormat(timeUntilField.getText())){
                List<Transaction> transactions = getTransactionsBetween(timeFromField.getText(), timeUntilField.getText(), transactionsBetweenPage);
                transactionsTable.getItems().clear();
                transactionsTable.getItems().addAll(transactions); 
            }
            else
                transactionsInfoLabel.setText("Neispravan format vremena.");
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && !userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            List<Transaction> transactions = getTransactionsForUserId(userIdField.getText(), transactionsUserIdPage);
            transactionsTable.getItems().clear();
            transactionsTable.getItems().addAll(transactions); 
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && !supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            List<Transaction> transactions = getTransactionsForSupervisorId(supervisorIdField.getText(), transactionsSupervisorIdPage);
            transactionsTable.getItems().clear();
            transactionsTable.getItems().addAll(transactions); 
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && !terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            List<Transaction> transactions = getTransactionsForTerminalId(terminalIdField.getText(), transactionsTerminalIdPage);
            transactionsTable.getItems().clear();
            transactionsTable.getItems().addAll(transactions); 
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() != null){
            List<Transaction> transactions = getTransactionsForTransporterId(getTransporterIdFromName(transactionsTransporterChoiceBox.getValue()), transactionsTransporterIdPage);
            transactionsTable.getItems().clear();
            transactionsTable.getItems().addAll(transactions); 
        }
        else
            transactionsInfoLabel.setText("Samo jedna vrsta pretrage je moguca u jednom trenutku.");
    }

    public void getNextTransactionsPage(ActionEvent event){

        if(!timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(isValidTimestampFormat(timeAfterField.getText())){
                List<Transaction> transactions = getTransactionsAfter(timeAfterField.getText(), allTransactionsPage + 1);
                if(transactions.size() > 0){
                    transactionsTable.getItems().clear();
                    transactionsTable.getItems().addAll(transactions);
                    allTransactionsPage++;
                }
            }
            else
                transactionsInfoLabel.setText("Neispravan format vremena.");
        }
        else if(timeAfterField.getText().isEmpty() && !timeFromField.getText().isEmpty() && !timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(isValidTimestampFormat(timeFromField.getText()) && isValidTimestampFormat(timeUntilField.getText())){
                List<Transaction> transactions = getTransactionsBetween(timeFromField.getText(), timeUntilField.getText(), transactionsBetweenPage + 1);
                if(transactions.size() > 0){
                    transactionsTable.getItems().clear();
                    transactionsTable.getItems().addAll(transactions);
                    transactionsBetweenPage++;
                }
            }
            else
                transactionsInfoLabel.setText("Neispravan format vremena.");
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && !userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            List<Transaction> transactions = getTransactionsForUserId(userIdField.getText(), transactionsUserIdPage + 1);
                if(transactions.size() > 0){
                    transactionsTable.getItems().clear();
                    transactionsTable.getItems().addAll(transactions);
                    transactionsUserIdPage++;
                }
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && !supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            List<Transaction> transactions = getTransactionsForSupervisorId(supervisorIdField.getText(), transactionsSupervisorIdPage + 1);
                if(transactions.size() > 0){
                    transactionsTable.getItems().clear();
                    transactionsTable.getItems().addAll(transactions);
                    transactionsSupervisorIdPage++;
                } 
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && !terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            List<Transaction> transactions = getTransactionsForTerminalId(terminalIdField.getText(), transactionsTerminalIdPage + 1);
                if(transactions.size() > 0){
                    transactionsTable.getItems().clear();
                    transactionsTable.getItems().addAll(transactions);
                    transactionsTerminalIdPage++;
                }
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() != null){
            List<Transaction> transactions = getTransactionsForTransporterId(getTransporterIdFromName(transactionsTransporterChoiceBox.getValue()), transactionsTransporterIdPage + 1);
                if(transactions.size() > 0){
                    transactionsTable.getItems().clear();
                    transactionsTable.getItems().addAll(transactions);
                    transactionsTransporterIdPage++;
                } 
        }
    }

    public void getPreviousTransactionsPage(ActionEvent event){

        if(!timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(isValidTimestampFormat(timeAfterField.getText())){
                if(allTransactionsPage > 0){
                    List<Transaction> transactions = getTransactionsAfter(timeAfterField.getText(), --allTransactionsPage);
                    transactionsTable.getItems().clear();
                    transactionsTable.getItems().addAll(transactions);
                }
            }
            else
                transactionsInfoLabel.setText("Neispravan format vremena.");
        }
        else if(timeAfterField.getText().isEmpty() && !timeFromField.getText().isEmpty() && !timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(isValidTimestampFormat(timeFromField.getText()) && isValidTimestampFormat(timeUntilField.getText())){
                if(transactionsBetweenPage > 0){
                    List<Transaction> transactions = getTransactionsBetween(timeFromField.getText(), timeUntilField.getText(), --transactionsBetweenPage);
                    transactionsTable.getItems().clear();
                    transactionsTable.getItems().addAll(transactions); 
                }
            }
            else
                transactionsInfoLabel.setText("Neispravan format vremena.");
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && !userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(transactionsUserIdPage > 0){
                List<Transaction> transactions = getTransactionsForUserId(userIdField.getText(), --transactionsUserIdPage);
                transactionsTable.getItems().clear();
                transactionsTable.getItems().addAll(transactions); 
            }
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && !supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(transactionsSupervisorIdPage > 0){
                List<Transaction> transactions = getTransactionsForSupervisorId(supervisorIdField.getText(), --transactionsSupervisorIdPage);
                transactionsTable.getItems().clear();
                transactionsTable.getItems().addAll(transactions); 
            }
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && !terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() == null){
            if(transactionsTerminalIdPage > 0){
                List<Transaction> transactions = getTransactionsForTerminalId(terminalIdField.getText(), --transactionsTerminalIdPage);
                transactionsTable.getItems().clear();
                transactionsTable.getItems().addAll(transactions);
            } 
        }
        else if(timeAfterField.getText().isEmpty() && timeFromField.getText().isEmpty() && timeUntilField.getText().isEmpty() && userIdField.getText().isEmpty()
        && supervisorIdField.getText().isEmpty() && terminalIdField.getText().isEmpty() && transactionsTransporterChoiceBox.getValue() != null){
            if(transactionsTransporterIdPage > 0){
                List<Transaction> transactions = getTransactionsForTransporterId(getTransporterIdFromName(transactionsTransporterChoiceBox.getValue()), --transactionsTransporterIdPage);
                transactionsTable.getItems().clear();
                transactionsTable.getItems().addAll(transactions); 
            }
        }
    }

    private List<Transaction> getTransactionsForTransporterId(Integer transporterId, int transactionsTransporterIdPage){

        URL url;
        try {
            url = new URL(App.BASE_URL + "transactions/getTransactionsByTransporterId=" + transporterId + "page=" + transactionsTransporterIdPage + "size=30");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Transaction> transactions = gson.fromJson(response.toString(), new TypeToken<List<Transaction>>(){}.getType());
                return transactions;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Transaction> getTransactionsForTerminalId(String terminalId, int transactionsTerminalIdPage){

        URL url;
        try {
            url = new URL(App.BASE_URL + "transactions/getScanTransactionsByTerminalId=" + terminalId + "page=" + transactionsTerminalIdPage + "size=30");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Transaction> transactions = gson.fromJson(response.toString(), new TypeToken<List<Transaction>>(){}.getType());
                return transactions;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Transaction> getTransactionsForSupervisorId(String supervisorId, int transactionsSupervisorIdPage){

        URL url;
        try {
            url = new URL(App.BASE_URL + "transactions/getCreditTransactionsBySupervisorId=" + supervisorId + "page=" + transactionsSupervisorIdPage + "size=30");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Transaction> transactions = gson.fromJson(response.toString(), new TypeToken<List<Transaction>>(){}.getType());
                return transactions;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Transaction> getTransactionsForUserId(String userId, int transactionsUserIdPage){

        URL url;
        try {
            url = new URL(App.BASE_URL + "transactions/getTransactionsForUser=" + userId + "page=" + transactionsUserIdPage + "size=30");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Transaction> transactions = gson.fromJson(response.toString(), new TypeToken<List<Transaction>>(){}.getType());
                return transactions;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isValidTimestampFormat(String input) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date date = dateFormat.parse(input);
            return input.equals(dateFormat.format(date));
        } catch (ParseException e) {
            return false;
        }
    }

    private List<Transaction> getTransactionsBetween(String fromTimestampString, String untilTimestampString, int transactionsBetweenPage){

        URL url;
        try {
            url = new URL(App.BASE_URL + "transactions/getTransactionsInBetweenStart=" + URLEncoder.encode(fromTimestampString, StandardCharsets.UTF_8.toString()) + "End=" + 
            URLEncoder.encode(untilTimestampString, StandardCharsets.UTF_8.toString()) + "page=" + transactionsBetweenPage + "size=30");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Transaction> transactions = gson.fromJson(response.toString(), new TypeToken<List<Transaction>>(){}.getType());
                return transactions;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Transaction> getTransactionsAfter(String timestampString, int allTransactionsPage){

        URL url;
        try {
            url = new URL(App.BASE_URL + "transactions/getTransactionsAfterPaged" + URLEncoder.encode(timestampString, StandardCharsets.UTF_8.toString()) + 
            "page=" + allTransactionsPage + "size=30");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Transaction> transactions = gson.fromJson(response.toString(), new TypeToken<List<Transaction>>(){}.getType());
                return transactions;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void registerTransporter(ActionEvent event){

        if(newTransporterField.getText().isEmpty())
            transporterInfoLabel.setText("Polje iznad ne smije biti prazno.");
        else{
            Transporter transporter = new Transporter(newTransporterField.getText());
            int tmp = 0;
            for(int i = 0; i < transporters.size(); i++)
                if(transporters.get(i).getName().compareTo(transporter.getName()) == 0){
                    transporterInfoLabel.setText("Prevoznik sa datim imenom vec postoji.");
                    tmp++;
                    break;
                }
            if(tmp == 0){
                URL url;
                try {
                    url = new URL(App.BASE_URL + "transporters/addTransporter");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    
                    conn.setRequestProperty("Authorization", "Bearer " + token);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    Gson gson = new Gson();
                    String jsonTransporter =  gson.toJson(transporter);

                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(jsonTransporter.getBytes("UTF-8"));
                    outputStream.close();

                    int responseCode = conn.getResponseCode();
                    if(responseCode == 200){
                        
                        transporterInfoLabel.setText("Registracija uspjesna.");
                        initializeTransportersForm();
                    }
                    else
                        transporterInfoLabel.setText("Registracija neuspjesna.");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Transporter> getAllTransporters(){

        URL url;
        try {
            url = new URL(App.BASE_URL + "transporters/getTransporters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Transporter> transporters = gson.fromJson(response.toString(), new TypeToken<List<Transporter>>(){}.getType());
                return transporters;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void initializeSupervisorsForm(){

        activeSupervisors.setSelected(false);
        inactiveSupervisors.setSelected(false);
        supervisorsPage = 0;
        supervisorEmailColumn.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("Email"));
        supervisorNameColumn.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("firstName"));
        supervisorLastNameColumn.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("LastName"));
        supervisorTransporterColumn.setCellValueFactory(new Callback<CellDataFeatures<Supervisor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Supervisor, String> cellData) {
                Supervisor supervisor = cellData.getValue();
                return new SimpleStringProperty(getTransporterNameFromId(supervisor.getTransporterId()));
            }
        });
        
        supervisorStatusColumn.setCellValueFactory(new Callback<CellDataFeatures<Supervisor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Supervisor, String> cellData) {
                Supervisor supervisor = cellData.getValue();
        
                if (supervisor.getIsActive()) {
                    return new SimpleStringProperty("Aktivan");
                } else {
                    return new SimpleStringProperty("Neaktivan");
                }
            }
        });
        supervisors = this.getAllSupervisors(supervisorsPage);
        this.supervisorsTable.getItems().setAll(supervisors);

        supervisorsTable.setRowFactory(tv -> {
            TableRow<Supervisor> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Supervisor supervisor = row.getItem();
                    changeSupervisorStatus(supervisor);
                }
            });
            return row;
        });
    }

    private void changeSupervisorStatus(Supervisor supervisor){

        boolean status = true;
        if(supervisor.getIsActive())
            status = false;
        URL url;
        try {
            url = new URL(App.BASE_URL + "supervisors/ChangeisActiveSupervisorId=" + supervisor.getId() + "andIsActive=" + status);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("POST");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                supervisorInfoLabel.setText("Status supervizora uspjesno promjenjen.");
                initializeSupervisorsForm();
            }
            else
                supervisorInfoLabel.setText("Status supervizora neuspjesno promjenjen.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTransporterNameFromId(int transporterId){

        for(int i = 0; i < transporters.size(); i++)
            if(transporters.get(i).getId() == transporterId)
                return transporters.get(i).getName();
        return null;
    }

    private Integer getTransporterIdFromName(String name){
        for(int i = 0; i < transporters.size(); i++)
            if(transporters.get(i).getName().compareTo(name) == 0)
                return transporters.get(i).getId();
        return null;
    }

    public void registerSupervisor(ActionEvent event){

        if(supervisorNameField.getText().isEmpty() || supervisorLastNameField.getText().isEmpty() || supervisorEmailField.getText().isEmpty() ||
        supervisorPasswordFirst.getText().isEmpty() || supervisorPasswordSecond.getText().isEmpty() || pickTransporterChoiceBox.getValue() == null)
            supervisorInfoLabel.setText("Sva polja moraju biti unesena.");
        else{
            String password1 = supervisorPasswordFirst.getText();
            String password2 = supervisorPasswordSecond.getText();
            if(password1.compareTo(password2) != 0)
                supervisorInfoLabel.setText("Lozinke se ne slazu.");
            else{
                SupervisorWithPassword supervisor = new SupervisorWithPassword(supervisorEmailField.getText(), supervisorNameField.getText(), 
                supervisorLastNameField.getText(), password1, getTransporterIdFromName(pickTransporterChoiceBox.getValue()));
            URL url;
            try {
                url = new URL(App.BASE_URL + "supervisors/register");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                Gson gson = new Gson();
                String jsonSupervisor =  gson.toJson(supervisor);

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(jsonSupervisor.getBytes("UTF-8"));
                outputStream.close();

                int responseCode = conn.getResponseCode();
                if(responseCode == 200){
                    
                    supervisorInfoLabel.setText("Registracija uspjesna.");
                    initializeSupervisorsForm();
                }
                else
                    supervisorInfoLabel.setText("Registracija neuspjesna.");
                
            } catch (Exception e) {
                e.printStackTrace();
            }

            }
        }
    }

    public void nextUsersPage(ActionEvent event){

        List<User> users = getAllUsers(usersPage + 1);
        if(users.size() != 0){
            ++usersPage;
            usersTable.getItems().clear();
            usersTable.getItems().setAll(users);
        }
    }

    public void previousUsersPage(ActionEvent event){

        if(usersPage != 0){
            usersTable.getItems().clear();
            usersTable.getItems().setAll(getAllUsers(--usersPage));
        }
    }

    public void nextSupervisorsPage(ActionEvent event){

        List<Supervisor> supervisors = null;
        if(!activeSupervisors.isSelected() && !inactiveSupervisors.isSelected()){
            supervisors = getAllSupervisors(supervisorsPage + 1);
            if(supervisors.size() != 0){
                ++supervisorsPage;
                supervisorsTable.getItems().clear();
                supervisorsTable.getItems().setAll(supervisors);
            }
        }
        else{
            if(activeSupervisors.isSelected()){
                supervisors = getFilteredSupervisors(true, activeSupervisorsPage + 1);
                if(supervisors.size() != 0){
                    ++activeSupervisorsPage;
                    supervisorsTable.getItems().clear();
                    supervisorsTable.getItems().setAll(supervisors);
                }
            }
            else{
                supervisors = getFilteredSupervisors(false, inactiveSupervisorsPage + 1);
                if(supervisors.size() != 0){
                    ++inactiveSupervisorsPage;
                    supervisorsTable.getItems().clear();
                    supervisorsTable.getItems().setAll(supervisors);
                }
            }
        }

    }

    public void previousSupervisorsPage(ActionEvent event){

        if(!activeSupervisors.isSelected() && !inactiveSupervisors.isSelected())
            if(supervisorsPage != 0){
                supervisorsTable.getItems().clear();
                supervisorsTable.getItems().setAll(getAllSupervisors(--supervisorsPage));
            }
        else{
            if(activeSupervisors.isSelected()){
                if(activeSupervisorsPage != 0){
                    supervisorsTable.getItems().clear();
                    supervisorsTable.getItems().setAll(getFilteredSupervisors(true, --activeSupervisorsPage));
                }
            }
            else{
                if(inactiveSupervisorsPage != 0){
                    supervisorsTable.getItems().clear();
                    supervisorsTable.getItems().setAll(getFilteredSupervisors(false, --inactiveSupervisorsPage));
                }
            }
        }
    }

    public void showFilteredSupervisors(ActionEvent event){

        List<Supervisor> filteredSupervisors = null;
        if(!activeSupervisors.isSelected() && !inactiveSupervisors.isSelected() && searchByTransporterChoiceBox.getValue() != null){
            supervisorsTable.getItems().clear();
            supervisorsTable.getItems().setAll(getSupervisorsByTransporterId(getTransporterIdFromName(searchByTransporterChoiceBox.getValue())));
        }
        else if(activeSupervisors.isSelected()){
            activeSupervisorsPage = 0;
            filteredSupervisors = getFilteredSupervisors(true, activeSupervisorsPage);
        }
        else if(inactiveSupervisors.isSelected()){
            inactiveSupervisorsPage = 0;
            filteredSupervisors = getFilteredSupervisors(false, inactiveSupervisorsPage); 
        }
        if(filteredSupervisors != null){
            supervisorsTable.getItems().clear();
            supervisorsTable.getItems().setAll(filteredSupervisors);
        }
    }

    private List<Supervisor> getSupervisorsByTransporterId(int transporterId){

        URL url;
        try {
            url = new URL(App.BASE_URL + "supervisors/getByTransporterId=" + transporterId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Supervisor> supervisors = gson.fromJson(response.toString(), new TypeToken<List<Supervisor>>(){}.getType());
                return supervisors;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void showFiltered(ActionEvent event){

        List<User> filteredUsers;
        usersInfoLabel.setText("");
        if(userEmailField.getText().isEmpty()){
            if(userFirstNameField.getText().isEmpty() || userLastNameField.getText().isEmpty())
                usersInfoLabel.setText("Polja ime i prezime moraju biti popunjeni.");
            else{
                filteredUsers = getUsersByNameAndLastName(userFirstNameField.getText(), userLastNameField.getText());
                usersTable.getItems().clear();
                usersTable.getItems().setAll(filteredUsers);
            }
        }
        else{
            if(!userFirstNameField.getText().isEmpty() || !userLastNameField.getText().isEmpty())
                usersInfoLabel.setText("Samo polje E-mail smije biti popunjeno.");
            else{
                filteredUsers = getUsersByEmail(userEmailField.getText());
                usersTable.getItems().clear();
                usersTable.getItems().setAll(filteredUsers);
            }
        }
    }

    private List<User> getUsersByNameAndLastName(String firstName, String lastName){

        URL url;
        try {
            url = new URL(App.BASE_URL + "users/find/name");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            User user = new User(firstName, lastName);

            Gson gson = new Gson();
		    String jsonUser =  gson.toJson(user);

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
                List<User> users = gson.fromJson(response.toString(), new TypeToken<List<User>>(){}.getType());
                return users;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<User> getUsersByEmail(String email){

        URL url;
        try {
            url = new URL(App.BASE_URL + "users/find/email");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            User user = new User(email);

            Gson gson = new Gson();
		    String jsonUser =  gson.toJson(user);

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
                List<User> users = gson.fromJson(response.toString(), new TypeToken<List<User>>(){}.getType());
                return users;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }
    
    private void showUser(User user) {

        userImage.setImage(null);
        userForm.setVisible(true);
        userNameLabel.setText(user.getFirstName());
        userLastNameLabel.setText(user.getLastName());
        creditLabel.setText(user.getCredit().toString());
        this.usageColumn.setCellValueFactory(new PropertyValueFactory<UserTicket, String>("Usage"));
        this.validUntilColumn.setCellValueFactory(new PropertyValueFactory<UserTicket, String>("ValidUntilDate"));
        this.ticketNameColumn.setCellValueFactory(new Callback<CellDataFeatures<UserTicket, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<UserTicket, String> cellData) {
                UserTicket userTicket = cellData.getValue();
                String ticketTypeName = userTicket.getType().getName();
                return new SimpleStringProperty(ticketTypeName);
            }
        });

        userTicketsTable.getItems().setAll(getUserTickets(user));

        byte[] picture = getProfilePicture(user);
        if(picture != null)
            userImage.setImage(new Image(new ByteArrayInputStream(picture)));
    }

    private byte[] getProfilePicture(User user){

        URL url;
        try {
            url = new URL(App.BASE_URL + "user/files/get/profilepicture&userId=" + user.getId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                InputStream inputStream = conn.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                
                byte[] resultBytes = byteArrayOutputStream.toByteArray();
                
                byteArrayOutputStream.close();
                inputStream.close();
                return resultBytes;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<UserTicket> getUserTickets(User user){

        URL url;
        try {
            url = new URL(App.BASE_URL + "users/getUserTickets");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            Gson gson = new Gson();
		    String jsonUser =  gson.toJson(user);

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
                List<UserTicket> userTickets = gson.fromJson(response.toString(), new TypeToken<List<UserTicket>>(){}.getType());
                return userTickets;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<User> getAllUsers(int pageNumber) {
        
        URL url;
        try {
            url = new URL(App.BASE_URL + "users/getUsers/pagesize="+ pageNumber + "size=25");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<User> users = gson.fromJson(response.toString(), new TypeToken<List<User>>(){}.getType());
                return users;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<Supervisor> getAllSupervisors(int pageNumber) {
        
        URL url;
        try {
            url = new URL(App.BASE_URL + "supervisors/getSupervisors/pagesize="+ pageNumber + "size=22");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Supervisor> supervisors = gson.fromJson(response.toString(), new TypeToken<List<Supervisor>>(){}.getType());
                return supervisors;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<Supervisor> getFilteredSupervisors(boolean status, int pageNumber) {
        
        URL url;
        try {
            if(status)
                url = new URL(App.BASE_URL + "supervisors/getByisActiveTrue/pagesize="+ pageNumber + "size=22");
            else
                url = new URL(App.BASE_URL + "supervisors/getByisActiveFalse/pagesize="+ pageNumber + "size=22");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	            
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            
	            reader.close();
                Gson gson = new Gson();
                List<Supervisor> supervisors = gson.fromJson(response.toString(), new TypeToken<List<Supervisor>>(){}.getType());
                return supervisors;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void switchPane(ActionEvent event) throws ParseException{

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
            ticketsForm.setVisible(false);
            routesForm.setVisible(false);
            initializeUsersForm();
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
            ticketsForm.setVisible(false);
            routesForm.setVisible(false);
            initializeSupervisorsForm();
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
            ticketsForm.setVisible(false);
            routesForm.setVisible(false);
            initializeTransactionsForm();
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
            ticketsForm.setVisible(false);
            routesForm.setVisible(false);
            initializeControllersForm();
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
            ticketsForm.setVisible(false);
            routesForm.setVisible(false);
            initializeTransportersForm();
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
            ticketsForm.setVisible(false);
            routesForm.setVisible(false);
            initializeRequestsForm();
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
            ticketsForm.setVisible(false);
            routesForm.setVisible(false);
            initializeTerminalsForm();
        }
        else if (event.getSource() == ticketsButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            supervisorsForm.setVisible(false);
            transactionsForm.setVisible(false);
            controllersForm.setVisible(false);
            transportersForm.setVisible(false);
            requestsForm.setVisible(false);
            terminalsForm.setVisible(false);
            terminalForm.setVisible(false);
            ticketsForm.setVisible(true);
            routesForm.setVisible(false);
            initializeTicketsForm();
        }
        else if (event.getSource() == routesButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            supervisorsForm.setVisible(false);
            transactionsForm.setVisible(false);
            controllersForm.setVisible(false);
            transportersForm.setVisible(false);
            requestsForm.setVisible(false);
            terminalsForm.setVisible(false);
            terminalForm.setVisible(false);
            ticketsForm.setVisible(false);
            routesForm.setVisible(true);
            initializeRoutesForm();
        }
    }
    
}