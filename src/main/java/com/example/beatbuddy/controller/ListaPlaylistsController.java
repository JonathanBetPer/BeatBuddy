package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Playlist;
import com.example.beatbuddy.model.utils.Navegacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ListaPlaylistsController {


    @FXML
    public Label txtNombrePlaylist;

    private Playlist playlistActual;

    private PantallaController pantallaController;


    public void actionReproducir(ActionEvent actionEvent) {

        pantallaController.cargarListaCanciones(playlistActual);

    }

    public void actionBorrar (ActionEvent actionEvent){

        Navegacion.cerrarInterfaz(actionEvent);

    }

    public void setData (Playlist playlist, PantallaController pantallaController){

        txtNombrePlaylist.setText(playlist.getNombre());
        playlistActual = playlist;
        this.pantallaController = pantallaController;


    }

    public void actionReproducirPlaylist(ActionEvent actionEvent) {

        pantallaController.reproductor.agregarPlaylistCola(playlistActual.getLista());
        pantallaController.reproductor.comenzar();



    }
}
