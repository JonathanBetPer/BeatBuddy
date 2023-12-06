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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    public JFXButton botonIniciarSesion;
    @FXML
    public JFXTextField tfnombreUsuario;
    @FXML
    public JFXPasswordField tfContrasenaUsuario;


    @FXML
    public void actionBotonIniciarSesion(ActionEvent actionEvent) {

       if (comprobarCamposVacios()){
            if (ConsultasInicioYRegistro.comprobarExisteUsuario(Conexion.getConnection(), tfnombreUsuario.getText(), tfnombreUsuario.getText())){

                System.out.println(tfContrasenaUsuario.getText());
                String hastResult = ConsultasInicioYRegistro.recuperarContrasena(Conexion.getConnection(), tfnombreUsuario.getText(), tfnombreUsuario.getText());
                System.out.println(hastResult);

                if (Encriptacion.comprobarPasswd(hastResult, tfContrasenaUsuario.getText())){
                    accederInterfazPrincipal(actionEvent);
                } else {
                    generarAlerta("DATOS ERRÓNEOS", "CONTRASEÑA INCORRECTA, VUELVE A INTENTARLO", Alert.AlertType.ERROR);
                }

            }else {
                generarAlerta("NO EXISTE", "EL USUARIO O CORREO INTRODUCIDO NO EXISTE", Alert.AlertType.ERROR);

            }

        }
    }

    public void actionRegistrarse(ActionEvent actionEvent) {

        accederInterfazRegister(actionEvent);

    }

    private void accederInterfazRegister(ActionEvent actionEvent){

        Navegacion.cargarInterfaz(Navegacion.REGISTER, Modality.APPLICATION_MODAL);
        Navegacion.cerrarInterfaz(actionEvent);

    }

    private void accederInterfazPrincipal(ActionEvent actionEvent){


        Navegacion.cargarInterfazPrincipal(tfnombreUsuario.getText(), null, null);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}