module com.example.beatbuddy {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.jfoenix;
    requires password4j;
    requires org.slf4j;
    requires mysql.connector.j;
    requires java.sql;
    requires annotations;
    requires basicplayer;

    opens com.example.beatbuddy to javafx.fxml;
    exports com.example.beatbuddy;

    exports com.example.beatbuddy.controller;
    opens com.example.beatbuddy.controller to javafx.fxml;

}