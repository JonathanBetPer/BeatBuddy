package com.example.beatbuddy.model.bbdd;

import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection connection;
    private final String usuario;
    private final String password;
    private final String url;
    public static final String mysqlDriver = "jdbc:mysql://";
    public static final String sqliteDirver = "jdbc:sqlite:";

    public Conexion(String Driver, String URL, String usuario, String contrasena) {
        this.url = Driver + URL;
        this.password = contrasena;
        this.usuario = usuario;
    }

    public static Connection getConnection() {
        return connection;
    }

    public void iniciar() {
        try {
            connection = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}