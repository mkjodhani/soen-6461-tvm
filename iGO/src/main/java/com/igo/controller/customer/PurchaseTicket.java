package com.igo.controller.customer;

import com.igo.IGoApplication;
import com.igo.models.data.Data;
import com.igo.models.fares.Cost;
import com.igo.models.ticket.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class PurchaseTicket {

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
    public void purchaseTicket(ActionEvent actionEvent) {
        try {
            Ticket ticket = Ticket.generateTicketByCash("H3W",ticketTypeComboBox.getValue());
            IGoApplication.showDialogBox("Success!","Ticket has been genearted!","The generated ticket will be referred as #"+ticket.getTicketId()+".");
        }catch (Exception e){
            IGoApplication.showDialogBox("Success!","Ticket can bot be generated!",e.getMessage());
        }
    }

    public void fetch(ActionEvent actionEvent) {
//        double price = ;
//        System.out.println(price);
        price.setText(String.format("$%.2f",Cost.getTicketPriceByTimePeriod(ticketTypeComboBox.getValue())));
    }
}
