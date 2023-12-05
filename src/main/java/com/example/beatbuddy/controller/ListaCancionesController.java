package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Cancion;
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

    public void actionReproducir(ActionEvent actionEvent) {
    }

    public void actionBorrar(ActionEvent actionEvent) {
    }

    public void setData(Cancion cancion){

        txtNombreCancion.setText(cancion.getNombre());
        txtNombreCancion.setText(cancion.getNombre());

    }

}
