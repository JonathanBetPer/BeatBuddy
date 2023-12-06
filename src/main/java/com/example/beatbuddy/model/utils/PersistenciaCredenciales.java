package com.example.beatbuddy.model.utils;

import com.example.beatbuddy.model.Cancion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;


public class PersistenciaCredenciales {


    public static class ConexionSQLite {

        private static Connection connection;
        private final String url;

        public ConexionSQLite(String url) {
            this.url = "jdbc:sqlite:"+url;
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


        try {
            PreparedStatement sentenciaSQLUsuario = ConexionSQLite.getConnection().prepareStatement(
                    "INSERT INTO UsuariosLocales (nombreUsuario, passwd) VALUES (?, ?)");
            sentenciaSQLUsuario.setString(1, nombreUsuario);
            sentenciaSQLUsuario.setString(2, contrasena);
            sentenciaSQLUsuario.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean comprobarExistenCredenciales() {


        try {
            PreparedStatement sentenciaSQLUsuario = ConexionSQLite.getConnection().prepareStatement(
                    "SELECT * FROM UsuariosLocales");
            ResultSet resultadoSQL = sentenciaSQLUsuario.executeQuery();
            while (resultadoSQL.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String recuperarUsuario() {
        String nombreUsuario = null;

        try {
            PreparedStatement sentenciaSQLUsuario = ConexionSQLite.getConnection().prepareStatement(
                    "SELECT * FROM UsuariosLocales");
            ResultSet resultadoSQL = sentenciaSQLUsuario.executeQuery();
            while (resultadoSQL.next()) {
                nombreUsuario = resultadoSQL.getString("nombreUsuario");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreUsuario;
    }

    public static Cancion recuperarCancionActual(){
        Cancion cancion = null;

        try {
            PreparedStatement sentenciaSQL = ConexionSQLite.getConnection().prepareStatement("SELECT ID, nombre, letra, autor, archivo FROM cancionActual");
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();


            while (resultadoSQL.next()) {
                cancion = new Cancion(
                        resultadoSQL.getInt("ID"),
                        resultadoSQL.getString("nombre"),
                        resultadoSQL.getString("letra"),
                        resultadoSQL.getString("autor"),
                        blobToFile(resultadoSQL.getBlob("archivo"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cancion;
    }

    public static LinkedList<Cancion> recuperarHistorailCanciones(){
        LinkedList<Cancion> lista = new LinkedList<>();

        try {
            PreparedStatement sentenciaSQL = ConexionSQLite.getConnection().prepareStatement("SELECT ID, nombre, letra, autor, archivo FROM historialCanciones");
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();


            while (resultadoSQL.next()) {
                lista.add(new Cancion(
                        resultadoSQL.getInt("ID"),
                        resultadoSQL.getString("nombre"),
                        resultadoSQL.getString("letra"),
                        resultadoSQL.getString("autor"),
                        blobToFile(resultadoSQL.getBlob("archivo"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    /**
     * Convierte un blob a un archivo temporal mp3 y lo devuelve como File
     * @param blob
     * @return FIle mp3 o null si no se ha podido convertir
     */
    private static File blobToFile(Blob blob){

        if (blob == null) {
            System.out.println("Blob null");
        }

        File tempMp3 = null;
        InputStream is;
        try {
            is = blob.getBinaryStream();

            tempMp3 = File.createTempFile("temp", ".mp3");
            tempMp3.deleteOnExit();

            FileOutputStream fos = new FileOutputStream(tempMp3);

            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                fos.write(buffer);
            }

            fos.close();
            is.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return tempMp3;
    }

    public static void eliminarDatosPersistentes() {
        eliminarUsuario();
        eliminarCancionActual();
        eliminarHistorialCanciones();
    }


    private static void eliminarUsuario() {
        try {
            PreparedStatement sentenciaSQLUsuario = ConexionSQLite.getConnection().prepareStatement(
                    "DELETE FROM UsuariosLocales");
            sentenciaSQLUsuario.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void eliminarCancionActual() {
        try {
            PreparedStatement sentenciaSQLUsuario = ConexionSQLite.getConnection().prepareStatement(
                    "DELETE FROM cancionActual");
            sentenciaSQLUsuario.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void eliminarHistorialCanciones() {
        try {
            PreparedStatement sentenciaSQLUsuario = ConexionSQLite.getConnection().prepareStatement(
                    "DELETE FROM historialCanciones");
            sentenciaSQLUsuario.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
