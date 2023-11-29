package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Usuario;
import com.example.beatbuddy.model.bbdd.Conexion;
import com.example.beatbuddy.model.bbdd.queries.ConsultaUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class PantallaController implements Initializable {

    @FXML
    public ImageView myImageView;

    public Usuario usuarioActual;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }

    public PantallaController() {
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
}