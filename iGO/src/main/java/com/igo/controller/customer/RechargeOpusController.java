package com.igo.controller.customer;

import com.igo.IGoApplication;
import com.igo.controller.admin.PaymentController;
import com.igo.models.data.Data;
import com.igo.models.fares.Cost;
import com.igo.models.localization.Language;
import com.igo.models.opus.OPUS;
import com.igo.models.person.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class RechargeOpusController implements Observer {
    public Label opusIdLabel;
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label dateOfBirthLabel;
    public Label rechargeTypeLabel;
    public Label priceLabel;
    public Label customerTypeLabel;
    @FXML
    ComboBox<String> rechargeType;
    @FXML
    ComboBox<Customer.TYPES> customerType;
    @FXML
    TextField firstNameText;
    @FXML
    TextField lastNameText;
    @FXML
    DatePicker dobObject;
    @FXML
    TextField opusID;
    @FXML
    TextField price;
    Button closeParentButton = new Button();
    public void initialize(){
        Language.getReference().addObserver(this);
        updateLabels();
        customerType.getItems().add(Customer.TYPES.CHILD);
        customerType.getItems().add(Customer.TYPES.STUDENT);
        customerType.getItems().add(Customer.TYPES.ADULT);
        customerType.getItems().add(Customer.TYPES.SENIOR_CITIZEN);
        rechargeType.getItems().add("One Week");
        rechargeType.getItems().add("One Month");
        rechargeType.getItems().add("Four Months");
    }
    private Cost.PERIOD getRechargeType(String type){
        switch (type){
            case "One Week":
                return Cost.PERIOD.ONE_WEEK;
            case "One Month":
                return Cost.PERIOD.ONE_MONTH;
            case "Four Months":
                return Cost.PERIOD.FOUR_MONTH;
        }
        return null;
    }
    public void onSelectRecharge(ActionEvent actionEvent) {
        try {
            int opusIDValue = Integer.parseInt(opusID.getText());
            String rechargeTypeString = rechargeType.getValue();

            OPUS opus = Data.getReference().getOpusHashMap().getOrDefault(opusIDValue,null);
            if (opus == null || customerType == null){
                IGoApplication.showErrorDialogBox("No OPUS found!","Please provide valid opus ID.");
            }
            else{
                Customer customer = opus.getCustomer();
                firstNameText.setText(customer.getFirstName());
                lastNameText.setText(customer.getLastName());
                dobObject.setValue(customer.getBirthDate());
                customerType.setValue(customer.getCustomerType());
                double priceValue =  Cost.getOpusRechargeAmount(getRechargeType(rechargeTypeString),customer.getCustomerType());
                price.setText(String.valueOf(priceValue));
            }
        }
        catch (Exception e){
            IGoApplication.showErrorDialogBox("Something went wrong!",e.getMessage());
        }
    }

    public void rechargeOPUS(ActionEvent actionEvent) throws IOException {
        int opusIDValue = Integer.parseInt(opusID.getText());
        OPUS opus = Data.getReference().getOpusHashMap().getOrDefault(opusIDValue,null);
        Customer customer = opus.getCustomer();
        if (opus == null){
            IGoApplication.showErrorDialogBox("No OPUS found!","Please provide valid opus ID.");
            clearUserInput();
        }
        String rechargeTypeString = rechargeType.getValue();
        double priceValue =  Cost.getOpusRechargeAmount(getRechargeType(rechargeTypeString),customer.getCustomerType());
        if (priceValue == -1){
            IGoApplication.showErrorDialogBox("Plan can not be activated!","This plan is not provided to "+customer.getCustomerType()+".");
        }
        else {
            String paymentOptions[] = { "Cash","Cash less" };
            ChoiceDialog d = new ChoiceDialog(paymentOptions[0], paymentOptions);
            d.setHeaderText("Payment Method");
            d.setHeaderText("Select one of the payment method");
            Optional<String> result = d.showAndWait();
            if ( result.isPresent() )
            {
                if(d.getSelectedItem().equals("Cash")){
                    opus.recharge(getRechargeType(rechargeTypeString));
                    IGoApplication.showDialogBox("Success!","Plan has been activated!","This plan provided to "+customer.getCustomerType()+".");
                    Data.getReference().notifyAllObservers();
                    this.closeParentButton.fire();
                }
                else {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/admin/payment.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
                    PaymentController paymentController = fxmlLoader.getController();
                    paymentController.setPaymentFor(Cost.TYPES.OPUS);
                    paymentController.setOpusID(opus.getCardId());
                    paymentController.setPeriod(getRechargeType(rechargeTypeString));
                    paymentController.getCloseParentButton().setOnAction(e ->{
                        this.closeParentButton.fire();
                        stage.close();
                    });
                    stage.setTitle("Payment");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    Data.getReference().notifyAllObservers();
                }
            }
        }
    }

    public void fetchUser(ActionEvent actionEvent) {
        try{
            int opusIDValue = Integer.parseInt(opusID.getText());
            OPUS opus = Data.getReference().getOpusHashMap().getOrDefault(opusIDValue,null);
            if (opus == null){
                IGoApplication.showErrorDialogBox("No OPUS found!","Please provide valid opus ID.");
                clearUserInput();
            }
            else{
                Customer customer = opus.getCustomer();
                firstNameText.setText(customer.getFirstName());
                lastNameText.setText(customer.getLastName());
                dobObject.setValue(customer.getBirthDate());
                customerType.setValue(customer.getCustomerType());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            IGoApplication.showErrorDialogBox("Something went wrong!",e.getMessage());
        }
    }
    private void clearUserInput(){
        firstNameText.setText("");
        lastNameText.setText("");
        dobObject.setValue(null);
        customerType.setValue(null);
    }
    public Button getCloseParentButton() {
        return closeParentButton;
    }
    private void updateLabels(){
        opusIdLabel.setText(Language.getReference().getLabel("opusId"));
        firstNameLabel.setText(Language.getReference().getLabel("firstName"));
        lastNameLabel.setText(Language.getReference().getLabel("lastName"));
        dateOfBirthLabel.setText(Language.getReference().getLabel("birthDate"));
        rechargeTypeLabel.setText(Language.getReference().getLabel("opusRechargeTypeLabel"));
        priceLabel.setText(Language.getReference().getLabel("price"));
        customerTypeLabel.setText(Language.getReference().getLabel("customerType"));
    }
    @Override
    public void update(Observable o, Object arg) {
        updateLabels();
    }
}
