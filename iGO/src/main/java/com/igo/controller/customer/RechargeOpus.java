package com.igo.controller.customer;

import com.igo.IGoApplication;
import com.igo.models.data.Data;
import com.igo.models.fares.Cost;
import com.igo.models.opus.OPUS;
import com.igo.models.person.Customer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class RechargeOpus {

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
    public void initialize(){
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
                IGoApplication.showDialogBox("Error!","No OPUS found!","Please provide valid opus ID.");
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
            IGoApplication.showDialogBox("Error!","Something went wrong!",e.getMessage());
        }
    }

    public void rechargeOPUS(ActionEvent actionEvent) {
        int opusIDValue = Integer.parseInt(opusID.getText());
        OPUS opus = Data.getReference().getOpusHashMap().getOrDefault(opusIDValue,null);
        Customer customer = opus.getCustomer();
        if (opus == null){
            IGoApplication.showDialogBox("Error!","No OPUS found!","Please provide valid opus ID.");
            return;
        }
        String rechargeTypeString = rechargeType.getValue();
        double priceValue =  Cost.getOpusRechargeAmount(getRechargeType(rechargeTypeString),customer.getCustomerType());
        if (priceValue == -1){
            IGoApplication.showDialogBox("Error!","Plan can not be activated!","This plan is not provided to "+customer.getCustomerType()+".");
        }
        else {
            opus.recharge(getRechargeType(rechargeTypeString));
            IGoApplication.showDialogBox("Success!","Plan has been activated!","This plan provided to "+customer.getCustomerType()+".");
        }
    }

    public void fetchUser(ActionEvent actionEvent) {
        try{
            int opusIDValue = Integer.parseInt(opusID.getText());
            OPUS opus = Data.getReference().getOpusHashMap().getOrDefault(opusIDValue,null);
            if (opus == null){
                IGoApplication.showDialogBox("Error!","No OPUS found!","Please provide valid opus ID.");
                return;
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
            IGoApplication.showDialogBox("Error!","Something went wrong!",e.getMessage());
        }
    }
}
