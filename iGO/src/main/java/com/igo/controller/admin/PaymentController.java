package com.igo.controller.admin;

import com.igo.IGoApplication;
import com.igo.models.data.Data;
import com.igo.models.fares.Cost;
import com.igo.models.localization.Language;
import com.igo.models.opus.OPUS;
import com.igo.models.payment.Card;
import com.igo.models.ticket.Ticket;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
 * @since 24/03/23
 */
public class PaymentController implements Observer {
    public Label paymentDetailsLabel;
    public Label cardHolderName;
    public Label cardNumberLabel;
    public Label expirationDate;
    public Button makePaymentButton;
    public Label cardTypeLabel;
    Cost.TYPES paymentFor;
    Cost.PERIOD period;
    int opusID;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField expirationMonth;
    @FXML
    private TextField expirationYear;
    @FXML
    private TextField cvv;

    @FXML
    private ComboBox<Card.TYPES> cardType;

    Button closeParentButton = new Button();
    public void initialize(){
        cardType.setItems(FXCollections.observableArrayList(Card.TYPES.values()));
    }
    public void setPaymentFor(Cost.TYPES paymentFor) {
        this.paymentFor = paymentFor;
    }

    public void setPeriod(Cost.PERIOD period) {
        this.period = period;
    }

    public void setOpusID(int opusID) {
        this.opusID = opusID;
    }

    private boolean validate(){
       String cardNumberString = cardNumber.getText();
       String month = expirationMonth.getText();
       String year = expirationYear.getText();
       Card.TYPES cardTypeValue =  cardType.getValue();
       String cvvText = cvv.getText();
       if (cardTypeValue == null){
           return false;
       }
       else if (cardNumberString.equals("") || month.equals("") || year.equals("") || cvvText.equals("")){
           return false;
       }
       return true;
    }
    public void makePayment(ActionEvent actionEvent) {
        try{
            if (!validate()){
                IGoApplication.showErrorDialogBox("Please provide valid information!","One of the field is not correct.");
                return;
            }
            if (paymentFor == Cost.TYPES.TICKET){
                Card card = Card.getCardByDetails(cardNumber.getText(),String.format("%s/%s",expirationMonth.getText(),expirationYear.getText()),Integer.valueOf(cvv.getText()), cardType.getValue());
                if (card == null){
                    IGoApplication.showErrorDialogBox("Please provide valid information!","You have entered all the details but the details are incorrect.Please try again!");
                    return;
                }
                Ticket ticket = Ticket.generateTicketByCard("H3W",period, card);
                IGoApplication.showDialogBox("Success!","Ticket has been generated successfully!","You can refer the generated by #"+ticket.getTicketId()+".");
                closeParentButton.fire();
                Data.getReference().notifyAllObservers();
            }
            else {
                OPUS opus = Data.getReference().getOpusHashMap().get(opusID);
                System.out.println(opusID);
                opus.recharge(period);
                closeParentButton.fire();
                IGoApplication.showDialogBox("Success!","Recharge for opus has been done successfully!","Information for opus #"+opus.getCardId()+" has been updated.");
            }
        }catch (Exception e){
            e.printStackTrace();
            IGoApplication.showErrorDialogBox("Something went wrong!",e.getMessage());
        }
    }

    public Button getCloseParentButton() {
        return closeParentButton;
    }

    private void updateLabels(){
        Platform.runLater(() ->{
            paymentDetailsLabel.setText(Language.getReference().getLabel("paymentDetailsLabel"));
            cardHolderName.setText(Language.getReference().getLabel("cardHolderName"));
            cardNumberLabel.setText(Language.getReference().getLabel("cardNumber"));
            expirationDate.setText(Language.getReference().getLabel("expirationDate"));
            makePaymentButton.setText(Language.getReference().getLabel("makePayment"));
            cardTypeLabel.setText(Language.getReference().getLabel("cardType"));
        });
    }
    @Override
    public void update(Observable o, Object arg) {
        updateLabels();
    }
}