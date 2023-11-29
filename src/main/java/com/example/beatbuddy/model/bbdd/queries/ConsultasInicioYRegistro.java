package com.example.beatbuddy.model.bbdd.queries;

import com.example.beatbuddy.model.utils.Encriptacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasInicioYRegistro {

    public static boolean comprobarExisteUsuario(Connection connection, String nombreUsuario, String correo){

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT ID from USUARIOS WHERE nombre = ? or correo = ?");
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
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT hashResult, salt FROM CONTRASENAS where idUsuario = (SELECT ID from USUARIOS WHERE nombre = ? or correo = ?)");
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

    public static void darAltaUsuario(Connection connection, String nombreUsuario, String nombreCompleto, String correo, String salt, String hashResult){

        try {
            PreparedStatement sentenciaSQLUsuario = connection.prepareStatement("insert into USUARIOS(ID, nombre, nombreUsuario, email) values(default, ?, ?, ?)");
            sentenciaSQLUsuario.setString(1, nombreCompleto);
            sentenciaSQLUsuario.setString(2, nombreUsuario);
            sentenciaSQLUsuario.setString(3, correo);
            sentenciaSQLUsuario.executeUpdate();

            PreparedStatement sentenciaSQLContrasena = connection.prepareStatement("insert into CONTRASENAS(ID, hashResult, salt) values(default, ?, ?)");
            sentenciaSQLContrasena.setString(1, hashResult);
            sentenciaSQLContrasena.setString(2, salt);
            sentenciaSQLContrasena.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
