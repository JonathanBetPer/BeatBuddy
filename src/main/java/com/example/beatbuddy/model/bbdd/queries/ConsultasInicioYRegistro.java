package com.example.beatbuddy.model.bbdd.queries;

import com.password4j.Hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasInicioYRegistro {

    public static boolean comprobarExisteUsuario(Connection connection, String nombreUsuario, String correo){

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT ID from BeatBuddy.USUARIOS WHERE nombre = ? or email = ?");
            sentenciaSQL.setString(1, nombreUsuario);
            sentenciaSQL.setString(2, correo);
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();
            if (resultadoSQL!=null){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String recuperarContrasena(Connection connection, String nombre, String correo){
        String resultado = "";
        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT hashResult FROM BeatBuddy.CONTRASENAS INNER JOIN BeatBuddy.USUARIOS U on CONTRASENAS.ID = U.ID WHERE U.nombreUsuario = ? or U.email = ?");
            sentenciaSQL.setString(1, nombre);
            sentenciaSQL.setString(2, correo);
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();
            while (resultadoSQL.next()) {
                resultado = resultadoSQL.getString("hashResult");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }







}
