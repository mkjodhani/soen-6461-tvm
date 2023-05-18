package com.igo.controller.admin;

import com.igo.IGoApplication;
import com.igo.models.tvm.TVM;
import com.igo.models.fares.Cost;
import com.igo.models.localization.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @project
 * @since 23/03/23
 */
public class TicketPriceController implements Observer {
    public Label ticketPriceDetailsLabel;
    public Label ticketTypeLabel;
    public Label priceLabel;
    public Button updatePriceButton;
    @FXML
    ComboBox<Cost.PERIOD> ticketTypeComboBox;
    @FXML
    TextField price;
    Button closeParentButton = new Button();
    public void initialize(){
        Language.getReference().addObserver(this);
        updateLabels();
        ticketTypeComboBox.getItems().add(Cost.PERIOD.ONE_TRIP);
        ticketTypeComboBox.getItems().add(Cost.PERIOD.TWO_TRIPS);
        ticketTypeComboBox.getItems().add(Cost.PERIOD.ONE_DAY);
        ticketTypeComboBox.getItems().add(Cost.PERIOD.THREE_DAYS);
        ticketTypeComboBox.getItems().add(Cost.PERIOD.UN_LIMITED_WEEKEND);
        ticketTypeComboBox.getItems().add(Cost.PERIOD.GROUP);
    }
    public void update(ActionEvent actionEvent) {
        Cost.PERIOD ticketType = ticketTypeComboBox.getValue();
        if (ticketType == null){
            IGoApplication.showErrorDialogBox("Please provide valid information!","One of the field is empty.");
            return;
        }
        double priceValue = Double.valueOf(price.getText());
        Cost.updateTicketPriceByTimePeriod(ticketType,priceValue);
        TVM.getReference().notifyAllObservers();
        closeParentButton.fire();
        IGoApplication.showDialogBox("Success!","Operation performed successfully!","Price has been updated!");
    }
    public void fetch(ActionEvent actionEvent) {
        Cost.PERIOD ticketType = ticketTypeComboBox.getValue();
        if (ticketType == null){
            return;
        }
        double priceValue =  Cost.getTicketPriceByTimePeriod(ticketType);
        price.setText(String.valueOf(priceValue));
    }
    public Button getCloseParentButton() {
        return closeParentButton;
    }
    private void updateLabels(){
        ticketPriceDetailsLabel.setText(Language.getReference().getLabel("ticketPriceDetailsLabel"));
        ticketTypeLabel.setText(Language.getReference().getLabel("ticketType"));
        priceLabel.setText(Language.getReference().getLabel("price"));
        updatePriceButton.setText(Language.getReference().getLabel("update"));
    }
    @Override
    public void update(Observable o, Object arg) {
        updateLabels();
    }
}
