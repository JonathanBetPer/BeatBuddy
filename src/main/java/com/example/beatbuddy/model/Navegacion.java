package com.example.beatbuddy.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * @author JonathanBetPer
 * @since 21/11/2023
 * @v0.1
 * Clase Navegación
 * Gestiona el inicio y el cierre de las interfaces de la aplicación
 */

public class Navegacion {
    public static final String LogIn = "/com/example/beatbuddy/views/LogIn.fxml";

    public static boolean cargarInterfaz(String viewToLoad, Modality modality, String stageTitle){
        FXMLLoader fxmlLoader = new FXMLLoader(Navegacion.class.getResource(viewToLoad));

        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(stageTitle);
            stage.setScene(scene);
            stage.initModality(modality);
            stage.show();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void cerrarInterfaz(ActionEvent e){
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}