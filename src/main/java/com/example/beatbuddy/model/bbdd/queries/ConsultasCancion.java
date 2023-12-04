package com.example.beatbuddy.model.bbdd.queries;

import com.example.beatbuddy.model.Cancion;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public abstract class ConsultasCancion {

    public static Cancion obtenerCancion(Connection connection, int ID){
       Cancion cancion = null;

        try {
            PreparedStatement sentenciaSQL = connection.prepareStatement("SELECT CANCIONES.ID, CANCIONES.nombre, AUTORES.nombre, CANCIONES.letra, CANCIONES.archivoMP3 FROM BeatBuddy.CANCIONES INNER JOIN AUTORES ON CANCIONES.idAutor = AUTORES.ID WHERE CANCIONES.ID = ?");
            sentenciaSQL.setInt(1, ID);
            ResultSet resultadoSQL = sentenciaSQL.executeQuery();


            while (resultadoSQL.next()) {
                cancion = new Cancion(
                        resultadoSQL.getInt("CANCIONES.ID"),
                        resultadoSQL.getString("CANCIONES.nombre"),
                        resultadoSQL.getString("AUTORES.nombre"),
                        resultadoSQL.getString("CANCIONES.letra"),
                        blobToFile(resultadoSQL.getBlob("CANCIONES.archivoMP3"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cancion;
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
}
