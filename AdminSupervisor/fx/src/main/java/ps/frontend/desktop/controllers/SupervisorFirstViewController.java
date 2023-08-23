package ps.frontend.desktop.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import java.awt.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import ps.frontend.desktop.App;
import ps.frontend.desktop.models.Driver;
import ps.frontend.desktop.models.Supervisor;
import ps.frontend.desktop.models.TicketRequest;
import ps.frontend.desktop.models.TicketRequestResponse;
import ps.frontend.desktop.models.TicketType;
import ps.frontend.desktop.models.Transaction;
import ps.frontend.desktop.models.Transporter;
import ps.frontend.desktop.models.User;
import ps.frontend.desktop.models.UserTicket;

public class SupervisorFirstViewController implements Initializable{

    private Stage stage;
    private Scene scene;
    
    @FXML
    private AnchorPane mainForm, usersForm, userForm, transactionsForm, driversForm, requestsForm, responsesForm;

    @FXML
    private Label supervisorWelcomeNameLabel, usersInfoLabel, userNameLabel, userLastNameLabel, creditLabel, transactionsInfoLabel, driversInfoLabel,
    pickedRequestLabel, requestInfoLabel, documentationInfoLabel, documentInfoLabel;

    @FXML
    private TextField creditInputField, userFirstNameField, userLastNameField, userEmailField, timeAfterField, timeFromField, timeUntilField,
    userIdField, supervisorIdField, terminalIdField;

    @FXML
    private Button usersButton, transactionsButton, driversButton, requestsButton, ticketResponseButton, searchDocButton;

    @FXML
    private TableColumn<User, String> emailColumn, nameColumn, lastNameColumn, creditColumn;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private RadioButton activeDrivers, inactiveDrivers;

    @FXML
    private TextField driversNameField, driversLastNameField, driversPinField, driversJMBField, documentNameField;

    @FXML
    private TableView userTicketsTable, transactionsTable, driversTable, ticketRequestsTable, ticketResponsesTable;

    @FXML
    private TableColumn ticketNameColumn, usageColumn, validUntilColumn;

    @FXML
    private TableColumn driversNameColumn, driversLastNameColumn, driversJMBColumn, driversPinColumn, driversStatusColumn;

    @FXML
    private TableColumn transactionsEmailColumn, requestIdColumn, requestsUserColumn, requestTimeColumn, requestsTicketColumn;

    @FXML
    private TableColumn transactionsValueColumn, responseIdColumn, requestStatusColumn, requestedTicketColumn;

    @FXML
    private TableColumn transactionsTimestampColumn;

    @FXML
    private TableColumn transactionsTerminalColumn;

    @FXML
    private TableColumn transactionsSupervisorColumn;

    @FXML
    private ChoiceBox<String> transactionsTransporterChoiceBox;

    @FXML
    private TextArea commentArea, responseCommentArea;

    @FXML
    private ImageView userImage;

