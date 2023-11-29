package com.example.beatbuddy.model.bbdd.queries;

import com.example.beatbuddy.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaUsuario {

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

    public static Usuario recuperarDatosYUsuario(Connection connection, String ID){
        Usuario usuarioActual = null;

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT nombre, nombreUsuario, correo FROM USUARIOS where ID =  ?");
            sentenciaSQL.setString(1, ID);
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();

             usuarioActual = new Usuario(ID, resultadoSQL.getString("nombre"), resultadoSQL.getString("nombreUsuario"), resultadoSQL.getString("correo"), null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioActual;
    }
}
