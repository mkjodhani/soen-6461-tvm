package com.igo.controller.customer;

import com.igo.IGoApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author mkjodhani
 * @project
 * @since 21/03/23
 */
public class CustomerController {
    private static Stage customerStage;
    @FXML public Button newOpusCardButton;
    @FXML public Button rechargeOPUSButton;
    @FXML public Button purchaseTicketButton;
    @FXML public Button scanTicketButton;

    public void onCreateOPUS(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/create-opus.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        CustomerController.setCustomerStage(stage);
        stage.setTitle("Register OPUS");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void rechargeOPUS(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/recharge-opus.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        CustomerController.setCustomerStage(stage);
        stage.setTitle("Recharge OPUS");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void purchaseTicket(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/purchase-ticket.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        CustomerController.setCustomerStage(stage);
        stage.setTitle("Purchase Ticket");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void scanTicket(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/scan-ticket.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        CustomerController.setCustomerStage(stage);
        stage.setTitle("Scan Ticket");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static Stage getCustomerStage() {
        return customerStage;
    }

    public static void setCustomerStage(Stage customerStage) {
        CustomerController.customerStage = customerStage;
    }
}
