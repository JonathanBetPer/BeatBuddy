package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Usuario;
import com.example.beatbuddy.model.bbdd.Conexion;
import com.example.beatbuddy.model.bbdd.queries.ConsultaUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class PantallaController implements Initializable {

    @FXML
    public ImageView myImageView;
    @FXML
    public VBox paneLista;


    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ArrayList<Cancion> listaCanciones = new ArrayList<>();

        listaCanciones.add(new Cancion(1, "SHE DONT GIVE", "Reggaeton", "Lola Indigo", "ajsajsajs"));

        for (int i = 0; i < listaCanciones.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("com/example/beatbuddy/views/Componentes/ListaCanciones.fxml"));

            try{
                AnchorPane anchorPane = fxmlLoader.load();
                ListaCancionesController listaCancionesController = fxmlLoader.getController();
                listaCancionesController.setData(listaCanciones.get(i));
                paneLista.getChildren().add(anchorPane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }





    }

    public PantallaController() {
    }

}