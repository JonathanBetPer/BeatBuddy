package com.example.beatbuddy.model.bbdd.queries;

import com.example.beatbuddy.model.utils.Encriptacion;

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

    public static String[] recuperarContrasena(Connection connection, String nombre, String correo){
        String [] resultado = new String[2];
        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT hashResult, salt FROM BeatBuddy.CONTRASENAS where ID = (SELECT ID from BeatBuddy.USUARIOS WHERE nombre = ? or email = ?)");
            sentenciaSQL.setString(1, nombre);
            sentenciaSQL.setString(2, correo);
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();
            while (resultadoSQL.next()) {
                resultado[0] = resultadoSQL.getString("hashResult");
                resultado[1] = resultadoSQL.getString("salt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }







}
