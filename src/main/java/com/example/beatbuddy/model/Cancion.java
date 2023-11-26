package com.example.beatbuddy.model;

public class Cancion {

    private int ID;
    private String nombre;
    private String genero;

    private String autor;
    private String rutaArchivo;

    public Cancion(int ID, String nombre, String genero, String autor, String rutaArchivo) {
        this.ID = ID;
        this.nombre = nombre;
        this.genero = genero;
        this.autor = autor;
        this.rutaArchivo = rutaArchivo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }


}
