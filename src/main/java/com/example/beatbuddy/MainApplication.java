package com.example.beatbuddy;

import com.example.beatbuddy.model.Navegacion;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        Navegacion.cargarInterfaz(Navegacion.PANTALLA, Modality.APPLICATION_MODAL, "Inicio Sesión");
    }

    public static void main(String[] args) {
        launch();
    }
}