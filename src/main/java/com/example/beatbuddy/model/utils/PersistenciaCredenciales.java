package com.example.beatbuddy.model.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class PersistenciaCredenciales {

    private static Properties properties = new Properties();
    private static File archivo = new File("/com/example/beatbuddy/files/credenciales.props");

    public static boolean hayCredenciales(){
        loadProperties();
        return !properties.getProperty("user").equals("") && !properties.getProperty("password").equals("");
    }

    public static String[] recuperarCredenciales(){
        return new String[] {
            properties.getProperty("user"),
            properties.getProperty("password")
        };
    }

    public static void guardarCredenciales(String usuario, String contrasena){
        properties.setProperty("user", usuario);
        properties.setProperty("password", contrasena);
        storeProperties();
    }

    public static void eliminarCredenciales(){
        properties.setProperty("user", "");
        properties.setProperty("password", "");
        storeProperties();
    }


    private static void storeProperties(){
        try {
            properties.store(new FileOutputStream(archivo), "Credenciales");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadProperties(){
        try {
            properties.load(new FileInputStream(archivo));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
