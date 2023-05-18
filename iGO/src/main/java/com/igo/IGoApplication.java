package com.igo;

import com.igo.mock.RepresentativeData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class IGoApplication extends Application {
    private static Stage primaryStage;
    private static Scene mainScene;
    private static int width = 600, height = 600;
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(IGoApplication.class.getResource("iGo.fxml"));
        mainScene = new Scene(fxmlLoader.load());
        stage.setTitle("iGo");
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        RepresentativeData.ImportUsers();
        RepresentativeData.ImportTickets();
        launch();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static Scene getMainScene() {
        return mainScene;
    }
    public static void showDialogBox(String title,String header,String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void showErrorDialogBox(String header,String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

}