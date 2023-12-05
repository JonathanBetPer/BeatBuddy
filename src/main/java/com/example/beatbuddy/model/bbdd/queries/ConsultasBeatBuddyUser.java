package com.example.beatbuddy.model.bbdd.queries;

import com.example.beatbuddy.model.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConsultasBeatBuddyUser {

    public static LinkedList<Playlist> obtenerListasBeatBuddy(Connection connection){
        LinkedList<Playlist> listasPlaylist = new LinkedList<>();
        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT ID FROM BeatBuddy.PLAYLISTS WHERE idUsuario = (SELECT ID from BeatBuddy.USUARIOS WHERE nombreUsuario = 'BeatBuddyApp')");
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();

            while (resultadoSQL.next()) {
                listasPlaylist.add(ConsultasPlaylist.getPlaylist(connection, resultadoSQL.getInt("ID")));
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listasPlaylist;
    }



}
