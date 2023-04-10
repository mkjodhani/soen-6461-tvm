package com.igo.controller;


import com.igo.IGoApplication;
import com.igo.models.localization.Language;
import com.igo.models.person.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class IGoController implements Observer {
    public Button adminButton;
    public Button customerButton;
    @FXML
    ImageView image;
    @FXML
    ComboBox<Language.TYPES> language;
    public void initialize(){
        Language.getReference().addObserver(this);
        for(Language.TYPES type: Language.TYPES.values()){
            language.getItems().add(type);
        }
        language.setValue(Language.TYPES.ENGLISH);
    }
    public void showAdminScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/admin/Admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void showCustomerScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/igo/customer/customer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), IGoApplication.getWidth(), IGoApplication.getHeight());
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onSelectLanguage(ActionEvent actionEvent) {
        Language.getReference().setSelectedOption(language.getValue());
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() ->{
            adminButton.setText(Language.getReference().getLabel("admin"));
            customerButton.setText(Language.getReference().getLabel("customerMenu"));
        });
    }
}