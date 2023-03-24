package com.igo.controller.customer;

import com.igo.IGoApplication;
import com.igo.models.data.Data;
import com.igo.models.opus.OPUS;
import com.igo.models.person.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class RegisterOpus {
    @FXML
    TextField customerID;
    @FXML
    TextField firstNameText;
    @FXML
    TextField lastNameText;
    @FXML
    DatePicker dobObject;
    @FXML
    ComboBox<Customer.TYPES> customerType;

    public void registerOPUS(ActionEvent actionEvent) {
        try{
            int customerIDValue = Integer.parseInt(customerID.getText());
            Customer customer = Data.getReference().getCustomerHashMap().getOrDefault(customerIDValue,null);
            if (customer == null){
                IGoApplication.showDialogBox("Error!","No Customer found!","Please provide valid customer ID.");
                return;
            }
            else{
                OPUS opus = new OPUS(customer);
                Data.getReference().getOpusHashMap().put(opus.getCardId(),opus);
                Data.getReference().notifyAllObservers();
                IGoApplication.showDialogBox("Success!","OPUS has been issued successfully!","You can access the OPUS by inserting #"+opus.getCardId()+" wherever required.");
            }
        }catch (Exception e){
            IGoApplication.showDialogBox("Error!","Something went wrong!",e.getMessage());
        }
    }

    public void fetchUser(ActionEvent actionEvent) {
        try{
            int customerIDValue = Integer.parseInt(customerID.getText());
            Customer customer = Data.getReference().getCustomerHashMap().getOrDefault(customerIDValue,null);
            if (customer == null){
                IGoApplication.showDialogBox("Error!","No Customer found!","Please provide valid customer ID.");
                return;
            }
            else{
                firstNameText.setText(customer.getFirstName());
                lastNameText.setText(customer.getLastName());
                dobObject.setValue(customer.getBirthDate());
                customerType.setValue(customer.getCustomerType());
            }
        }catch (Exception e){
            IGoApplication.showDialogBox("Error!","Something went wrong!",e.getMessage());
        }
    }
}
