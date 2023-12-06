package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Playlist;
import com.example.beatbuddy.model.Usuario;
import com.example.beatbuddy.model.bbdd.ConexionMySQL;
import com.example.beatbuddy.model.bbdd.queries.ConsultasPlaylist;
import com.example.beatbuddy.model.utils.Navegacion;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;

public class CrearPlaylistController {
    public JFXTextField tfNombrePlaylist;
    public JFXTextField tfDescripcionPlaylist;
    private Usuario usuario;

    public void actionCrearPlaylist(ActionEvent actionEvent) {

        if (ComprobarCamposVacios()){

            Playlist playlist = new Playlist(tfNombrePlaylist.getText(), tfDescripcionPlaylist.getText());
            ConsultasPlaylist.setPlaylist(ConexionMySQL.getConnection(), usuario.getID(), playlist);
            Navegacion.cerrarInterfaz(actionEvent);
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
