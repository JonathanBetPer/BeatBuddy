package com.example.beatbuddy.model.utils;

import java.sql.*;


public class PersistenciaCredenciales {

    private static class Conexion {

        private static Connection connection;
        private final String url;

        public Conexion() {
            this.url = "jdbc:sqlite:" + "/com/example/beatbuddy/files/persitenciaBeadBuddydb";
        }

        public static Connection getConnection() {
            return connection;
        }

        public void iniciar() {
            try {
                connection = DriverManager.getConnection(url);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public static void close(){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void guardarCredenciales(String nombreUsuario, String contrasena) {
        Conexion conexion = new Conexion();
        conexion.iniciar();

        try {
            PreparedStatement sentenciaSQLUsuario = conexion.getConnection().prepareStatement(
                    "INSERT INTO Usuarios (nombreUsuario, passwd) VALUES (?, ?)");
            sentenciaSQLUsuario.setString(1, nombreUsuario);
            sentenciaSQLUsuario.setString(2, contrasena);
            sentenciaSQLUsuario.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conexion.close();
    }

    public static boolean comprobarExistenCredenciales() {
         new Conexion().iniciar();

        try {
            PreparedStatement sentenciaSQLUsuario = Conexion.getConnection().prepareStatement(
                    "SELECT nombreUsuario, passwd FROM Usuarios");
            ResultSet resultadoSQL = sentenciaSQLUsuario.executeQuery();
            while (resultadoSQL.next()) {
                return true;
            }
            sentenciaSQLUsuario.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Conexion.close();
        return false;
    }

}
