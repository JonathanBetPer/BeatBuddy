package com.example.beatbuddy.controller;

import com.example.beatbuddy.model.bbdd.Conexion;
import com.example.beatbuddy.model.bbdd.queries.ConsultasInicioYRegistro;
import com.example.beatbuddy.model.utils.Encriptacion;
import com.example.beatbuddy.model.utils.Navegacion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;

public class LogInController {
    @FXML
    public JFXButton botonIniciarSesion;
    @FXML
    public JFXTextField tfnombreUsuario;
    @FXML
    public JFXPasswordField tfContrasenaUsuario;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("");
    }

    public void actionBotonIniciarSesion(ActionEvent actionEvent) {

        if (comprobarCamposVacios()){
            if (ConsultasInicioYRegistro.comprobarExisteUsuario(Conexion.getConnection(), tfnombreUsuario.getText(), tfnombreUsuario.getText())){
                String [] contrasena = ConsultasInicioYRegistro.recuperarContrasena(Conexion.getConnection(), tfnombreUsuario.getText(), tfnombreUsuario.getText());
                if (Encriptacion.comprobarPasswd(contrasena[0], contrasena[1], tfContrasenaUsuario.getText())){
                    accederInterfazPrincipal(actionEvent);
                } generarAlerta("DATOS ERRÓNEOS", "CONTRASEÑA INCORRECTA, VUELVE A INTENTARLO", Alert.AlertType.ERROR);
            } generarAlerta("NO EXISTE", "EL USUARIO O CORREO INTRODUCIDO NO EXISTE", Alert.AlertType.ERROR);
        }
    }

    public void actionRegistrarse(ActionEvent actionEvent) {

        accederInterfazRegister(actionEvent);

    }

    private void accederInterfazRegister(ActionEvent actionEvent){

        Navegacion.cargarInterfaz(Navegacion.REGISTER, Modality.APPLICATION_MODAL, "Register");
        Navegacion.cerrarInterfaz(actionEvent);

    }

    private void accederInterfazPrincipal(ActionEvent actionEvent){

        Navegacion.cargarInterfaz(Navegacion.PANTALLA, Modality.APPLICATION_MODAL, "BeatBuddy🎵");
        Navegacion.cerrarInterfaz(actionEvent);

    }

    private boolean comprobarCamposVacios(){
        if (tfnombreUsuario.getText().isEmpty()){
            generarAlerta("CAMPOS SIN RELLENAR", "Es necesario rellenar el campo de nombre de usuario", Alert.AlertType.ERROR);
            return false;
        }
        if (tfContrasenaUsuario.getText().isEmpty()){
            generarAlerta("CAMPOS SIN RELLENAR", "Es necesario rellenar el campo de contraseña de usuario", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void generarAlerta(String tituloVentana, String texto, Alert.AlertType tipoAlerta){
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(tituloVentana);
        alert.setContentText(texto);
        alert.showAndWait();
    }



}