package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ListaPlaylistsController {

    @FXML
    public Button buttonReproducir;
    @FXML
    public Button buttonBorrar;
    @FXML
    public Label txtNombrePlaylist;

    public void actionReproducir(ActionEvent actionEvent) {
    }

    public void actionBorrar(ActionEvent actionEvent) {
    }

    public void setData(Playlist playlist){

        txtNombrePlaylist.setText(playlist.getNombre());

    }

}
