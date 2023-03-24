package com.igo.controller.admin;

import com.igo.IGoApplication;
import com.igo.models.data.Data;
import com.igo.models.person.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class RegisterCustomer {

    @FXML
    Button addCustomerButton;
    @FXML
    TextField firstNameText;
    @FXML
    TextField lastNameText;
    @FXML
    DatePicker dobObject;
    @FXML
    ComboBox<Customer.TYPES> customerType;

    public void initialize(){
        for(Customer.TYPES type: Customer.TYPES.values()){
            customerType.getItems().add(type);
        }
    }
    public void addCustomer(ActionEvent actionEvent) {
        String firstName=firstNameText.getText(),lastName=lastNameText.getText();
        Customer.TYPES customerTypeValue = customerType.getValue();
        Customer.registerCustomer(firstName,lastName,dobObject.getValue(),customerTypeValue);
        Data.getReference().notifyAllObservers();
        IGoApplication.showDialogBox("Success!","Customer added successfully!","");
    }
}
