package com.example.beatbuddy;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Reproductor;
import com.example.beatbuddy.model.bbdd.Conexion;
import com.example.beatbuddy.model.bbdd.queries.ConsultaUsuario;
import com.example.beatbuddy.model.bbdd.queries.ConsultasCancion;
import com.example.beatbuddy.model.utils.Encriptacion;
import com.example.beatbuddy.model.utils.Navegacion;
import com.example.beatbuddy.model.utils.PersistenciaCredenciales;
import com.password4j.Hash;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedList;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        Navegacion.cargarInterfaz(Navegacion.LOGIN, Modality.APPLICATION_MODAL);
    }

    public static void main(String[] args) {
        new Conexion("yoni13ja.ddns.net:13306/BeatBuddy", "beatbuddy", "BeatBuddy@133").iniciar();


        PersistenciaCredenciales.guardarCredenciales("yoni13ja", "BeatBuddy@133");
        System.out.println(PersistenciaCredenciales.comprobarExistenCredenciales());
        //launch();
    }
}