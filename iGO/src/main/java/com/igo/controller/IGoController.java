package com.igo.controller;


import com.igo.IGoApplication;
import com.igo.controller.customer.CustomerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class IGoController {
    @FXML
    ImageView image;

    public void initialize() {
        Image imProfile = new Image(getClass().getResourceAsStream("/com/igo/assets/TVM.jpg"));
        if(image != null){
            image.setImage(imProfile);
        }
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
        CustomerController.setCustomerStage(stage);
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}