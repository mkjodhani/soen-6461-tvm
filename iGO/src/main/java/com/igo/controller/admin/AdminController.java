package com.igo.controller.admin;

import com.igo.IGoApplication;
import com.igo.models.data.Data;
import com.igo.models.localization.Language;
import com.igo.models.opus.OPUS;
import com.igo.models.person.Customer;
import com.igo.models.ticket.Ticket;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class AdminController implements Observer {
    public Tab customerTab;
    public Tab opusCardTab;
    public Tab ticketsTab;
    public Menu customerMenu;
    public Menu priceMenu;
    public Menu helpMenu;
    public MenuItem registerCustomerMenu;
    public MenuItem opusPriceMenu;
    public MenuItem ticketPriceMenu;
    public MenuItem aboutMenu;
    @FXML
    private VBox customerList;
    @FXML
    private VBox opusList;
    @FXML
    private VBox ticketList;

    public void initialize(){
        Data.getReference().addObserver(this);
        Language.getReference().addObserver(this);
        initializeCustomerList();
        initializeTicketList();
        initializeOpusList();
        updateLabels();
    }
    public void onRegisterCustomer(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/admin/register-customer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        RegisterCustomerController registerCustomerController = fxmlLoader.getController();
        registerCustomerController.getCloseParentButton().setOnAction(e ->{
            stage.close();
        });
        stage.setTitle("Register Customer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onChangeOpusPrice(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/admin/opus-price.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        OpusPriceController opusPriceController = fxmlLoader.getController();
        opusPriceController.getCloseParentButton().setOnAction(e->{
            stage.close();
        });
        stage.setTitle("Change OPUS Plan Price");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onChangeTicketPrice(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/admin/ticket-price.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        TicketPriceController ticketPriceController = fxmlLoader.getController();
        ticketPriceController.getCloseParentButton().setOnAction(e ->{
            stage.close();
        });
        stage.setTitle("Change Ticket Price");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initializeCustomerList(){
        try{
            TableView tableView = new TableView();

            TableColumn<Customer, String> userIDCol = new TableColumn<>(Language.getReference().getLabel("customerIdColumn"));
            userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

            TableColumn<Customer, String> firstNameCol = new TableColumn<>(Language.getReference().getLabel("firstName"));
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<Customer, String> lastNameCol = new TableColumn<>(Language.getReference().getLabel("lastName"));
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<Customer, String> customerTypeCol = new TableColumn<>(Language.getReference().getLabel("customerType"));
            customerTypeCol.setCellValueFactory(new PropertyValueFactory<>("customerType"));

            TableColumn<Customer, String> birthDateCol = new TableColumn<>(Language.getReference().getLabel("birthDate"));
            birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));


            tableView.getColumns().add(userIDCol);
            tableView.getColumns().add(firstNameCol);
            tableView.getColumns().add(lastNameCol);
            tableView.getColumns().add(birthDateCol);
            ObservableList<Customer> people = FXCollections.observableArrayList(Data.getReference().getCustomerHashMap().values());
            tableView.setItems(people);
            tableView.setPlaceholder(new Label(Language.getReference().getLabel("noCustomerFoundMessage")));
            if(customerList.getChildren().size()>0){
                customerList.getChildren().removeAll(customerList.getChildren());
            }
            customerList.getChildren().add(tableView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initializeTicketList(){
        try{
            TableView tableView = new TableView();

            TableColumn<Ticket, String> ticketIdCol = new TableColumn<>(Language.getReference().getLabel("ticketId"));
            ticketIdCol.setCellValueFactory(new PropertyValueFactory<>("ticketId"));

            TableColumn<Ticket, String> areaCodeCol = new TableColumn<>(Language.getReference().getLabel("areaCode"));
            areaCodeCol.setCellValueFactory(new PropertyValueFactory<>("areaCode"));

            TableColumn<Ticket, String> priceCol = new TableColumn<>(Language.getReference().getLabel("price"));
            priceCol.setCellValueFactory(cellValue->new SimpleStringProperty(String.format("$ %.2f",cellValue.getValue().getPrice())) );

            TableColumn<Ticket, String> ticketTypeCol = new TableColumn<>(Language.getReference().getLabel("ticketType"));
            ticketTypeCol.setCellValueFactory(new PropertyValueFactory<>("ticketType"));

            TableColumn<Ticket, String> cashPaymentCol = new TableColumn<>(Language.getReference().getLabel("cashPayment"));
            cashPaymentCol.setCellValueFactory(new PropertyValueFactory<>("cashPayment"));

            tableView.getColumns().add(ticketIdCol);
            tableView.getColumns().add(areaCodeCol);
            tableView.getColumns().add(priceCol);
            tableView.getColumns().add(ticketTypeCol);
            tableView.getColumns().add(cashPaymentCol);

            tableView.setEditable(false);
            tableView.setPlaceholder(new Label(Language.getReference().getLabel("noTicketsFoundMessage")));
            ObservableList<Ticket> tickets = FXCollections.observableArrayList(Data.getReference().getTicketHashMap().values());
            tableView.setItems(tickets);
            if(ticketList.getChildren().size()>0){
                ticketList.getChildren().removeAll(ticketList.getChildren());
            }
            ticketList.getChildren().add(tableView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initializeOpusList(){
        try{
            TableView tableView = new TableView();

            TableColumn<OPUS, String> cardIdCol = new TableColumn<>(Language.getReference().getLabel("opusId"));
            cardIdCol.setCellValueFactory(new PropertyValueFactory<>("cardId"));

            TableColumn<OPUS, String> customerCol = new TableColumn<>(Language.getReference().getLabel("customerMenu"));
            customerCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer().getFirstName()+" " + cellData.getValue().getCustomer().getLastName()));

            TableColumn<OPUS, String> currentPlanCol = new TableColumn<>(Language.getReference().getLabel("currentPlan"));
            currentPlanCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCurrentPlan())));

            tableView.getColumns().add(cardIdCol);
            tableView.getColumns().add(customerCol);
            tableView.getColumns().add(currentPlanCol);


            ObservableList<OPUS> opuses = FXCollections.observableArrayList(Data.getReference().getOpusHashMap().values());
            tableView.setItems(opuses);
            tableView.setPlaceholder(new Label(Language.getReference().getLabel("noOpusMessage")));
            if(opusList.getChildren().size()>0){
                opusList.getChildren().removeAll(opusList.getChildren());
            }
            opusList.getChildren().add(tableView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() ->{
            initializeOpusList();
            initializeCustomerList();
            initializeTicketList();
            updateLabels();
        });
    }

    private void updateLabels(){
        customerTab.setText(Language.getReference().getLabel("customerTab"));
        opusCardTab.setText(Language.getReference().getLabel("opusCardTab"));
        ticketsTab.setText(Language.getReference().getLabel("ticketsTab"));
        customerMenu.setText(Language.getReference().getLabel("customerMenu"));
        priceMenu.setText(Language.getReference().getLabel("priceMenu"));
        helpMenu.setText(Language.getReference().getLabel("helpMenu"));
        registerCustomerMenu.setText(Language.getReference().getLabel("registerCustomerMenu"));
        opusPriceMenu.setText(Language.getReference().getLabel("opusPriceMenu"));
        ticketPriceMenu.setText(Language.getReference().getLabel("ticketPriceMenu"));
        aboutMenu.setText(Language.getReference().getLabel("aboutMenu"));
    }
}
