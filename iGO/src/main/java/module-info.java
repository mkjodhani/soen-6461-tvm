module com.igo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;


    opens com.igo to javafx.fxml;
    exports com.igo;
    exports com.igo.controller;
    exports com.igo.models.person;
    exports com.igo.models.opus;
    exports com.igo.models.ticket;
    exports com .igo.models.fares;
    opens com.igo.controller to javafx.fxml;
    exports com.igo.controller.admin;
    opens com.igo.controller.admin to javafx.fxml;
    exports com.igo.controller.customer;
    opens com.igo.controller.customer to javafx.fxml;
}