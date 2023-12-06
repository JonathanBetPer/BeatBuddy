package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.Usuario;
import com.example.beatbuddy.model.bbdd.Conexion;
import com.example.beatbuddy.model.bbdd.queries.ConsultasInicioYRegistro;
import com.example.beatbuddy.model.utils.Encriptacion;
import com.example.beatbuddy.model.utils.Navegacion;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.password4j.Hash;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

public class RegisterController {
    @FXML
    public JFXTextField tfNombreUsuario;
    @FXML
    public JFXPasswordField tfContrasenaUsuario;
    @FXML
    public JFXTextField tfCorreoUsuario;
    @FXML
    public JFXTextField tfNombreCompleto;

    public void actionRegistrarse(ActionEvent actionEvent) {



        if (comprobarCamposVacios()){
            if (ConsultasInicioYRegistro.comprobarExisteUsuario(Conexion.getConnection(), tfNombreUsuario.getText(), tfCorreoUsuario.getText())){
                Hash hash = Encriptacion.generarHash(tfContrasenaUsuario.getText());
/*
                ConsultasInicioYRegistro.darAltaUsuario(Conexion.getConnection(), tfNombreUsuario.getText(), tfNombreCompleto.getText(), tfCorreoUsuario.getText(), hash.getSalt(), hash.getResult());
*/

            }
        }



    }

    public void actionVolverLogIn(MouseEvent mouseEvent) {


    }

    private boolean comprobarCamposVacios(){
        if (tfNombreUsuario.getText().isEmpty()){
            generarAlerta("CAMPOS SIN RELLENAR", "Es necesario rellenar el campo de nombre de usuario", Alert.AlertType.ERROR);
            return false;
        }
        if (tfCorreoUsuario.getText().isEmpty()){
            generarAlerta("CAMPOS SIN RELLENAR", "Es necesario rellenar el campo de correo de correo", Alert.AlertType.ERROR);
            return false;
        }
        if (tfContrasenaUsuario.getText().isEmpty()){
            generarAlerta("CAMPOS SIN RELLENAR", "Es necesario rellenar el campo de correo de contraseña", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void accederInterfazLogIn(ActionEvent actionEvent){

        Navegacion.cargarInterfaz(Navegacion.LOGIN, Modality.APPLICATION_MODAL);
        Navegacion.cerrarInterfaz(actionEvent);

    }
    private void accederInterfazPantalla(ActionEvent actionEvent){

        Navegacion.cargarInterfaz(Navegacion.PANTALLA, Modality.APPLICATION_MODAL);
        Navegacion.cerrarInterfaz(actionEvent);

    }

    private void generarAlerta(String tituloVentana, String texto, Alert.AlertType tipoAlerta){
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(tituloVentana);
        alert.setContentText(texto);
        alert.showAndWait();
    }
}
