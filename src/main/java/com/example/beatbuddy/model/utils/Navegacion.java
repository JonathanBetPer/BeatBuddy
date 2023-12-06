package com.example.beatbuddy.model.utils;

import com.example.beatbuddy.controller.PantallaController;
import com.example.beatbuddy.model.Cancion;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.intellij.lang.annotations.MagicConstant;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author JonathanBetPer
 * @version v1
 * @since 21/11/2023

 * Clase Navegación
 * Se encarga de cargar iniciar las distintas ventanas de la interfaz y de cerrarlas
 */

public class Navegacion {
    @MagicConstant(stringValues = {Navegacion.LOGIN, Navegacion.REGISTER, Navegacion.PANTALLA, Navegacion.CREARPLAYLIST},
            valuesFromClass = Navegacion.class)
    private static final String RUTA = "/com/example/beatbuddy/views/";
    public static final String LOGIN = "LogIn.fxml";
    public static final String REGISTER = "Register.fxml";
    public static final String PANTALLA = "Pantalla.fxml";
    public static final String CREARPLAYLIST = "CrearPlaylist.fxml";
    private static final String tituloVentana = "BeatBuddy🎵";



    public static boolean cargarInterfaz(String interfaz, Modality modality){
        FXMLLoader fxmlLoader = new FXMLLoader(Navegacion.class.getResource(RUTA+interfaz));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(tituloVentana);
            stage.getIcons().add(new Image(Navegacion.class.getResourceAsStream("/com/example/beatbuddy/Icons/logo1.png")));
            stage.setScene(scene);
            stage.initModality(modality);
            stage.show();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cargarInterfazPrincipal(String nombreUsuario, Cancion cancionActual, LinkedList<Cancion> historialCanciones){
        FXMLLoader fxmlLoader = new FXMLLoader(Navegacion.class.getResource(RUTA+PANTALLA));
        try {
            Parent root = fxmlLoader.load();
            PantallaController pantallaController = fxmlLoader.getController();
            pantallaController.init(nombreUsuario, cancionActual, historialCanciones);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(tituloVentana);
            stage.getIcons().add(new Image(Navegacion.class.getResourceAsStream("/com/example/beatbuddy/Icons/logo1.png")));
            stage.setScene(scene);
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
    public static void cerrarInterfaz(TextField e){
        Stage ventanaPrincipal = (Stage) e.getScene().getWindow();
        ventanaPrincipal.close();
    }
}