module com.igo.igo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.igo to javafx.fxml;
    exports com.igo;
    exports com.igo.controller;
    opens com.igo.controller to javafx.fxml;
}