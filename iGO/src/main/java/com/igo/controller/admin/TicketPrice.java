package com.igo.controller.admin;

import com.igo.models.fares.Cost;
import com.igo.models.person.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * @author mkjodhani
 * @project
 * @since 23/03/23
 */
public class TicketPrice {
    @FXML
    ComboBox<Cost.PERIOD> ticketTypeComboBox;
    @FXML
    TextField price;
    public void initialize(){
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
            return;
        }
        double priceValue = Double.valueOf(price.getText());
        Cost.updateTicketPriceByTimePeriod(ticketType,priceValue);
    }
    public void fetch(ActionEvent actionEvent) {
        Cost.PERIOD ticketType = ticketTypeComboBox.getValue();
        if (ticketType == null){
            return;
        }
        double priceValue =  Cost.getTicketPriceByTimePeriod(ticketType);
        price.setText(String.valueOf(priceValue));
    }
}
