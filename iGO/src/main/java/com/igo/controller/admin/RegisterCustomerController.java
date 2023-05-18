package com.igo.controller.admin;

import com.igo.IGoApplication;
import com.igo.models.tvm.TVM;
import com.igo.models.localization.Language;
import com.igo.models.person.Customer;
import javafx.application.Platform;
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
public class RegisterCustomerController implements Observer {
    public Label customerDetailsLabel;
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label dobLabel;
    public Label customerTypeLabel;
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
    Button closeParentButton = new Button();
    public void initialize(){
        Language.getReference().addObserver(this);
        updateLabels();
        for(Customer.TYPES type: Customer.TYPES.values()){
            customerType.getItems().add(type);
        }
    }
    public void addCustomer(ActionEvent actionEvent) {
        try {
            String firstName=firstNameText.getText(),lastName=lastNameText.getText();
            Customer.TYPES customerTypeValue = customerType.getValue();
            if (firstName.equals("") || lastName.equals("") || customerTypeValue == null || dobObject.getValue() == null){
                Platform.runLater(() ->{
                    IGoApplication.showErrorDialogBox("Something went wrong!","Please provide all the necessary information.");
                });
                return;
            }
            Customer.registerCustomer(firstName,lastName,dobObject.getValue(),customerTypeValue);
            TVM.getReference().notifyAllObservers();
            closeParentButton.fire();
            IGoApplication.showDialogBox("Success!","Operation performed successfully!","Customer added successfully!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public Button getCloseParentButton() {
        return closeParentButton;
    }
    private void updateLabels(){
        Platform.runLater(() ->{
            customerDetailsLabel.setText(Language.getReference().getLabel("customerDetails"));
            firstNameLabel.setText(Language.getReference().getLabel("firstName"));
            lastNameLabel.setText(Language.getReference().getLabel("lastName"));
            dobLabel.setText(Language.getReference().getLabel("birthDate"));
            customerTypeLabel.setText(Language.getReference().getLabel("customerType"));
            addCustomerButton.setText(Language.getReference().getLabel("registerCustomerMenu"));
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        updateLabels();
    }
}
