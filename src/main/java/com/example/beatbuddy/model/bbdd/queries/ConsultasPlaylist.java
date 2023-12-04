package com.example.beatbuddy.model.bbdd.queries;

import com.example.beatbuddy.model.Cancion;
import com.example.beatbuddy.model.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConsultasPlaylist {

    public static Playlist getPlaylist(Connection connection,  int idPlaylist) {
        Playlist playlist = null;

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT ID, nombre, descripcion, idUsuario FROM BeatBuddy.PLAYLISTS WHERE id = ?");
            sentenciaSQL.setInt(1, idPlaylist);

            ResultSet resultadoSQL = sentenciaSQL.executeQuery();

            while (resultadoSQL.next()) {
                playlist = new Playlist(
                        resultadoSQL.getInt("id"),
                        resultadoSQL.getString("nombre"),
                        resultadoSQL.getString("descripcion"),
                        resultadoSQL.getInt("idUsuario")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        assert playlist != null;
        playlist.setLista(getCancionesInPlaylist(connection, idPlaylist));

        return playlist;
    }

    private static LinkedList<Cancion> getCancionesInPlaylist(Connection connection, int idPlaylist){
        LinkedList<Cancion> lista = new LinkedList<>();

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT idCancion FROM BeatBuddy.CONTIENEN WHERE idPlaylist = ?");
            sentenciaSQL.setInt(1, idPlaylist);

            ResultSet resultadoSQL = sentenciaSQL.executeQuery();

            while (resultadoSQL.next()) {
                lista.add(ConsultasCancion.obtenerCancion(connection, resultadoSQL.getInt("idCancion")));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return lista;
    }
}
