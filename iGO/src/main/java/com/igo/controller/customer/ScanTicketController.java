package com.igo.controller.customer;

import com.igo.IGoApplication;
import com.igo.models.tvm.TVM;
import com.igo.models.localization.Language;
import com.igo.models.ticket.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class ScanTicketController implements Observer {
    public Label ticketDetailsLabel;
    public Label ticketIdLabel;
    @FXML
    TextField ticketID;
    Button closeParentButton = new Button();
    public void initialize() {
        Language.getReference().addObserver(this);
        updateLabels();
    }
    public void scanTicket(ActionEvent actionEvent) {
        try{
            if (ticketID.getText().equals("")){
                IGoApplication.showErrorDialogBox("Please provide valid information!","Ticket is not provided.");
                return;
            }
            Ticket ticket = TVM.getReference().getTicketHashMap().getOrDefault(Integer.parseInt(ticketID.getText()),null);
            if (ticket == null){
                IGoApplication.showErrorDialogBox("Ticket has not been issued before!","You can try with valid ticket number.");
            }
            else {
                if (ticket.isUsed()){
                    IGoApplication.showErrorDialogBox("Ticket has been used before!","You need to purchase new ticket to use the system.");
                }
                else {
                    ticket.scan();
                    IGoApplication.showDialogBox("Success!","Ticket has been scanned successfully!","You can access the metro.Happy Journey!!");
                    closeParentButton.fire();
                }
            }
        }catch (Exception e){
            IGoApplication.showErrorDialogBox("Something went wrong!",e.getMessage());
        }
    }
    public Button getCloseParentButton() {
        return closeParentButton;
    }
    private void updateLabels(){
        ticketDetailsLabel.setText(Language.getReference().getLabel("ticketDetails"));
        ticketIdLabel.setText(Language.getReference().getLabel("ticketId"));
    }
    @Override
    public void update(Observable o, Object arg) {
        updateLabels();
    }
}
