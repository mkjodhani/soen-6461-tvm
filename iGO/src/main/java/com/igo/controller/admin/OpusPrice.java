package com.igo.controller.admin;

import com.igo.models.data.Data;
import com.igo.models.fares.Cost;
import com.igo.models.person.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class OpusPrice {
    @FXML
    ComboBox<Customer.TYPES> customerTypeComboBox;
    @FXML
    ComboBox<Cost.PERIOD> opusTypeComboBox;
    @FXML
    TextField price;
    public void initialize(){
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
            return;
        }
        double priceValue = Double.valueOf(price.getText());
        Cost.updateOpusRechargeAmount(opusType,customerType,priceValue);
        Data.getReference().notifyAllObservers();
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

}
