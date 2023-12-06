package com.example.beatbuddy;

import com.example.beatbuddy.model.bbdd.ConexionMySQL;
import com.example.beatbuddy.model.utils.Navegacion;
import com.example.beatbuddy.model.utils.PersistenciaCredenciales;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private final boolean PERSISTENCIA = PersistenciaCredenciales.comprobarExistenCredenciales();

    @Override
    public void start(Stage stage) {

        if (PERSISTENCIA){
            Navegacion.cargarInterfazPrincipal(PersistenciaCredenciales.recuperarUsuario(),
                    PersistenciaCredenciales.recuperarCancionActual(),
                    PersistenciaCredenciales.recuperarHistorailCanciones()
                    );
        } else {
            Navegacion.cargarInterfaz(Navegacion.LOGIN, Modality.APPLICATION_MODAL);
        }

    }

    public static void main(String[] args) {
        new ConexionMySQL("yoni13ja.ddns.net:13306/BeatBuddy", "beatbuddy", "BeatBuddy@133").iniciar();
        new PersistenciaCredenciales.ConexionSQLite("src/main/resources/com/example/beatbuddy/files/persitenciaBeadBuddy.db").iniciar();

        launch();
    }

}