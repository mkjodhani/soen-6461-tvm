package com.igo.controller.admin;

import com.igo.IGoApplication;
import com.igo.models.tvm.TVM;
import com.igo.models.fares.Cost;
import com.igo.models.localization.Language;
import com.igo.models.person.Customer;
import javafx.application.Platform;
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
 * @since 22/03/23
 */
public class OpusPriceController implements Observer {
    public Label opusPlanDetails;
    public Label customerTypeLabel;
    public Label opusTypeLabel;
    public Label priceLabel;
    public Button updateButton;
    @FXML
    ComboBox<Customer.TYPES> customerTypeComboBox;
    @FXML
    ComboBox<Cost.PERIOD> opusTypeComboBox;
    @FXML
    TextField price;
    Button closeParentButton = new Button();
    public void initialize(){
        Language.getReference().addObserver(this);
        opusPlanDetails.setText(Language.getReference().getLabel("opusPlanDetails"));
        customerTypeLabel.setText(Language.getReference().getLabel("customerType"));
        opusTypeLabel.setText(Language.getReference().getLabel("opusRechargeTypeLabel"));
        priceLabel.setText(Language.getReference().getLabel("price"));
        updateButton.setText(Language.getReference().getLabel("update"));
        customerTypeComboBox.getItems().add(Customer.TYPES.CHILD);
        customerTypeComboBox.getItems().add(Customer.TYPES.STUDENT);
        customerTypeComboBox.getItems().add(Customer.TYPES.ADULT);
        customerTypeComboBox.getItems().add(Customer.TYPES.SENIOR_CITIZEN);
        opusTypeComboBox.getItems().add(Cost.PERIOD.ONE_WEEK);
        opusTypeComboBox.getItems().add(Cost.PERIOD.ONE_MONTH);
        opusTypeComboBox.getItems().add(Cost.PERIOD.FOUR_MONTH);
    }

    public void update(ActionEvent actionEvent) {
        Customer.TYPES customerType = customerTypeComboBox.getValue();
        Cost.PERIOD opusType = opusTypeComboBox.getValue();
        if (customerType == null || opusType == null){
            IGoApplication.showErrorDialogBox("Please provide valid information!","One of the field is empty.");
            return;
        }
        else if (Cost.getOpusRechargeAmount(opusType,customerType) == -1){
            IGoApplication.showErrorDialogBox("Please provide valid information!","This plan doesn't exist in the system.");
            return;
        }
        double priceValue = Double.valueOf(price.getText());
        Cost.updateOpusRechargeAmount(opusType,customerType,priceValue);
        TVM.getReference().notifyAllObservers();
        IGoApplication.showDialogBox("Success!","Operation performed successfully!","Price has been updated!");
        closeParentButton.fire();
    }

    public void fetch(ActionEvent actionEvent) {
        Customer.TYPES customerType = customerTypeComboBox.getValue();
        Cost.PERIOD opusType = opusTypeComboBox.getValue();
        if (customerType == null || opusType == null){
            return;
        }
        double priceValue =  Cost.getOpusRechargeAmount(opusType,customerType);
        price.setText(String.valueOf(priceValue));
    }
    public Button getCloseParentButton() {
        return closeParentButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() ->{
            opusPlanDetails.setText(Language.getReference().getLabel("opusPlanDetails"));
            customerTypeLabel.setText(Language.getReference().getLabel("customerType"));
            opusTypeLabel.setText(Language.getReference().getLabel("opusRechargeTypeLabel"));
            priceLabel.setText(Language.getReference().getLabel("price"));
            updateButton.setText(Language.getReference().getLabel("update"));
        });
    }
}
