package com.example.beatbuddy;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Reproductor;
import com.example.beatbuddy.model.bbdd.Conexion;
import com.example.beatbuddy.model.bbdd.queries.ConsultasCancion;
import com.example.beatbuddy.model.utils.Encriptacion;
import com.example.beatbuddy.model.utils.Navegacion;
import com.password4j.Hash;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedList;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        Navegacion.cargarInterfaz(Navegacion.PANTALLA, Modality.APPLICATION_MODAL, "Inicio Sesión");
    }

    public static void main(String[] args) {
        new Conexion("yoni13ja.ddns.net:13306/BeatBuddy", "beatbuddy", "BeatBuddy@133").iniciar();



        launch();
    }
}