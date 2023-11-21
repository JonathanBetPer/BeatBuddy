package com.example.beatbuddy.model.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasInicioYRegistro {

    public static boolean comprobarInicioSesion(Connection connection){

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT id, nombre, apellidos, telefono, email, direccion FROM Contactos");

            ResultSet resultadoSQL = sentenciaSQL.executeQuery();


            while (resultadoSQL.next()) {

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean nuevoRegistro(Connection connection){

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT id, nombre, apellidos, telefono, email, direccion FROM Contactos");

            ResultSet resultadoSQL = sentenciaSQL.executeQuery();


            while (resultadoSQL.next()) {

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


}
