module com.example.beatbuddy {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires password4j;
    requires com.jfoenix;

    opens com.example.beatbuddy to javafx.fxml;
    exports com.example.beatbuddy;
    exports com.example.beatbuddy.controller;
    opens com.example.beatbuddy.controller to javafx.fxml;
    opens com.example.beatbuddy.views to com.jfoenix;
}