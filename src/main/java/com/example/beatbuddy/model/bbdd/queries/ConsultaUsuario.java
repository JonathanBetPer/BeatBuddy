package com.example.beatbuddy.model.bbdd.queries;

import com.example.beatbuddy.model.Playlist;
import com.example.beatbuddy.model.Usuario;
import com.password4j.Hash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;

public class ConsultaUsuario {

    public static void darAltaUsuario(Connection connection, String nombreUsuario, String nombreCompleto, String correo, String hashResult, String salt){

        try {
            PreparedStatement sentenciaSQLUsuario = connection.prepareStatement("insert into BeatBuddy.USUARIOS(ID, nombre, nombreUsuario, email) values(default, ?, ?, ?)");
            sentenciaSQLUsuario.setString(1, nombreCompleto);
            sentenciaSQLUsuario.setString(2, nombreUsuario);
            sentenciaSQLUsuario.setString(3, correo);
            sentenciaSQLUsuario.executeUpdate();

            PreparedStatement sentenciaSQLContrasena = connection.prepareStatement("insert into BeatBuddy.CONTRASENAS(ID, hashResult, salt) values(1, ?, ?)");
            sentenciaSQLContrasena.setString(1, hashResult);
            sentenciaSQLContrasena.setString(2, salt);
            sentenciaSQLContrasena.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Usuario recuperarDatosUsuario(Connection connection, String nombreUsuario){
        Usuario usuarioActual = null;

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT ID, nombre, nombreUsuario, email, imagen FROM BeatBuddy.USUARIOS where nombreUsuario =  ? or email = ?");
            sentenciaSQL.setString(1, nombreUsuario);
            sentenciaSQL.setString(2, nombreUsuario);
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();

            while (resultadoSQL.next()) {
                usuarioActual = new Usuario(
                        resultadoSQL.getInt("ID"),
                        resultadoSQL.getString("nombre"),
                        resultadoSQL.getString("nombreUsuario"),
                        resultadoSQL.getString("email"), null);
            }
/*                    blobToFile(resultadoSQL.getBlob("imagen"))*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioActual;
    }
    public static LinkedList<Playlist> getPlaylistsUsuario(Connection connection, int idUsuario) {
        LinkedList<Playlist> listaPlaylist = new LinkedList<>();

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT ID FROM BeatBuddy.PLAYLISTS WHERE idUsuario = ?");
            sentenciaSQL.setInt(1, idUsuario);

            ResultSet resultadoSQL = sentenciaSQL.executeQuery();

            while (resultadoSQL.next()) {
                listaPlaylist.add(ConsultasPlaylist.getPlaylist(connection, resultadoSQL.getInt("ID")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPlaylist;
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
