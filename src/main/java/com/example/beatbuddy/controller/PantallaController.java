package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Playlist;
import com.example.beatbuddy.model.Usuario;
import com.example.beatbuddy.model.bbdd.Conexion;
import com.example.beatbuddy.model.bbdd.queries.ConsultaUsuario;
import com.example.beatbuddy.model.bbdd.queries.ConsultasBeatBuddyUser;
import com.example.beatbuddy.model.bbdd.queries.ConsultasPlaylist;
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
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class PantallaController implements Initializable {

    @FXML
    public ImageView myImageView;
    @FXML
    public VBox vboxListasPlaylists;


    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        LinkedList<Playlist> listaPlaylist = ConsultasBeatBuddyUser.obtenerListasBeatBuddy(Conexion.getConnection());

        System.out.println(listaPlaylist.size());
        for (int i = 0; i < listaPlaylist.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(PantallaController.class.getResource("/com/example/beatbuddy/views/Componentes/ListaCanciones.fxml"));

            try{
                HBox hBox = fxmlLoader.load();
                ListaPlaylistsController listaPlaylistsController = fxmlLoader.getController();
                listaPlaylistsController.setData(listaPlaylist.get(i));
                vboxListasPlaylists.getChildren().add(hBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }





    }

    public PantallaController() {
    }

}