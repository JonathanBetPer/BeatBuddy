package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Playlist;
import com.example.beatbuddy.model.Reproductor;
import com.example.beatbuddy.model.Usuario;
import com.example.beatbuddy.model.bbdd.Conexion;
import com.example.beatbuddy.model.bbdd.queries.ConsultaUsuario;
import com.example.beatbuddy.model.bbdd.queries.ConsultasBeatBuddyUser;
import com.example.beatbuddy.model.bbdd.queries.ConsultasPlaylist;
import javafx.event.ActionEvent;
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

    public Reproductor reproductor;

    public Playlist playlistActual;
    @FXML
    public VBox vboxListasPlaylistsUsuario;

    private Usuario usuario;

    public void cargarListaCanciones(Playlist playlistActual) {
        vboxListasPlaylists.getChildren().clear();

        LinkedList<Cancion> listaCanciones = ConsultasPlaylist.getCancionesInPlaylist(Conexion.getConnection(), playlistActual.getID());

        System.out.println(listaCanciones.size());
        for (int i = 0; i < listaCanciones.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(PantallaController.class.getResource("/com/example/beatbuddy/views/Componentes/ListaCanciones.fxml"));

            try {
                HBox hBox = fxmlLoader.load();
                ListaCancionesController listaCancionesController = fxmlLoader.getController();
                listaCancionesController.setData(listaCanciones.get(i), this);
                vboxListasPlaylists.getChildren().add(hBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    //
    public void init(int idUsuario) {

        this.usuario = ConsultaUsuario.recuperarDatosYUsuario(Conexion.getConnection(), idUsuario);

        playlistActual = null;
        reproductor = new Reproductor(null, null);

        LinkedList<Playlist> listaPlaylistBeatBuddy = ConsultasBeatBuddyUser.obtenerListasBeatBuddy(Conexion.getConnection());


        System.out.println(listaPlaylistBeatBuddy.size());
        for (int i = 0; i < listaPlaylistBeatBuddy.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(PantallaController.class.getResource("/com/example/beatbuddy/views/Componentes/ListaPlaylists.fxml"));

            try{

                HBox hBox = fxmlLoader.load();
                ListaPlaylistsController listaPlaylistsController = fxmlLoader.getController();
                listaPlaylistsController.setData(listaPlaylistBeatBuddy.get(i), this);
                vboxListasPlaylists.getChildren().add(hBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        LinkedList<Playlist> listaPlaylistUsuario = ConsultasBeatBuddyUser.obtenerListasBeatBuddy(Conexion.getConnection());


        System.out.println(listaPlaylistUsuario.size());
        for (int i = 0; i < listaPlaylistUsuario.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(PantallaController.class.getResource("/com/example/beatbuddy/views/Componentes/ListaPlaylists.fxml"));

            try{

                HBox hBox = fxmlLoader.load();
                ListaPlaylistsController listaPlaylistsController = fxmlLoader.getController();
                listaPlaylistsController.setData(listaPlaylistUsuario.get(i), this);
                vboxListasPlaylistsUsuario.getChildren().add(hBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }



    public PantallaController() {
    }

    public void actionVolverPantallaInicial(ActionEvent actionEvent) {

        vboxListasPlaylists.getChildren().clear();

        LinkedList<Playlist> listaPlaylist = ConsultasBeatBuddyUser.obtenerListasBeatBuddy(Conexion.getConnection());

        System.out.println(listaPlaylist.size());
        for (int i = 0; i < listaPlaylist.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(PantallaController.class.getResource("/com/example/beatbuddy/views/Componentes/ListaPlaylists.fxml"));

            try{
                HBox hBox = fxmlLoader.load();
                ListaPlaylistsController listaPlaylistsController = fxmlLoader.getController();
                listaPlaylistsController.setData(listaPlaylist.get(i), this);
                vboxListasPlaylists.getChildren().add(hBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void actionPararOContinuar(ActionEvent actionEvent) {

        if (reproductor.estaReproduciendo()) {
            reproductor.pausar();
        } else {
            reproductor.continuar();
        }

    }

    public void actionCancionDelante(ActionEvent actionEvent) {

        reproductor.avanzarCancion();

    }

    public void actionCancionAtras(ActionEvent actionEvent) {


    }

    public void actionCrearPlaylist(ActionEvent actionEvent) {
    }
}