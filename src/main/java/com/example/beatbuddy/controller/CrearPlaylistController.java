package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Usuario;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;

public class CrearPlaylistController {
    public JFXTextField tfNombrePlaylist;
    public JFXTextField tfDescripcionPlaylist;

    private Usuario usuario;

    public void actionCrearPlaylist(ActionEvent actionEvent) {

        if (ComprobarCamposVacios()){

            //usu

        }

    }

    public void init(Usuario usuario){
        this.usuario = usuario;
    }

    private boolean ComprobarCamposVacios(){

        if (tfNombrePlaylist.getText().isEmpty()){
            return false;
        }
        if (tfDescripcionPlaylist.getText().isEmpty()){
            return false;
        }
        return true;
    }

}
