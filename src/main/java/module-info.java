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

    exports com.example.beatbuddy;
    exports com.example.beatbuddy.controller;
    exports com.example.beatbuddy.model;
    exports com.example.beatbuddy.model.utils;
    exports com.example.beatbuddy.model.bbdd;
    exports com.example.beatbuddy.model.bbdd.queries;


    opens com.example.beatbuddy to javafx.fxml, com.jfoenix, org.controlsfx.controls;
    opens com.example.beatbuddy.model to javafx.fxml, com.jfoenix, org.controlsfx.controls;
    opens com.example.beatbuddy.controller to javafx.fxml, com.jfoenix, org.controlsfx.controls;

}