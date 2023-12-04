package com.example.beatbuddy.model.bbdd.queries;

import com.example.beatbuddy.model.Usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class ConsultaUsuario {

    public static void darAltaUsuario(Connection connection, String nombreUsuario, String nombreCompleto, String correo, String salt, String hashResult){

        try {
            PreparedStatement sentenciaSQLUsuario = connection.prepareStatement("insert into BeatBuddy.USUARIOS(ID, nombre, nombreUsuario, email) values(default, ?, ?, ?)");
            sentenciaSQLUsuario.setString(1, nombreCompleto);
            sentenciaSQLUsuario.setString(2, nombreUsuario);
            sentenciaSQLUsuario.setString(3, correo);
            sentenciaSQLUsuario.executeUpdate();

            PreparedStatement sentenciaSQLContrasena = connection.prepareStatement("insert into BeatBuddy.CONTRASENAS(ID, hashResult, salt) values(default, ?, ?)");
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
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT nombre, nombreUsuario, email, imagen FROM BeatBuddy.USUARIOS where ID =  ?");
            sentenciaSQL.setString(1, ID);
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();

            usuarioActual = new Usuario(
                    ID, resultadoSQL.getString("nombre"),
                    resultadoSQL.getString("nombreUsuario"),
                    resultadoSQL.getString("correo"),
                    blobToFile(resultadoSQL.getBlob("imagen"))
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioActual;
    }


    private static File blobToFile(Blob blob){

        if (blob == null) {
            System.out.println("Blob null");
        }

        File tempPNG = null;
        InputStream is = null;
        try {
            is = blob.getBinaryStream();

            tempPNG = File.createTempFile("temp", ".png");
            tempPNG.deleteOnExit();

            FileOutputStream fos = new FileOutputStream(tempPNG);

            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                fos.write(buffer);
            }

            fos.close();
            is.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(10);
        return tempPNG;
    }
}
