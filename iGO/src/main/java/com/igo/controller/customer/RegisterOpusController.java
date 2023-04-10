package com.igo.controller.customer;

import com.igo.IGoApplication;
import com.igo.models.data.Data;
import com.igo.models.localization.Language;
import com.igo.models.opus.OPUS;
import com.igo.models.person.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @project
 * @since 22/03/23
 */
public class RegisterOpusController implements Observer {
    public Button registerOPUSButton;
    public Label customerDetailsLabel;
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label dateOfBirthLabel;
    public Label customerTypeLabel;
    public Button fetchUserButton;
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
    Button closeParentButton = new Button();
    public void initialize() {
        Language.getReference().addObserver(this);
        updateLabels();
    }
    public void registerOPUS(ActionEvent actionEvent) {
        try{
            int customerIDValue = Integer.parseInt(customerID.getText());
            Customer customer = Data.getReference().getCustomerHashMap().getOrDefault(customerIDValue,null);
            if (customer == null){
                IGoApplication.showErrorDialogBox("No Customer found!","Please provide valid customer ID.");
                clearUserInput();
            }
            else{
                OPUS opus = new OPUS(customer);
                Data.getReference().getOpusHashMap().put(opus.getCardId(),opus);
                Data.getReference().notifyAllObservers();
                IGoApplication.showDialogBox("Success!","OPUS has been issued successfully!","You can access the OPUS by inserting #"+opus.getCardId()+" wherever required.");
                closeParentButton.fire();
            }
        }catch (Exception e){
            IGoApplication.showErrorDialogBox("Something went wrong!",e.getMessage());
        }
    }

    public void fetchUser(ActionEvent actionEvent) {
        try{
            int customerIDValue = Integer.parseInt(customerID.getText());
            Customer customer = Data.getReference().getCustomerHashMap().getOrDefault(customerIDValue,null);
            if (customer == null){
                IGoApplication.showErrorDialogBox("No Customer found!","Please provide valid customer ID.");
                clearUserInput();
            }
            else{
                firstNameText.setText(customer.getFirstName());
                lastNameText.setText(customer.getLastName());
                dobObject.setValue(customer.getBirthDate());
                customerType.setValue(customer.getCustomerType());
            }
        }catch (Exception e){
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
        registerOPUSButton.setText(Language.getReference().getLabel("registerOpus"));
        customerDetailsLabel.setText(Language.getReference().getLabel("customerDetails"));
        firstNameLabel.setText(Language.getReference().getLabel("firstName"));
        lastNameLabel.setText(Language.getReference().getLabel("lastName"));
        dateOfBirthLabel.setText(Language.getReference().getLabel("birthDate"));
        customerTypeLabel.setText(Language.getReference().getLabel("customerType"));
        fetchUserButton.setText(Language.getReference().getLabel("fetch"));
    }

    @Override
    public void update(Observable o, Object arg) {
        updateLabels();
    }
}
