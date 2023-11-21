module com.example.beatbuddy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.beatbuddy to javafx.fxml;
    exports com.example.beatbuddy;
    exports com.example.beatbuddy.controller;
    opens com.example.beatbuddy.controller to javafx.fxml;
}