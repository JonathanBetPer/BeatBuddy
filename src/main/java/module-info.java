module com.example.beatbuddy {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires password4j;
    requires com.jfoenix;

    opens com.example.beatbuddy to javafx.fxml;
    exports com.example.beatbuddy;
    opens com.example.beatbuddy.controller to javafx.fxml, com.jfoenix;
    exports com.example.beatbuddy.controller to javafx.fxmlcom.jfoenix ;

    opens com.example.beatbuddy.views to com.jfoenix;
}