    private String token = null;
    private int allTransactionsPage = 0;
    private int transactionsBetweenPage = 0;
    private int transactionsSupervisorIdPage = 0;
    private int transactionsTerminalIdPage = 0;
    private int transactionsTransporterIdPage = 0;
    private int transactionsUserIdPage = 0;
    private int usersPage = 0;
    private int driversPage = 0;
    private int activeDriversPage = 0;
    private int inactiveDriversPage = 0;
    private int supervisorId;
    private int requestsPage = 0;
    private int responsesPage = 0;
    private Supervisor supervisor = null;
    private List<User> users = null;
    private List<Transporter> transporters = null;
    private List<TicketType> tickets = null;
    private User pickedUser = null;
    private TicketRequest pickedRequest = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        token = LoginViewController.loggedToken;
        supervisor = getSupervisor(getIdFromAuthToken(token));
        supervisorWelcomeNameLabel.setText(supervisor.getFirstName() + " " + supervisor.getFirstName());
        supervisorId = supervisor.getId();
        mainForm.setVisible(true);
        usersForm.setVisible(false);
        transactionsForm.setVisible(false);
        driversForm.setVisible(false);
        requestsForm.setVisible(false);
        responsesForm.setVisible(false);
        initializeUsersForm();
        transporters = getAllTransporters();  
        tickets = getAllTickets();
    }

    public void initializeResponsesForm(){

        responsesPage = 0;
        responseCommentArea.setText("");

        responseIdColumn.setCellValueFactory(new PropertyValueFactory<TicketRequestResponse, String>("id"));

        requestStatusColumn.setCellValueFactory(new Callback<CellDataFeatures<TicketRequestResponse, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<TicketRequestResponse, String> cellData) {
                TicketRequestResponse response = cellData.getValue();
                if(response.getApproved())
                    return new SimpleStringProperty("Prihvaćen");
                else
                    return new SimpleStringProperty("Odbijen");
            }
        });
        requestedTicketColumn.setCellValueFactory(new PropertyValueFactory<TicketRequestResponse, String>("ticketRequestId"));

        ticketResponsesTable.setRowFactory(tv -> {
            TableRow<TicketRequestResponse> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    TicketRequestResponse response = row.getItem();
                    showResponse(response);
                }
            });
            return row;
        });

        ticketResponsesTable.getItems().clear();
        ticketResponsesTable.getItems().addAll(getTicketResponses(responsesPage));
    }

    private void showResponse(TicketRequestResponse response){

        responseCommentArea.setText(response.getComment());
    }

    public void getNextResponsesPage(ActionEvent event){

        List<TicketRequestResponse> responses = getTicketResponses(responsesPage + 1);
        if(responses.size() != 0){
            ++responsesPage;
            ticketResponsesTable.getItems().clear();
            ticketResponsesTable.getItems().setAll(responses);
        }
    }

    public void getPreviousResponsesPage(ActionEvent event){

        if(responsesPage != 0){
            ticketResponsesTable.getItems().clear();
            ticketResponsesTable.getItems().setAll(getTicketResponses(--responsesPage));
        }
    }
    
    public void searchDocument(ActionEvent event) throws Exception{

        if(documentNameField.getText().isEmpty())
            documentInfoLabel.setText("Morate ukucati naziv dokumenta");
        else{
            byte[] documentBytes = getDocument(pickedUser, documentNameField.getText());
            if(documentBytes != null){
                String userId = String.valueOf(pickedUser.getId());
                String fileName = documentNameField.getText() + ".pdf";
                String filePath = "AdminSupervisor\\fx\\src\\main\\resources\\" + userId + "\\" + fileName;
                savePDFToFile(documentBytes, filePath);
                openPDFWithDesktop(filePath);
            }
            else
                documentInfoLabel.setText("Korisnik nema traženi dokument");
        }
    }

    public static void openPDFWithDesktop(String filePath) throws IOException {
        File file = new File(filePath);
        
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        }
    }

    public static void savePDFToFile(byte[] pdfBytes, String filePath) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(pdfBytes);
        }
    }

    private byte[] getDocument(User user, String documentName){

        URL url;
        try {
            documentName = documentName.replace(" ", "%20");
            url = new URL(App.BASE_URL + "user/files/get/document&userId=" + user.getId() + "&DocumentName=" + documentName);
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

    private List<TicketRequestResponse> getTicketResponses(int pageNumber){
    
        URL url;
        try {
            url = new URL(App.BASE_URL + "ticketRequests/getTicketResponseBySupervisorId=" + supervisorId + "/pagesize=" + pageNumber + "size=30");
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
                List<TicketRequestResponse> responses = gson.fromJson(response.toString(), new TypeToken<List<TicketRequestResponse>>(){}.getType());
                return responses;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }
    
    private List<TicketType> getAllTickets(){

        URL url;
        try {
            url = new URL(App.BASE_URL + "tickets/getInUseTicketsForTransporter/" + supervisor.getTransporterId());

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

    public void initializeRequestsForm(){

        requestInfoLabel.setText("");
        commentArea.setText("");
        requestsPage = 0;
        requestIdColumn.setCellValueFactory(new PropertyValueFactory<TicketRequest, String>("id"));

        requestsUserColumn.setCellValueFactory(new Callback<CellDataFeatures<TicketRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<TicketRequest, String> cellData) {
                TicketRequest request = cellData.getValue();
                return new SimpleStringProperty(getUserEmailById(request.getUserId()));
            }
        });
        requestTimeColumn.setCellValueFactory(new PropertyValueFactory<TicketRequest, String>("dateTime"));

        requestsTicketColumn.setCellValueFactory(new Callback<CellDataFeatures<TicketRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<TicketRequest, String> cellData) {
                TicketRequest request = cellData.getValue();
                return new SimpleStringProperty(getTicketNameFromId(request.getTicketTypeId()));
            }
        });

        ticketRequestsTable.setRowFactory(tv -> {
            TableRow<TicketRequest> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    TicketRequest request = row.getItem();
                    pickedRequest = request;
                    showRequest(request);
                }
            });
            return row;
        });

        ticketRequestsTable.getItems().clear();
        ticketRequestsTable.getItems().addAll(getTicketRequests(requestsPage));
    }

    private void showRequest(TicketRequest request){

        pickedRequestLabel.setText(request.toString());
        documentationInfoLabel.setText("Potrebna dokumentacija: " + getDocumentationInfo(request.getTicketTypeId()));
    }

    private String getDocumentationInfo(int id){
        for(int i = 0; i < tickets.size(); i++)
            if(tickets.get(i).getId() == id)
                return tickets.get(i).getDocumentaionName();

        return null;
    }

    public void approveRequest(ActionEvent event){

        if(pickedRequest == null)
            requestInfoLabel.setText("Nije odabran nijedan zahtjev");
        else
            sendTicketRequestResponse(new TicketRequestResponse(commentArea.getText(), pickedRequest.getId(), true, supervisorId));
    }

    public void declineRequest(ActionEvent event){

        if(pickedRequest == null)
            requestInfoLabel.setText("Nije odabran nijedan zahtjev");
        else
            sendTicketRequestResponse(new TicketRequestResponse(commentArea.getText(), pickedRequest.getId(), false, supervisorId));
    }

    private void sendTicketRequestResponse(TicketRequestResponse ticketRequestResponse){
    
        URL url;
        try {
            url = new URL(App.BASE_URL + "ticketRequests/addTicketResponse");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            Gson gson = new Gson();
		    String jsonResponse =  gson.toJson(ticketRequestResponse);

            OutputStream outputStream = conn.getOutputStream();
	        outputStream.write(jsonResponse.getBytes("UTF-8"));
	        outputStream.close();

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                initializeRequestsForm();
                requestInfoLabel.setText("");
                documentationInfoLabel.setText("");
                pickedRequestLabel.setText("");
                pickedRequest = null;
                requestInfoLabel.setText("Zahtjev uspješno obrađen");
            }
            else
                requestInfoLabel.setText("Greška pri obradi zahtjeva");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
    }

    public void getNextRequestsPage(ActionEvent event){

        List<TicketRequest> requests = getTicketRequests(requestsPage + 1);
        if(requests.size() != 0){
            ++requestsPage;
            ticketRequestsTable.getItems().clear();
            ticketRequestsTable.getItems().setAll(requests);
        }
    }

    public void getPreviousRequestsPage(ActionEvent event){

        if(requestsPage != 0){
            ticketRequestsTable.getItems().clear();
            ticketRequestsTable.getItems().setAll(getTicketRequests(--requestsPage));
        }
    }
    
    private List<TicketRequest> getTicketRequests(int pageNumber){
    
        URL url;
        try {
            url = new URL(App.BASE_URL + "ticketRequests/getTicketRequests&SupervisorId=" + supervisorId + "/pagesize=" + pageNumber + "size=30");
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
                List<TicketRequest> requests = gson.fromJson(response.toString(), new TypeToken<List<TicketRequest>>(){}.getType());
                return requests;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private String getTicketNameFromId(int id){

        for(int i = 0; i < tickets.size(); i++)
            if(tickets.get(i).getId() == id)
                return tickets.get(i).getName();
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
        transactionsSupervisorColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("supervisorId"));
        
        List<String> transporterNames = new ArrayList<>();
        for(int i = 0; i < transporters.size(); i++)
            transporterNames.add(transporters.get(i).getName());
        transactionsTransporterChoiceBox.getItems().clear();
        transactionsTransporterChoiceBox.getItems().addAll(transporterNames);
    }

    private Supervisor getSupervisor(Integer id){

        URL url;
        try {
            url = new URL(App.BASE_URL + "supervisors/getBySupervisorId=" + id);
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
                Supervisor supervisor = gson.fromJson(response.toString(), Supervisor.class);
                return supervisor;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    public void initializeDriversForm(){

        driversInfoLabel.setText("");
        activeDrivers.setSelected(false);
        inactiveDrivers.setSelected(false);
        driversPage = 0;
        driversNameColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("firstname"));
        driversLastNameColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("lastname"));
        driversJMBColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("jmb"));
        driversPinColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("Pin"));
        driversStatusColumn.setCellValueFactory(new Callback<CellDataFeatures<Driver, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Driver, String> cellData) {
                Driver Driver = cellData.getValue();
        
                if (Driver.getIsActive()) {
                    return new SimpleStringProperty("Aktivan");
                } else {
                    return new SimpleStringProperty("Neaktivan");
                }
            }
        });
        List<Driver> drivers = this.getAllDrivers(driversPage);
        this.driversTable.getItems().setAll(drivers);

        driversTable.setRowFactory(tv -> {
            TableRow<Driver> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Driver driver = row.getItem();
                    changeDriverStatus(driver);
                }
            });
            return row;
        });
    }

    public void registerDriver(ActionEvent event){

        if(driversNameField.getText().isEmpty() || driversLastNameField.getText().isEmpty() || driversPinField.getText().isEmpty() ||
        driversJMBField.getText().isEmpty())
            driversInfoLabel.setText("Sva polja moraju biti unesena.");
        else{
            Driver driver = new Driver(driversPinField.getText(), driversNameField.getText(), driversLastNameField.getText(), 
            driversJMBField.getText(), supervisor.getTransporterId());
            URL url;
            try {
                url = new URL(App.BASE_URL + "pinusers/register/driverBySupervisorId=" + supervisorId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                Gson gson = new Gson();
                String jsonDriver =  gson.toJson(driver);

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(jsonDriver.getBytes("UTF-8"));
                outputStream.close();

                int responseCode = conn.getResponseCode();
                if(responseCode == 200){
                    
                    driversInfoLabel.setText("Registracija uspjesna.");
                    initializeDriversForm();
                }
                else
                    driversInfoLabel.setText("Registracija neuspjesna.");
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void nextDriversPage(ActionEvent event){

        List<Driver> drivers = null;
        if(!activeDrivers.isSelected() && !inactiveDrivers.isSelected()){
            drivers = getAllDrivers(driversPage + 1);
            if(drivers.size() != 0){
                ++driversPage;
                driversTable.getItems().clear();
                driversTable.getItems().setAll(drivers);
            }
        }
        else{
            if(activeDrivers.isSelected()){
                drivers = getFilteredDrivers(true, activeDriversPage + 1);
                if(drivers.size() != 0){
                    ++activeDriversPage;
                    driversTable.getItems().clear();
                    driversTable.getItems().setAll(drivers);
                }
            }
            else{
                drivers = getFilteredDrivers(false, inactiveDriversPage + 1);
                if(drivers.size() != 0){
                    ++inactiveDriversPage;
                    driversTable.getItems().clear();
                    driversTable.getItems().setAll(drivers);
                }
            }
        }

    }

    public void previousDriversPage(ActionEvent event){

        if(!activeDrivers.isSelected() && !inactiveDrivers.isSelected())
            if(driversPage != 0){
                driversTable.getItems().clear();
                driversTable.getItems().setAll(getAllDrivers(--driversPage));
            }
        else{
            if(activeDrivers.isSelected()){
                if(activeDriversPage != 0){
                    driversTable.getItems().clear();
                    driversTable.getItems().setAll(getFilteredDrivers(true, --activeDriversPage));
                }
            }
            else{
                if(inactiveDriversPage != 0){
                    driversTable.getItems().clear();
                    driversTable.getItems().setAll(getFilteredDrivers(false, --inactiveDriversPage));
                }
            }
        }
    }

    public void showFilteredDrivers(ActionEvent event){

        List<Driver> filteredDrivers = null;
        if(activeDrivers.isSelected()){
            activeDriversPage = 0;
            filteredDrivers = getFilteredDrivers(true, activeDriversPage);
        }
        else if(inactiveDrivers.isSelected()){
            inactiveDriversPage = 0;
            filteredDrivers = getFilteredDrivers(false, inactiveDriversPage); 
        }
        if(filteredDrivers != null){
            driversTable.getItems().clear();
            driversTable.getItems().setAll(filteredDrivers);
        }
    }

    private void changeDriverStatus(Driver driver){

        boolean status = true;
        if(driver.getIsActive())
            status = false;
        URL url;
        try {
            url = new URL(App.BASE_URL + "pinusers/drivers/ChangeisActiveDriverId=" + driver.getId() + "andIsActive=" + status + "andSupervisorId=" + supervisorId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestProperty("Authorization", "Bearer " + token);
	        conn.setRequestMethod("POST");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                driversInfoLabel.setText("Status vozača uspjesno promjenjen.");
                initializeDriversForm();
            }
            else
                driversInfoLabel.setText("Status vozača neuspjesno promjenjen.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Driver> getAllDrivers(int pageNumber) {
        
        URL url;
        try {
            url = new URL(App.BASE_URL + "pinusers/drivers/getDriversBySupervisorId=" + supervisorId + "/pagesize=" + pageNumber + "size=22");
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
                List<Driver> drivers = gson.fromJson(response.toString(), new TypeToken<List<Driver>>(){}.getType());
                return drivers;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
        return null;
    }

    private List<Driver> getFilteredDrivers(boolean status, int pageNumber) {
        
        URL url;
        try {
            if(status)
                url = new URL(App.BASE_URL + "pinusers/drivers/getActiveDriversBySupervisorId=" + supervisorId + "/pagesize=" + pageNumber + "size=22");
            else
                url = new URL(App.BASE_URL + "pinusers/drivers/getInactiveDriversBySupervisorId=" + supervisorId + "/pagesize=" + pageNumber + "size=22");

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
                List<Driver> drivers = gson.fromJson(response.toString(), new TypeToken<List<Driver>>(){}.getType());
                return drivers;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
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

    private String getUserEmailById(Integer userId){
        for(int i = 0; i < users.size(); i++)
            if(users.get(i).getId() == userId)
                return users.get(i).getEmail();
        return null;
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

    public void initializeUsersForm(){

        usersInfoLabel.setText("");
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
                    pickedUser = user;
                    showUser(user);
                }
            });
            return row;
        });
    }

    public void addCredit(ActionEvent event){

        if(creditInputField.getText().isEmpty())
            usersInfoLabel.setText("Morate popuniti polje za kredit");
        else{
            try{
                BigDecimal credit = new BigDecimal(creditInputField.getText());

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Potvrda uplate kredita");
                alert.setHeaderText("Potvrdi uplatu");
                alert.setContentText("Da li se želite uplatiti uneseni kredit?");
        
                Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                yesButton.setText("Da");
        
                Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
                noButton.setText("Ne");
        
                alert.showAndWait().ifPresent(result -> {
                    if (result == ButtonType.OK) {
                        URL url;
                        try {
                            url = new URL(App.BASE_URL + "users/addCreditUserId=" + pickedUser.getId() + "andAmount=" + credit + "andSupervisorId=" + supervisorId);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            
                            conn.setRequestProperty("Authorization", "Bearer " + token);
                            conn.setRequestMethod("GET");

                            int responseCode = conn.getResponseCode();
                            if(responseCode == 200){
                                userForm.setVisible(false);
                                initializeUsersForm();
                            }
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }   
            catch(Exception e){
                usersInfoLabel.setText("Neispravan unos kredita");
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

    public void switchPane(ActionEvent event) throws ParseException{

        if (event.getSource() == usersButton) {
            usersForm.setVisible(true);
            transactionsForm.setVisible(false);
            mainForm.setVisible(false);
            userForm.setVisible(false);
            driversForm.setVisible(false);
            requestsForm.setVisible(false);
            responsesForm.setVisible(false);
            initializeUsersForm();
        }
        else if (event.getSource() == transactionsButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            transactionsForm.setVisible(true);
            driversForm.setVisible(false);
            requestsForm.setVisible(false);
            responsesForm.setVisible(false);
            initializeTransactionsForm();
        }
        else if (event.getSource() == driversButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            transactionsForm.setVisible(false);
            driversForm.setVisible(true);
            requestsForm.setVisible(false);
            responsesForm.setVisible(false);
            initializeDriversForm();
        }
        else if (event.getSource() == requestsButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            transactionsForm.setVisible(false);
            driversForm.setVisible(false);
            responsesForm.setVisible(false);
            requestsForm.setVisible(true);
            initializeRequestsForm();
        }
        else if (event.getSource() == ticketResponseButton) {
            mainForm.setVisible(false);
            usersForm.setVisible(false);
            transactionsForm.setVisible(false);
            driversForm.setVisible(false);
            responsesForm.setVisible(true);
            requestsForm.setVisible(false);
            initializeResponsesForm();
        }
    }

    public Integer getIdFromAuthToken(String bearerToken) {

        bearerToken = bearerToken.substring(7);
        String[] chunks = bearerToken.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();

        return id;
    }
}
