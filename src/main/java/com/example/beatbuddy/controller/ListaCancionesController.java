package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Playlist;
import com.example.beatbuddy.model.Reproductor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ListaCancionesController {

    @FXML
    public Label txtNombreCancion;
    @FXML
    public Label txtNombreAutor;
    @FXML
    public Button buttonReproducir;
    @FXML
    public Button buttonBorrar;
    @FXML
    public Label txtNombrePlaylists;
    public Button buttonReproducir1;

    private Cancion cancionActual;
    private PantallaController pantallaController;



    public void setData(Cancion cancion, PantallaController pantallaController){

        txtNombreCancion.setText(cancion.getNombre());
        txtNombreCancion.setText(cancion.getNombre());
        cancionActual = cancion;
        this.pantallaController = pantallaController;

    }

    public void actionReproducir(ActionEvent actionEvent) {

        pantallaController.reproductor.setCancionActual(cancionActual);
        pantallaController.reproductor.comenzar();

    }

    public void actionBorrar(ActionEvent actionEvent) {
        pantallaController.usuario.eliminarCancionPlaylist(pantallaController.playlistActual, cancionActual);
    }
}
