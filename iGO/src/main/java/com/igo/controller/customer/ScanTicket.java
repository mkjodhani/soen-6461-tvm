package com.igo.controller.customer;

import com.igo.IGoApplication;
import com.igo.models.data.Data;
import com.igo.models.ticket.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class ScanTicket {
    @FXML
    TextField ticketID;

    public void scanTicket(ActionEvent actionEvent) {
        try{
            Ticket ticket = Data.getReference().getTicketHashMap().getOrDefault(Integer.parseInt(ticketID.getText()),null);
            if (ticket == null){
                IGoApplication.showDialogBox("Error!","Ticket has not been issued before!","You can try with valid ticket number.");
            }
            else {
                if (ticket.isUsed()){
                    IGoApplication.showDialogBox("Error!","Ticket has been used before!","You need to purchase new ticket to use the system.");
                }
                else {
                    ticket.scan();
                    IGoApplication.showDialogBox("Success!","Ticket has been scanned successfully!","You can access the metro.Happy Journey!!");
                }
            }
        }catch (Exception e){
            IGoApplication.showDialogBox("Error!","Something went wrong!",e.getMessage());
        }
    }
}
