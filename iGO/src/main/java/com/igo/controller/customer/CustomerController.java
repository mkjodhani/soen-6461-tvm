package com.igo.controller.customer;

import com.igo.IGoApplication;
import com.igo.models.localization.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @project
 * @since 21/03/23
 */
public class CustomerController  implements Observer {
    @FXML public Button newOpusCardButton;
    @FXML public Button rechargeOPUSButton;
    @FXML public Button purchaseTicketButton;
    @FXML public Button scanTicketButton;
    public void initialize() {
        Language.getReference().addObserver(this);
        updateLabels();
    }
    public void onCreateOPUS(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/create-opus.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        RegisterOpusController registerOpusController = fxmlLoader.getController();
        registerOpusController.getCloseParentButton().setOnAction(e ->{
            stage.close();
        });
        stage.setTitle("Register OPUS");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void rechargeOPUS(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/recharge-opus.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        RechargeOpusController rechargeOpusController = fxmlLoader.getController();
        rechargeOpusController.getCloseParentButton().setOnAction(e ->{
            stage.close();
        });
        stage.setTitle("Recharge OPUS");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void purchaseTicket(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/purchase-ticket.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        PurchaseTicketController purchaseTicketController = fxmlLoader.getController();
        purchaseTicketController.getCloseParentButton().setOnAction(e ->{
            stage.close();
        });
        stage.setTitle("Purchase Ticket");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void scanTicket(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/scan-ticket.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        ScanTicketController scanTicketController = fxmlLoader.getController();
        scanTicketController.getCloseParentButton().setOnAction(e ->{
            stage.close();
        });
        stage.setTitle("Scan Ticket");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    private void updateLabels(){
        newOpusCardButton.setText(Language.getReference().getLabel("getNewOpusCard"));
        rechargeOPUSButton.setText(Language.getReference().getLabel("rechargeOpusCard"));
        purchaseTicketButton.setText(Language.getReference().getLabel("purchaseTicket"));
        scanTicketButton.setText(Language.getReference().getLabel("scanTicket"));
    }

    @Override
    public void update(Observable o, Object arg) {
        updateLabels();
    }
}
