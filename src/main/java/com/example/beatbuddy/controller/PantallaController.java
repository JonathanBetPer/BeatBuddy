package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Playlist;
import com.example.beatbuddy.model.Reproductor;
import com.example.beatbuddy.model.Usuario;
import com.example.beatbuddy.model.bbdd.ConexionMySQL;
import com.example.beatbuddy.model.bbdd.queries.ConsultaUsuario;
import com.example.beatbuddy.model.bbdd.queries.ConsultasPlaylist;
import com.example.beatbuddy.model.utils.Navegacion;
import com.example.beatbuddy.model.utils.PersistenciaCredenciales;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.LinkedList;


public class PantallaController {
    @FXML
    private Label labelNombreUsuario;
    @FXML
    private JFXTextField tfBuscador;
    @FXML
    private VBox vboxListasPlaylists;
    @FXML
    private VBox vboxListasPlaylistsUsuario;
    @FXML
    private Label tfNombreUsuario;
    public Reproductor reproductor;

    public Usuario usuario;
    public Playlist playlistActual;



    public void cargarListaCanciones(Playlist playlistActual) {
        vboxListasPlaylists.getChildren().clear();

        LinkedList<Cancion> listaCanciones = ConsultasPlaylist.getCancionesInPlaylist(ConexionMySQL.getConnection(), playlistActual.getID());

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
    public void init(String nombreUsuario, Cancion cancionActual, LinkedList<Cancion> historialCanciones, Stage stage) {
        this.usuario = ConsultaUsuario.recuperarDatosUsuario(ConexionMySQL.getConnection(), nombreUsuario);

        this.usuario.setListaPlaylists(ConsultaUsuario.getPlaylistsUsuario(ConexionMySQL.getConnection(), usuario.getID()));

        reproductor = new Reproductor(cancionActual, historialCanciones);

        labelNombreUsuario.setText(usuario.getNombreUsuario());

        cargarListaEnPanel(vboxListasPlaylists, ConsultasPlaylist.obtenerListasBeatBuddy(ConexionMySQL.getConnection()));
        cargarListaEnPanel(vboxListasPlaylistsUsuario, usuario.getListaPlaylists());

        stage.setOnCloseRequest(this::handleClose);
    }

    private void handleClose(WindowEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Estás saliendo de la aplicación");
        alert.setContentText("¿Quieres cerrar la sesión actual?");
        alert.getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == javafx.scene.control.ButtonType.YES) {

            PersistenciaCredenciales.eliminarDatosPersistentes();
            Navegacion.cerrarInterfaz(tfBuscador);
            event.consume();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            //No cerrar ventana
            event.consume();
        }

    }


    private void cargarListaEnPanel(VBox vbox, LinkedList<Playlist> listaPlaylists) {
        vbox.getChildren().clear();

        System.out.println(listaPlaylists.size());
        for (int i = 0; i < listaPlaylists.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(PantallaController.class.getResource("/com/example/beatbuddy/views/Componentes/ListaPlaylists.fxml"));

            try {
                HBox hBox = fxmlLoader.load();
                ListaPlaylistsController controller = fxmlLoader.getController();
                controller.setData(listaPlaylists.get(i), this);
                vbox.getChildren().add(hBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    public void actionVolverPantallaInicial(ActionEvent actionEvent) {
        cargarListaEnPanel(vboxListasPlaylists, ConsultasPlaylist.obtenerListasBeatBuddy(ConexionMySQL.getConnection()));
    }

    @FXML
    public void actionPararOContinuar(ActionEvent actionEvent) {

        if (reproductor.estaReproduciendo()) {
            reproductor.pausar();
        } else {
            reproductor.continuar();
        }
    }

    @FXML
    public void actionCancionDelante(ActionEvent actionEvent) {
        reproductor.avanzarCancion();
    }

    @FXML
    public void actionCancionAtras(ActionEvent actionEvent) {
        reproductor.retrocederCancion();
    }

    @FXML
    public void actionCrearPlaylist(ActionEvent actionEvent) {
        Navegacion.cerrarInterfaz(actionEvent);
        Navegacion.cargarCrearPlaylist(usuario);
    }
}